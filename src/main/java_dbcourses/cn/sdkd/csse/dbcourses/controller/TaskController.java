package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.Task;
import cn.sdkd.csse.dbcourses.service.ITaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Resource
    public ITaskService taskService;

    @RequestMapping("/list")
    @ResponseBody
    public List<Task> getList()
    {
        System.out.println("tast!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return taskService.getTasklist();
    }
}
