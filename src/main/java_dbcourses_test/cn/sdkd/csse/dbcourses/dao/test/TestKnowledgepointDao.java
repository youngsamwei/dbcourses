package cn.sdkd.csse.dbcourses.dao.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.sdkd.csse.dbcourses.dao.IKnowledgepointDao;
import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.test.dao.TestBaseDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;

/**
 * Created by Sam on 2018/1/7.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestKnowledgepointDao extends TestBaseDao {
  @Resource
  private IKnowledgepointDao mapper;

  private Knowledgepoint testKnowledgepointCase;

  @Before
  public void setup() throws Exception {
    testKnowledgepointCase = getTestKnowledgepointCase();
  }

  @Test
  //增加学生
  public void testAInsert() {

    Assert.assertEquals(1, this.mapper.insert(testKnowledgepointCase).longValue());

  }

  @Test
  //根据学号查询学生
  public void testBFind() {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("knowledgepointName", testKnowledgepointCase.getKnowledgepointName());
    params.put("addName", testKnowledgepointCase.getAddName());
    params.put("knowledgepointCreateDate", testKnowledgepointCase.getKnowledgepointCreateDate());
    List kps = this.mapper.selectByMap(params);
    Assert.assertEquals(1, kps.size());
    testKnowledgepointCase = (Knowledgepoint) kps.get(0);
    Assert.assertNotEquals(-1, testKnowledgepointCase.getId().longValue());
  }

  @Test
  //修改数据
  public void testCUpdate() {
    Knowledgepoint kp = new Knowledgepoint();
    kp.setKnowledgepointName(testKnowledgepointCase.getKnowledgepointName());
    kp.setAddName("samwei");

    kp.setId(testKnowledgepointCase.getId());
    Assert.assertEquals(1, this.mapper.updateById(kp).longValue());
  }

  @Test
  //删除
  public void testDDelete() {
    Assert.assertEquals(1,
      this.mapper.deleteById(testKnowledgepointCase.getId()).longValue());
  }


}
