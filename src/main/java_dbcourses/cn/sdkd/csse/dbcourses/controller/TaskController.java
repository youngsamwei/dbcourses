package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.Task;
import cn.sdkd.csse.dbcourses.service.ITaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.HashMap;
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
    @RequestMapping("/auditaddkonw")
    @ResponseBody
    public Object auditaddknow(@RequestParam("mainid") String knowid,@RequestParam("taskid") String taskid)
    {
        String userName= (String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user");
        HashMap<String,Object> ids=new HashMap<>();
        ids.put("knowid",knowid);
        ids.put("taskid",taskid);
        ids.put("userName",userName);
        taskService.updateTaskKnow(ids);
        System.out.println("___________________________成功");
        return renderSuccess();
    }
}
