package cn.sdkd.csse.dbcourses.utils;
import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.util.*;

//南宁实现增加链接功能
//调用的函数都在这个类里面
public class Add_lianjie {
    public static List<String> stopWord;
    static {
        stopWord = new ArrayList<>();
        // FileInputStream i=new FileInputStream("F://github/stopword1.txt");
        try{
            File file=new File("F://github//dbcourses//src//nannning//stopword1.txt");
            InputStreamReader i = new InputStreamReader(new FileInputStream(file),"gbk");
            BufferedReader r=new BufferedReader(i);
            String str;
            while((str=r.readLine().trim())!=null){
                stopWord.add(str);
            }
            i.close();
            r.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String rmhtml(String paragraph){
        String str= Jsoup.parse(paragraph).text();//将转义字符重新改为html格式
        String str1= Jsoup.parse(str).text();//吃掉html格式
        return str1;
    }
    public static ArrayList<String> fenci(String paragraph){
        Charset charset=Charset.forName("UTF-8");
        WordDictionary.getInstance().loadUserDict(Paths.get("F://github//dbcourses//src//nannning//ciku.txt"),charset);
        JiebaSegmenter s=new JiebaSegmenter();
        ArrayList<String> list=new ArrayList<String>(s.sentenceProcess(paragraph));
        ArrayList list1=new ArrayList();
        Iterator it=list.iterator();
        while(it.hasNext()){
            String obj=(String)it.next();
            if(!list1.contains(obj)&&obj.length()>1&&!stopWord.contains(obj)){//过滤
                list1.add(obj);
            }
        }
        return list1;
        // System.out.println(s.process(text))
            //System.out.print(c+" ");
    }
    public static String addhtml(String paragraph){
        String str="&lt;p&gt;&lt;span style=&quot;color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);&quot;&gt;"+paragraph+"&lt;/span&gt;&lt;/p&gt;";
        return str;
    }
    public static String rmlianjie(String paragraph){
        String str= Jsoup.parse(paragraph).text();//将转义字符重新改为html格式
        Elements Links = Jsoup.parse(str).getElementsByTag("a");
        for (Element link : Links) {
            paragraph=paragraph.replaceFirst("&lt;a.*?(?<=/a&gt;)","&lt;span style=&quot;color: rgb(51, 51, 51); font-family: arial, 宋体, sans-serif; font-size: 14px; text-indent: 28px; background-color: rgb(255, 255, 255);&quot;&gt;"+link.text()+"&lt;/span&gt;");
        }

        return paragraph;
           }
    public static String addlianjie(String s,String paragraph,Integer id){
        ///有待修改

        return paragraph.replaceAll(s,"&lt;a target=&quot;_blank&quot; href=&quot;javascript:loadKnowledgepointParagraph(" + id + ")&quot; style=&quot;white-space: normal; color: rgb(19, 110, 194); text-decoration-line: none; font-family: arial, 宋体, sans-serif; text-indent: 28px; background-color: rgb(255, 255, 255);&quot;&gt;"+s+"&lt;/a&gt;");
    }
}
