package cn.sdkd.csse.dbcourses.dao;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2018/1/6.
 */
public interface IKnowledgepointDao extends BaseMapper<Knowledgepoint> {

    public boolean insertATask(Map params);
    public boolean insertKnow(Knowledgepoint know);
    public List<Knowledgepoint>  selectTopTenByName(Map<String, Object> params);
    public List<Knowledgepoint> selectByName(Map<String, Object> params);
    public List<Knowledgepoint> selectByKid(Map params);
    public Knowledgepoint selectKnowledgepointByName(String name);
    public List<Knowledgepoint> getKnowledgePointByLike(String name);
}
