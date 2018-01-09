package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.IKnowledgepointDao;
import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Sam on 2018/1/7.
 */
@Service
public class KnowledgepointServiceImpl  extends ServiceImpl<IKnowledgepointDao, Knowledgepoint> implements IKnowledgepointService {
}
