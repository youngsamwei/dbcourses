package cn.sdkd.csse.dbcourses.dao;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2018/1/6.
 */
public interface IKnowledgepointDao extends BaseMapper<Knowledgepoint> {


    public List<Knowledgepoint>  selectTopTenByName(Map<String, Object> params);

}
