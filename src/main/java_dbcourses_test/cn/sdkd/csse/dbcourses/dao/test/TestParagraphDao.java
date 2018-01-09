package cn.sdkd.csse.dbcourses.dao.test;


import cn.sdkd.csse.dbcourses.dao.IParagraphDao;

import cn.sdkd.csse.dbcourses.entity.Paragraph;
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
 * Created by Sam on 2018/1/7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mybatis.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestParagraphDao {
  @Resource
  private IParagraphDao mapper;

  @Test
  //增加学生
  public void testAInsert() throws Exception {
    Paragraph p = new Paragraph();
    p.setKnowledgepointId(2);
    p.setAddName("sam");
    p.setParagraphCreateDate(DateUtil.getCurrentDateStr());
    p.setParagraphTitle("定义");
    p.setParagraphContent("关系是一个二维表格。用于定义多个属性值间的联系。");
    p.setParagraphOrder(0);
    Assert.assertEquals(1, this.mapper.insert(p).longValue());

    p = new Paragraph();
    p.setKnowledgepointId(2);
    p.setAddName("sam");
    p.setParagraphCreateDate(DateUtil.getCurrentDateStr());
    p.setParagraphTitle("");
    p.setParagraphContent("关系有EF Codd在1972年的论文中提出。");
    p.setParagraphOrder(1);
    Assert.assertEquals(1, this.mapper.insert(p).longValue());
  }

  @Test
  public void testUpdateParagraphOrder(){

    EntityWrapper ew = new EntityWrapper<>();
    ew.orderBy("paragraphOrder")
    .eq("knowledgepointId", "2");
    List kps = this.mapper.selectList(ew);

    Assert.assertTrue( kps.size()>2);
    Paragraph kp = (Paragraph)kps.get(1);
    this.mapper.updateParagraphOrder(kp);
    kps = this.mapper.selectList(ew);
  }

  @Test
  //根据学号查询学生
  public void testBFind() {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("knowledgepointId", "2");
    List kps = this.mapper.selectByMap(params);

    Assert.assertEquals( 2, kps.size());
  }

  @Test
  //修改数据
  public void testCUpdateParagraphTitle() {
    Paragraph kp = new Paragraph();
    kp.setParagraphTitle("提出人");
    kp.setId(3);
    Assert.assertEquals(1, this.mapper.updateById(kp).longValue());

  }

  @Test
  public void testDFindByTitle() {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("paragraphTitle", "提出人");
    List kps = this.mapper.selectByMap(params);
    Assert.assertEquals(1, kps.size());

  }

  @Test
  //修改数据
  public void testEUpdateParagraphContent() {
    Paragraph kp = new Paragraph();
    kp.setParagraphContent("关系由EF Codd在1972年的论文中提出。");
    kp.setId(3);
    Assert.assertEquals(1, this.mapper.updateById(kp).longValue());

  }

  @Test
  public void testFFindByContent() {
    Map<String, Object> params = new HashMap<String, Object>();
    params = new HashMap<String, Object>();
    params.put("paragraphContent", "%EF Codd%");
    List kps = this.mapper.selectByMap(params);
    Assert.assertEquals(1, kps.size());
  }

  @Test
  //删除
  public void testGDelete() {
    Assert.assertEquals(1, this.mapper.deleteById(1).longValue());
  }

  /*
  @Test
  public void test0DeleteByKnowledgepointId() {
    Assert.assertEquals(this.mapper.deleteByKnowledgepointId(1), 1);
  }
  */

}
