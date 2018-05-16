package cn.sdkd.csse.dbcourses.controller;


import cn.sdkd.csse.dbcourses.entity.UserPower;
import cn.sdkd.csse.dbcourses.service.IUserpowerService;
import cn.sdkd.csse.dbcourses.utils.UserUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
