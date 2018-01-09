package cn.sdkd.csse.dbcourses.service.test;

import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.service.IParagraphService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import cn.sdkd.csse.dbcourses.utils.DateUtil;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2018/1/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:applicationContext.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestParagraphService {
  @Resource
  private IParagraphService paragraphService;


  @Test
  //增加学生
  public void testAInsert() throws Exception {
    Paragraph p = new Paragraph();
    p.setKnowledgepointId(2);
    p.setAddName("sam");
    p.setParagraphCreateDate(DateUtil.getCurrentDateStr());
    p.setParagraphTitle("定义");
    p.setParagraphContent("关系是一个二维表格。用于定义多个属性值间的联系。");
    p.setParagraphOrder(7);
    Assert.assertTrue(this.paragraphService.insert(p));

  }

  @Test
  //根据学号查询学生
  public void testBFind() {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("knowledgepointId", "2");
    List kps = this.paragraphService.selectByMap(params);

    Assert.assertTrue( kps.size() > 2);
  }

  @Test
  //修改数据
  public void testCUpdateParagraphTitle() {
    Paragraph kp = new Paragraph();
    kp.setParagraphTitle("提出人");
    kp.setId(3);

    Assert.assertTrue(this.paragraphService.updateById(kp));

  }

  @Test
  public void testDFindByTitle() {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("paragraphTitle", "提出人");
    List kps = this.paragraphService.selectByMap(params);
    Assert.assertEquals(1, kps.size());

  }

  @Test
  //修改数据
  public void testEUpdateParagraphContent() {
    Paragraph kp = new Paragraph();
    kp.setParagraphContent("关系由E.F. Codd在1972年的论文中提出。");
    kp.setId(3);
    Assert.assertTrue(this.paragraphService.updateById(kp));

  }

  @Test
  public void testFFindByContent() {

    EntityWrapper ew = new EntityWrapper();
    ew.like("paragraphContent", "%E.F. Codd%");
    List kps = this.paragraphService.selectList(ew);
    Assert.assertEquals(1, kps.size());
  }

  @Test
  //删除
  public void testGDelete() {
    Assert.assertTrue( this.paragraphService.deleteById(2));
  }


}
