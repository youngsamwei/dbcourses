package cn.sdkd.csse.dbcourses.weixin.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 *
 * @ClassName WxChatDButils
 * @author ZHANGDAN
 * @date 2018年5月17日
 * @Description TODO
 */

public class WxChatDButils {

    private static String driver;
    private static String url;
    private static String name;
    private static String pass;

    /**
     * 获取数据源的信息
     */
    static {
        name = ResourceBundle.getBundle("jdbc-druid-config").getString("jdbc.username");
        pass = ResourceBundle.getBundle("jdbc-druid-config").getString("jdbc.password");
        driver = ResourceBundle.getBundle("jdbc-druid-config").getString("jdbc.driverclass");
        url = ResourceBundle.getBundle("jdbc-druid-config").getString("jdbc.url");
    }
    static Connection connection = null;
    static ResultSet     resultSet = null;
    static PreparedStatement preparedStatement = null;
    //static Connection connection = null;
    //获取connection
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(driver);

        connection = DriverManager.getConnection(url,name,pass);

        if(connection != null)
            System.out.println("ok");
        else
            System.out.println("no");
        return connection;
    }

    /**
     * 增删改的公共方法
     */
    public static boolean execute(String sql,Object obj[]){
        try {
            connection = getConnection();

            preparedStatement = connection.prepareStatement(sql);

            if(obj != null && obj.length>0){
                for(int i=0; i<obj.length; i++){
                    preparedStatement.setObject(i+1, obj[i]);
                }
                int executeUpdate = preparedStatement.executeUpdate();
                if(executeUpdate>0){
                    return true;
                }else{
                    return false;
                }
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            close(connection,null,preparedStatement);
        }
        return false;
    }
    public static void close(Connection connection,ResultSet resultSet,PreparedStatement preparedStatement){
        try {
            if(connection != null){
                connection.close();}
            if(resultSet != null){
                resultSet.close();}
            if(preparedStatement != null){
                preparedStatement.close();}
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}