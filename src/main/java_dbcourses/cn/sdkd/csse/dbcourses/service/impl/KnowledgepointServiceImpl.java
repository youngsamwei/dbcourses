package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.IKnowledgepointDao;
import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.github.pagehelper.PageHelper;
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
    }
    public  Knowledgepoint selectKnowledgepointByName(String name){

        return this.baseMapper.selectKnowledgepointByName(name);
    }

//    public List<Knowledgepoint> getListCheckBySemester(String likeSem,int epage, int pagesize) {
//        // TODO Auto-generated method stub
//
//        PageHelper.startPage(epage, pagesize,"addtime DESC");
//
//        return scoreCheckListDao.getListCheckBySemester(likeSem);
//    }
  /*  public List<Knowledgepoint> getKnowledgePointByLike(String name){
        return this.baseMapper.getKnowledgePointByLike(name);
    }*/
}

