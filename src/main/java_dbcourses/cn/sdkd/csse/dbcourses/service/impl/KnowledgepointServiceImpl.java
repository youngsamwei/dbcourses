package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.IKnowledgepointDao;
import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2018/1/7.
 */
@Service
public class KnowledgepointServiceImpl  extends ServiceImpl<IKnowledgepointDao, Knowledgepoint> implements IKnowledgepointService {
    public List<Knowledgepoint>  selectTopTenByName(Map<String, Object> params){
        return this.baseMapper.selectTopTenByName(params);
    };
    public boolean insert(Knowledgepoint knowledgepoint,Map params){
        super.insert(knowledgepoint);
        return this.baseMapper.insertATask(params);
    }
    public boolean insertKnow(Knowledgepoint know,Map params)
    {
        this.baseMapper.insertKnow(know);
        int id =know.getId();
        params.put("mainid",id);
        return this.baseMapper.insertATask(params);
    }

}
