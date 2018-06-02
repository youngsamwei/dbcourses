package cn.sdkd.csse.dbcourses.weixin.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;

import cn.sdkd.csse.dbcourses.weixin.dto.WxUser;
import cn.sdkd.csse.dbcourses.weixin.util.WxChatDButils;
import org.springframework.stereotype.Component;

/**
 *
 * @ClassName WxChatDao
 * @author ZHANGDAN
 * @date 2018年5月14日
 */
@Component
public class WxChatDao {
    Connection connection = null;
    ResultSet     resultSet = null;
    PreparedStatement preparedStatement = null;

    //查询方法
    public String select(String sql,Object obj[]){
        String str = null;
        try {
            connection = WxChatDButils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i < obj.length; i++){
                preparedStatement.setObject(i+1, obj[i]);
            }
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                str = resultSet.getString("nickName"); //数据库中的nickname一栏
            }
        }  catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            WxChatDButils.close(connection, resultSet, preparedStatement);
        }
        return str;
    }

    public WxUser selectAccountPasswd(String sql,Object obj[]){
        WxUser user = new WxUser();
        try {
            connection = WxChatDButils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i < obj.length; i++){
                preparedStatement.setObject(i+1, obj[i]);
            }
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                //数据库中的userName一栏
                user.setAccount(resultSet.getString("userName"));
                user.setPassword(resultSet.getString("passWord"));
            }
        }  catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            WxChatDButils.close(connection, resultSet, preparedStatement);
        }
        return user;
    }
    public WxUser selectUser(String sql,Object obj[]){
        WxUser user = new WxUser();
        try {
            connection = WxChatDButils.getConnection();
            preparedStatement = connection.prepareStatement(sql);
            for(int i = 0; i < obj.length; i++){
                preparedStatement.setObject(i+1, obj[i]);
            }
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                //数据库中的userName一栏
                user.setAccount(resultSet.getString("userName"));
                user.setNickname(resultSet.getString("power"));
            }
        }  catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            WxChatDButils.close(connection, resultSet, preparedStatement);
        }
        return user;
    }

    //修改方法
    public boolean update(String sql,Object obj[]){
        return WxChatDButils.execute(sql, obj);
    }
}