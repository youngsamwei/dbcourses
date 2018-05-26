package cn.sdkd.csse.dbcourses.weixin.test;

import java.sql.SQLException;

import cn.sdkd.csse.dbcourses.weixin.util.WxChatDButils;

/**
 *
 * @ClassName Test
 * @author ZHANGDAN
 * @date 2018年5月17日
 */

public class Test {

    public void test(){
        try {
            WxChatDButils.getConnection();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
