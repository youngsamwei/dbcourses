package cn.sdkd.csse.dbcourses.service;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import com.baomidou.mybatisplus.service.IService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2018/1/6.
 */
public interface IKnowledgepointService extends IService<Knowledgepoint> {
    public List<Knowledgepoint>  selectTopTenByName(Map<String, Object> params);
    public List<Knowledgepoint>  selectByName(Map<String, Object> params);
    //public List<Knowledgepoint>  selectKnowList(Map params);
    public Knowledgepoint selectKnowledgepointByName(String name);

    public boolean insertKnow(Knowledgepoint know,Map params);
//    public List<Knowledgepoint> getListCheckBySemester(String likeSem,int epage, int pagesize);
    //public List<Knowledgepoint> getKnowledgePointByLike(String name);
}
