package cn.sdkd.csse.dbcourses.weixin.controller;

import cn.sdkd.csse.dbcourses.controller.BaseController;
import cn.sdkd.csse.dbcourses.weixin.dto.WxUser;
import cn.sdkd.csse.dbcourses.weixin.service.WxChatService;
import cn.sdkd.csse.dbcourses.weixin.util.WxChatUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
/**
 *
 * @ClassName WxChatCallBack
 * @author ZHANGDAN
 * @date 2018年5月14日
 * @Description TODO
 */

@Controller
public class WxChatCallBack  extends BaseController {

    @Autowired
    WxChatService service;

    @RequestMapping("/callBack")
    public void callBack(HttpServletRequest req , HttpServletResponse resp)throws Exception{
        req.setCharacterEncoding("utf-8");

        //2 第二步：通过code换取网页授权access_token
        String code = req.getParameter("code");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+ WxChatUtils.APP_ID
                + "&secret="+WxChatUtils.APP_SECRET
                + "&code="+code
                + "&grant_type=authorization_code";
        JSONObject jsonObject = WxChatUtils.getJSONObject(url);

        String access_token = jsonObject.getString("access_token");
        String openid = jsonObject.getString("openid");
        //3 第三步：刷新access_token（如果需要）
        //4 第四步：拉取用户信息(需scope为 snsapi_userinfo)
        String  returnUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+access_token
                + "&openid="+openid
                + "&lang=zh_CN";
        JSONObject  userinfo = WxChatUtils.getJSONObject(returnUrl);
        String oid = userinfo.getString("openid");
        String nickname = userinfo.getString("nickname");
        WxChatService service = new WxChatService();
        String sel = service.getSel(openid);
        if(sel != null){
            //账号和微信已绑定
            service = new WxChatService();
            WxUser user = service.getAccountByOpenid(openid);
            String username = userinfo.getString("nickname");
            System.out.println("-----------------------"+user.getAccount());
            System.out.println("-----------------------"+user.getNickname());
            req.setAttribute("power","0");
            HttpSession session = req.getSession();  //获取登录信息
            session.setAttribute("user",user.getAccount());
            session.setAttribute("power","0");
            resp.sendRedirect("/weChatLoginSuccess.jsp");
        }
        else{
            //没有绑定的情况下
            req.setAttribute("openid", openid);
            //没有绑定跳转到账号登录并绑定微信的页面
            req.getRequestDispatcher("/weChatBindLogin.jsp").forward(req, resp);
        }

    }

    @RequestMapping("bindUser")
    public void bindWxUser(HttpServletRequest req,@RequestParam("userName")String userName,
                           @RequestParam("openid")String openid,
                           @RequestParam("passWord")String passWord,
                            HttpServletResponse resp)throws Exception{
        WxChatService service = new WxChatService();
        WxUser user = service.getAccount(userName);
        if(user.getAccount()!=null&&user.getAccount().equals(userName)
                &&user.getPassword()!=null&&user.getPassword().equals(passWord)){
            //update
            service.getUp(openid, userName,passWord);
            HttpSession session = req.getSession();  //获取登录信息
            session.setAttribute("user",userName);
            session.setAttribute("power","0");
            resp.sendRedirect("/weChatLoginSuccess.jsp");
        }else{
            resp.sendRedirect("/weChatLoginEorr.jsp");
        }

    }
}
