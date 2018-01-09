package cn.sdkd.csse.dbcourses.controller.test;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Sam on 2018/1/7.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:applicationContext.xml"})

// 添加注释@Transactional 回滚对数据库操作
@Transactional
public class BaseSpringTestCase {
}
