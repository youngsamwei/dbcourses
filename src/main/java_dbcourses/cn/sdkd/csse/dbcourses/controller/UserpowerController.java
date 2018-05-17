package cn.sdkd.csse.dbcourses.controller;


import cn.sdkd.csse.dbcourses.entity.UserPower;
import cn.sdkd.csse.dbcourses.service.IUserpowerService;
import cn.sdkd.csse.dbcourses.utils.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/userpower")
public class UserpowerController extends BaseController{
    @Resource
    public IUserpowerService userpowerService;
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(UserpowerController.class);

    @RequestMapping("/add")
    @ResponseBody
    public Object addPowerusers(@RequestParam("powerCode") String powerCode,@RequestParam("idlist") String idlist)
    {
        List<UserPower> list= new ArrayList<>();
        UserPower up;
        int[] ids = UserUtils.spiltId(idlist);
        for(int i=0;i<ids.length;i++)
        {
            up=new UserPower();
            up.setUserId(ids[i]);
            up.setPowerCode(powerCode);
            list.add(up);
        }
        userpowerService.insertPower(list);
        return renderSuccess();
    }
    @RequestMapping("/delete")
    @ResponseBody
    public Object deletePowerusers(@RequestParam("powerCode") String powerCode,@RequestParam("idlist") String idlist)
    {
        Map params=new HashMap<>();
        userpowerService.deleteUserpower(powerCode,idlist);
        return  renderSuccess();
    }

    @RequestMapping("/getpower")
    @ResponseBody
    public Object getUserpower(@RequestParam("powers") String powerCode)
    {
        String userName= (String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user");
        HashMap<String,String> params=new HashMap<>();
        params.put("userName",userName);
        params.put("powerCode",powerCode.substring(1,powerCode.length()));
        String[] power=userpowerService.selectuserPower(params);
        String msg="权限不足";
        for(int i=0;i<power.length;i++)
        {
            if(power[i].equals("9999999")||power[i].equals(powerCode))
            {
                msg="获得权限";
            }
        }
        return renderSuccess(msg);
    }

}
