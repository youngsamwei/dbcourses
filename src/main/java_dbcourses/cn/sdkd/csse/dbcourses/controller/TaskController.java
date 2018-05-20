package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.entity.Task;
import cn.sdkd.csse.dbcourses.service.ITaskService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.validation.Valid;
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
    @RequestMapping("/auditadd")
    @ResponseBody
    public Object auditaddknow(@RequestParam("mainid") String mainid,@RequestParam("taskid") String taskid,@RequestParam("type") String type)
    {
        String userName= (String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user");
        HashMap<String,Object> ids=new HashMap<>();
        ids.put("mainid",mainid);
        ids.put("taskid",taskid);
        ids.put("userName",userName);
        System.out.println("pppppppppppp"+type);
        if(type.equals("know")) {
            taskService.updateTaskKnow(ids);
        }
        else if(type.equals("para"))
        {
            taskService.updateTaskPara(ids);
        }
        System.out.println("___________________________成功");
        return renderSuccess();
    }
    @RequestMapping("/editpara")
    @ResponseBody
    public Object editPara(@Valid Paragraph paragraph)
    {
        String userName= (String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user");
        //HashMap<String,Object> edit=new HashMap<>();
        Task task =new Task();
        task.setSubmitter(userName);
        task.setMainid(paragraph.getId());
        task.setContent(paragraph.getParagraphContent());
        task.setType("22");
        taskService.insert(task);
        return renderSuccess();
    }

    @RequestMapping("/auditpe")
    @ResponseBody
    public Object auditpe(@RequestParam("mainid") String mainid,@RequestParam("taskid") String taskid)
    {
        String userName= (String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user");
        HashMap<String,Object> params= new HashMap<>();
        params.put("mainid",mainid);
        params.put("taskid",taskid);
        params.put("userName",userName);
        taskService.updateParaEdit(params);
        return  renderSuccess();
    }

}
