package cn.sdkd.csse.dbcourses.controller.test;

import cn.sdkd.csse.dbcourses.controller.KnowledgepointController;
import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

/**
 * Created by Sam on 2018/1/7.
 */
public class TestKnowledgepointController extends BaseSpringTestCase{
  private MockHttpServletRequest request;
  private MockHttpServletResponse response;
  private IKnowledgepointService userService;
  @Autowired
  private KnowledgepointController testController ;
  @Before
  public void before(){
/*        ApplicationContext context = new ClassPathXmlApplicationContext(new String[]{"classpath:applicationContext.xml"
                ,"classpath:mybatis/mybatis-sqlserver.xml"});*/
    request = new MockHttpServletRequest();
    request.setCharacterEncoding("UTF-8");
    response = new MockHttpServletResponse();
  }

  @Test
  public void addUser(){
    Knowledgepoint user = new Knowledgepoint();
    String id= "23" ;
    //assertEquals("loginok",testController.getUser(id)) ;
  }
}

