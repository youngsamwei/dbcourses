package cn.sdkd.csse.dbcourses.service.test;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import cn.sdkd.csse.dbcourses.utils.DateUtil;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2018/1/7.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class TestKnowledgepointService {
  @Resource
  private IKnowledgepointService knowledgepointService;

  private Knowledgepoint testKnowledgepointCase;

  @Before
  public void setup() throws Exception {
    testKnowledgepointCase = getTestKnowledgepointCase();
  }

  @Test
  //增加学生
  public void testAInsert() {

    Assert.assertTrue(this.knowledgepointService.insert(testKnowledgepointCase));

  }

  @Test
  //根据学号查询学生
  public void testBFind() {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("knowledgepointName", testKnowledgepointCase.getKnowledgepointName());
    params.put("addName", testKnowledgepointCase.getAddName());
    params.put("knowledgepointCreateDate", testKnowledgepointCase.getKnowledgepointCreateDate());
    List kps = this.knowledgepointService.selectByMap(params);
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

    EntityWrapper ew = new EntityWrapper();
    ew.eq("knowledgepointName", "testKnowledgepointCase.getKnowledgepointName()")
      .eq("addName","samwei");
    Assert.assertEquals(1, this.knowledgepointService.updateById(kp));
  }

  @Test
  public void testUpdateByMap(){
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("knowledgepointName", testKnowledgepointCase.getKnowledgepointName());

    List kps = this.knowledgepointService.selectByMap(params);
    Assert.assertEquals(1, kps.size());
    Knowledgepoint kp = (Knowledgepoint)kps.get(0);

    Knowledgepoint kp2 = new Knowledgepoint();
    kp2.setAddName("samwei");
    kp2.setId(kp.getId());
    Assert.assertTrue(this.knowledgepointService.updateById(kp2));


  }
  @Test
  //删除
  public void testDDelete() {
    Assert.assertEquals(1,
      this.knowledgepointService.deleteById(testKnowledgepointCase.getId()));
  }

  @Test
  public void testAdd(){
    Knowledgepoint kp=new Knowledgepoint();
    kp.setKnowledgepointName("关系");
    kp.setAddName("samwei");

    //knowledgepointService.insertArticle(kp);
  }
  public Knowledgepoint getTestKnowledgepointCase() throws Exception {
    Knowledgepoint kp = new Knowledgepoint();
    kp.setKnowledgepointName("关系数据库");
    kp.setAddName("sam");
    kp.setKnowledgepointCreateDate(DateUtil.getCurrentDateStr());
    kp.setId(-1);
    return kp;
  }
}
