package cn.sdkd.csse.dbcourses.weixin.util;

import java.io.IOException;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.sun.xml.internal.stream.Entity;

/**
 *
 * @ClassName WxChatUtils
 * @author ZHANGDAN
 * @date 2018年5月13日
 * @Description TODO（微信公共方法）
 */
public class WxChatUtils {
    //放入当前公众号的Appid和Appsecret
    public static final String APP_ID="wx34288c285dc802cb";
    public static final String APP_SECRET="842fcf2927c96eef10e783bdeeb494a8";

    //Stirng url 地址
    public static JSONObject getJSONObject(String url) throws ClientProtocolException, IOException{
        JSONObject fromObject = null;
        //初始化对象
        DefaultHttpClient defaultHttpClient = new DefaultHttpClient();
        /**
         * 通过get方法提交
         * 并传入地址
         */
        HttpGet httpGet = new HttpGet(url);
        //发送请求
        HttpResponse execute = defaultHttpClient.execute(httpGet);
        //返回结果
        HttpEntity entity = execute.getEntity();
        if(execute != null){
            //转换成String类型
            String string = EntityUtils.toString(entity,"UTF-8");
            //String类型转换成JSON格式的
            fromObject = JSONObject.fromObject(string);
        }
        httpGet.releaseConnection();//链接关闭
        return fromObject;

    }
}