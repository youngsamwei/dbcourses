package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.User;
import cn.sdkd.csse.dbcourses.service.IUserService;
import cn.sdkd.csse.dbcourses.utils.UserUtils;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController {
    @Resource
    public IUserService userService;
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(UserController.class);// 日志文件

    @RequestMapping("/login")
    @ResponseBody
    public String login(String username,String password,HttpServletRequest req){

        log.info("success");
        User check=userService.selectPassword(username);
        System.out.println(check.getUserName());
        System.out.println(check.getPassWord());
        System.out.println(check.getPower());
        if(check.getPassWord().equals(password))
        {
            System.out.println("dengluchenggong");
            HttpSession session =req.getSession();
            session.setAttribute("user",username);
            session.setAttribute("power",check.getPower());
            return "index";
        }
        else
        {
            return "login";
        }
    }


    @RequestMapping("/register")
    @ResponseBody
    public void register(@RequestParam("username") String username,@RequestParam("password") String password,@RequestParam("nickname") String nickname )
    {
        HashMap<String,Object> user=new HashMap<>();
        user.put("username",username);
        user.put("password",password);
        user.put("nickname",nickname);
        userService.insertUser(user);
        System.out.println("zhuce chenggong");
    }

    @ResponseBody
    @RequestMapping("/list")
    public List<User> List(User user,@RequestParam("page") String page,@RequestParam("rows") String rows)
    {
        System.out.println(page +" "+rows);
        EntityWrapper ew = new EntityWrapper();
        if(user.getUserId()!=null){
            ew.eq("userId", user.getUserId());
        }
        else if (user.getUserName()!=null)
        {
            ew.like("userName", MessageFormat.format("%{0}%", user.getUserName()));
        }else
        { }
        System.out.println(ew.getSqlSegment());
        List<User> ls = userService.selectList(ew);
        return ls;
    }

    @ResponseBody
    @RequestMapping("/init")
    public String init(@RequestParam("page") String page, @RequestParam("rows") String rows, HttpServletRequest req)
    {
        String userName =req.getParameter("userName");
        String nickName =req.getParameter("nickName");
        String userGroup =req.getParameter("userGroup");

        System.out.println(" ////////////////////////////////"+userName);
        List<User> list=new ArrayList<>();
        int min =Integer.parseInt(page);
        int row =Integer.parseInt(rows);
        HashMap<String,Object> pages=new HashMap<>();
        pages.put("min",(min-1)*row);
        pages.put("max",min*row);
        pages.put("userName","%"+userName+"%");
        pages.put("nickName","%"+nickName+"%");
        if(userGroup!=null) {
            if(!userGroup.equals("0"))
                pages.put("userGroup", userGroup);
        }
        System.out.println(pages.toString());
        int total =userService.selectCount(pages);
        list =userService.selectAll(pages);
        System.out.println("---------{\"total\":"+total+",\"rows\":"+list.toString()+"} -------------");
        return "{\"total\":"+total+",\"rows\":"+list.toString()+"}";
    }
    @ResponseBody
    @RequestMapping("/getchildren")
    public String getGroupUser(@RequestParam("ids") String id,@RequestParam("id") String gid)
    {
        int[] ids=UserUtils.spiltId("'"+id+"'");
        System.out.println("1111111111111111111111   "+gid);
        for(int i=0;i<ids.length;i++)
        {
            System.out.println("1111111111111111111111 ids   "+ids[i]);
        }
        List<User>list =userService.selectChildren(gid,ids);
        return list.toString().replace("userName","text").replace("userId","id");
    }

    @ResponseBody
    @RequestMapping("/delete")
    public Object delete(@RequestParam("idlist") String idlist)
    {

        userService.deleteUsersBatch(idlist);
        return renderSuccess("删除成功！");
    }

    @ResponseBody
    @RequestMapping("/update")
    public Object update(@RequestParam("user") String  jsonstr)
    {

        String userstr=jsonstr.replace("&quot;","\"").replace("&#39;","\'");
        System.out.println(userstr);
        JSONObject edit = JSONObject.fromObject(userstr);
        Map maps = (Map)JSON.parse(userstr);
        System.out.println("------------------------"+maps.get("power"));
        String str=maps.get("power").toString();
        String[] list=str.substring(1, str.length()-1).replace("\"", "").split(",");
        int power=0;
        for(int i=0;i<list.length;i++)
        {
            power+=Integer.parseInt(list[i]);
        }
        if(power>=9)
        {
            power=9;
        }
        maps.put("power",power);
        userService.updateUser(maps);
        return renderSuccess("修改成功");
    }
    @ResponseBody
    @RequestMapping("/selectbyid")
    public Object selectById(@RequestParam("userId")  String id)
    {

        int userId =Integer.parseInt(id);
        System.out.println(userId);
        User user= userService.selectUserById(userId);
        System.out.println(user.toString());
        return user;
    }

    @ResponseBody
    @RequestMapping("/logout")
    public Object logout()
    {
        ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().invalidate();
        return renderSuccess("登出成功");
    }

    @ResponseBody
    @RequestMapping("/powerCode")
    public List<User> getPowerCodeUser(@RequestParam("powerCode") String powerCode)
    {
         List<User> ls =userService.selectByUserpower(powerCode);
         return ls;
    }

}
