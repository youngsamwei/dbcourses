package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.IKnowledgepointDao;
import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import cn.sdkd.csse.dbcourses.utils.Word2VEC;
import cn.sdkd.csse.dbcourses.utils.WordEntry;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.sun.tools.internal.ws.wsdl.document.jaxws.Exception;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;

/**
 * Created by Sam on 2018/1/7.
 */
@Service
public class KnowledgepointServiceImpl  extends ServiceImpl<IKnowledgepointDao, Knowledgepoint> implements IKnowledgepointService {
    public List<Knowledgepoint>  selectTopTenByName(Map<String, Object> params){
        return this.baseMapper.selectTopTenByName(params);
    }
    public List<Knowledgepoint>  selectKnowList(Map params){
        return this.baseMapper.selectByKid(params);
    }
    public List<Knowledgepoint>  selectByName(Map<String, Object> params) {
      //  ArrayList<String> list=new ArrayList<String>();
        List<Knowledgepoint> ls=new ArrayList<>();
        List<Knowledgepoint> ls0=new ArrayList<>();
        String name=params.get("knowledgepointName").toString();
        Map<String, Object> p0 = new HashMap<String, Object>();
        p0.put("knowledgepointName", name);
        ls0=this.baseMapper.selectByName(p0);
        if(ls0.size()>0){
            ls0.get(0).setXiangguandu(100);
            ls.add(ls0.get(0));
            try{
                System.out.println(name);
                //list=Word2VEC.word2vec(name);
                Set<WordEntry> set=Word2VEC.word2vec(name);
                for (WordEntry s:set){
                    List<Knowledgepoint> ls1=new ArrayList<>();//最终放在for循环
                    Map<String, Object> p = new HashMap<String, Object>();
//                    p.put("knowledgepointName", list.get(i));
                    p.put("knowledgepointName", s.name);
                    ls1=this.baseMapper.selectByName(p);
                    if(ls1.size()>0){
                        int  i;
                        i= (int)(s.score*100);
                        ls1.get(0).setXiangguandu(i);
                        ls.add(ls1.get(0));
                        System.out.println("第三个"+ls);
                    }

                }
                System.out.println("测试成功");
            }
            catch (java.lang.Exception e){
                e.printStackTrace();
            }
        }
        for(int j=0;j<ls.size();j++){
            System.out.println("名称"+ls.get(j).getKnowledgepointName());
        }
        return ls;
    }
};
