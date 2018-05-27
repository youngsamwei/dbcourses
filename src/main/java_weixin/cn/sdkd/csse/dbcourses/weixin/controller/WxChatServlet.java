package cn.sdkd.csse.dbcourses.weixin.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.sdkd.csse.dbcourses.weixin.util.WxChatUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @ClassName WxChatServlet
 * @author ZHANGDAN
 * @date 2018年5月14日
 * @Description TODO
 */
@Controller
public class WxChatServlet{
    @RequestMapping("/wxChatServlet")
    public void getValadate(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
//        System.out.println("servlet+++++++++++++++++++++++++++++");
        //获取返回的回调地址
        String str = "http://wechat.ngrok.xiaomiqiu.cn/callBack.do";
        //1 第一步：用户同意授权，获取code
        String url = "https://open.weixin.qq.com/connect/oauth2/authorize?appid="+WxChatUtils.APP_ID
                + "&redirect_uri="+URLEncoder.encode(str)
                + "&response_type=code"
                + "&scope=snsapi_userinfo"
                + "&state=STATE#wechat_redirect";
        System.out.println("------------"+url);

        //进行重定向
        resp.sendRedirect(url);
    }

}