package cn.sdkd.csse.dbcourses.test.dao;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.utils.DateUtil;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Sam on 2018/1/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestBaseDao {
  public Knowledgepoint getTestKnowledgepointCase() throws Exception {
    Knowledgepoint kp = new Knowledgepoint();
    kp.setKnowledgepointName("关系");
    kp.setAddName("sam");
    kp.setKnowledgepointCreateDate(DateUtil.getCurrentDateStr());
    kp.setId(-1);
    return kp;
  }
}
