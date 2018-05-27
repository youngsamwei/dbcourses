package cn.sdkd.csse.dbcourses.utils;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.*;
import org.apache.solr.common.*;

import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import org.apache.solr.client.solrj.impl.HttpSolrClient;
import org.apache.solr.client.solrj.response.*;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/***
 * created by weihongwei 2018/5/7
 * solr 实现内容搜索，返回摘要信息，并高亮关键字
 */
@Component
public class solr {

    /***
     * 设置定时器更新索引表
     * @throws Exception
     */

    /**
     * CRON表达式                含义
     "0 0 12 * * ?"            每天中午十二点触发
     "0 15 10 ? * *"            每天早上10：15触发
     "0 15 10 * * ?"            每天早上10：15触发
     "0 15 10 * * ? *"        每天早上10：15触发
     "0 15 10 * * ? 2005"    2005年的每天早上10：15触发
     "0 * 14 * * ?"            每天从下午2点开始到2点59分每分钟一次触发
     "0 0/5 14 * * ?"        每天从下午2点开始到2：55分结束每5分钟一次触发
     "0 0/5 14,18 * * ?"        每天的下午2点至2：55和6点至6点55分两个时间段内每5分钟一次触发
     "0 0-5 14 * * ?"        每天14:00至14:05每分钟一次触发
     "0 10,44 14 ? 3 WED"    三月的每周三的14：10和14：44触发
     "0 15 10 ? * MON-FRI"   每个周一、周二、周三、周四、周五的10：15触发
     "0 0/30 * * * ?"         30分钟执行一次
     */
    @Scheduled(cron = "0 0/30 * * * ?")
    public void solrDateImport() throws Exception{
        //URL url = new URL("http://localhost:8983/solr/mycore/dataimport?command=full-import");
        URL url = new URL("http://47.94.224.222:8983/solr/mycore/dataimport?command=full-import&clean=true&commit=true&entity=knowledgepoint");
        //URLConnection con = url.openConnection();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        //设置连接超时的时间
        //con.setDoOutput(true);
        //在使用post请求的时候，设置不能使用缓存
        //con.setUseCaches(false);
        //设置该请求为post请求
        con.setRequestMethod("GET");
        //con.setInstanceFollowRedirects(true);
        //配置请求content-type
        //con.setRequestProperty("Content-Type", "application/json, text/javascript");
        //执行连接操作
        con.connect();
        InputStreamReader in = new InputStreamReader(con.getInputStream());
        //System.out.println("索引已更新=======================");
        //发送请求的参数
        //DataOutputStream dos=new DataOutputStream(con.getOutputStream());
        //dos.flush();
        //dos.close();
    }
    /**
     * 查询
     * @throws Exception
     */
    public SolrDocumentList querySolr(String keyword) throws Exception {
        //[1]获取连接
        // HttpSolrClient client= new HttpSolrClient.Builder("http://127.0.0.1:8080/solr/core1").build();
        String solrUrl = "http://47.94.224.222:8983/solr/mycore";
        //创建solrClient同时指定超时时间，不指定走默认配置
        HttpSolrClient client = new HttpSolrClient.Builder(solrUrl)
                .withConnectionTimeout(10000)
                .withSocketTimeout(60000)
                .build();
        //创建查询对象
        SolrQuery query = new SolrQuery();
        //q 查询字符串，如果查询所有*:*
        //query.set("q",keyword);
        query.set("q","knowledgepointName:" + keyword + " paragraphContent:" + keyword);
        //System.out.println("keyword======================================" + keyword);
        //sort 排序，请注意，如果一个字段没有被索引，那么它是无法排序的
//        query.set("sort", "product_price desc");
        //start row 分页信息，与mysql的limit的两个参数一致效果
        query.setStart(0);
        query.setRows(10000);
        //fl 查询哪些结果出来，不写的话，就查询全部，所以我这里就不写了
//        query.set("fl", "");
        //df 默认搜索的域
        //query.set("df", "keywords");
        //======高亮设置===已在配置文件添加
        //开启高亮
        query.setHighlight(true);
        //高亮域
        query.addHighlightField("paragraphContent,knowledgepointName");
        //前缀
        //query.setHighlightSimplePre("&lt;font color=&quot;red&gt;");
        //后缀
        //query.setHighlightSimplePost("&lt;/font&gt;");
        query.setHighlightSimplePre("&lt;b style=\"color:red\"&gt;");
        query.setHighlightSimplePost("&lt;/b&gt;");
        query.setHighlightFragsize(1000);




        //[4]执行查询返回QueryResponse
        QueryResponse response = client.query(query);
//        //[5]获取doc文档
        SolrDocumentList documents = response.getResults();
        //查询出来的数量
        //long numFound = documents.getNumFound();
        //System.out.println("总查询出:" + numFound + "条记录");
        //[6]内容遍历
        Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

        for (SolrDocument doc : documents) {
            //System.out.println("id:"+doc.get("id"));
           //         +"\tknowledgepointName:"+doc.get("knowledgepointName")
           //         +"\tparagraphContent:"+doc.get("paragraphContent"));
//                    +"\tkeywords:"+doc.get("keywords"));

            //输出高亮
            Map<String, List<String>> map = highlighting.get(doc.get("id"));
            List<String> list = map.get("knowledgepointName");
            if(list != null && list.size() > 0){
                //System.out.println("knowledgepointName:");
                //System.out.println(list.get(0));
                doc.setField("knowledgepointName",list.get(0));
            }
            List<String> listt = map.get("paragraphContent");
            if(listt != null && listt.size() > 0) {
                //System.out.println("paragraphContent:");
                //System.out.println(listt.get(0));
                doc.setField("paragraphContent",listt.get(0)+"...");
            }
        }




        client.close();
        //return highlighting;
        return documents;
    }

    //public void solrAdd(Integer id,String field,String content) throws Exception{
        //[1]获取连接
        // HttpSolrClient client= new HttpSolrClient.Builder("http://127.0.0.1:8080/solr/core1").build();
        //String solrUrl = "http://127.0.0.1:8983/solr/mycore";
        //创建solrClient同时指定超时时间，不指定走默认配置
        //HttpSolrClient client = new HttpSolrClient.Builder(solrUrl)
        //        .withConnectionTimeout(10000)
        //        .withSocketTimeout(60000)
        //        .build();
        //[2]创建文档doc
        //SolrInputDocument doc = new SolrInputDocument();

        //System.out.println("KnowledgepointId=" + id + "   field=" + field + "     content=" + content + "++++++++++++++++++++++++++");

        //[3]添加内容
        //doc.addField("id", id);
        //doc.addField(field, content);

        //[4]添加到client
        //UpdateResponse updateResponse = client.add(doc);
        //System.out.println(updateResponse.getElapsedTime());
        //[5] 索引文档必须commit
        //client.commit();
        //client.close();
    //}

    /**
     * 单个id 的删除索引
     */
//    public void solrDelete(Integer id) throws Exception{
//        String solrUrl = "http://127.0.0.1:8983/solr/mycore";
//        HttpSolrClient client = new HttpSolrClient.Builder(solrUrl)
//                .withConnectionTimeout(10000)
//                .withSocketTimeout(60000)
//                .build();
//        //[2]通过id删除
//        client.deleteById("id",id);
//        //[3]提交
//        client.commit();
//        //[4]关闭资源
//        client.close();
//    }

}



