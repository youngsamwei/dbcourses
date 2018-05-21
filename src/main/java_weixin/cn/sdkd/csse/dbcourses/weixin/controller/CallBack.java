package cn.sdkd.csse.dbcourses.weixin.controller;

import cn.sdkd.csse.dbcourses.weixin.service.Service;
import cn.sdkd.csse.dbcourses.weixin.util.WxChatUtils;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class CallBack {

    @RequestMapping("/callBack")
    public void callBack(HttpServletRequest req , HttpServletResponse resp)throws  Exception{
        req.setCharacterEncoding("utf-8");
        String openid = req.getParameter("openid");
        String account = req.getParameter("account");
        String password = req.getParameter("password");
        Service  service = new Service();
        boolean up = service.getUp(openid, account, password);
        if(up){
            String sel = service.getSel(openid);
            req.setAttribute("name", sel);
            req.getRequestDispatcher("/weChatHinder.jsp").forward(req, resp);
        }
        //else{
            //账号和密码错误进行跳转

        //}
    }
}
