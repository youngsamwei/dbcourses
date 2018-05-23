package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.Group;
import cn.sdkd.csse.dbcourses.service.IGroupService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/group")
public class GroupController extends BaseController{
    @Resource
    private IGroupService groupService;
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(GroupController.class);// 日志文件

    @ResponseBody
    @RequestMapping("/list")
    public List<Group> list()
    {
        //System.out.println(groupService.selectAllGroup().toString());
        return groupService.selectAllGroup();
    }
    @ResponseBody
    @RequestMapping("/getgroup")
    public String getGroup()
    {
        List<Group> ls=groupService.selectAllGroup();
        return ls.toString();
    }
    @ResponseBody
    @RequestMapping("/somegroup")
    public List<Group> getSomeGroup()
    {

        return groupService.selectSomeGroup();
    }

    @ResponseBody
    @RequestMapping("/add")
    public Object addgroup(@RequestParam("name") String name)
    {
        groupService.insertuserGroup(name);
        return renderSuccess();
    }
}
