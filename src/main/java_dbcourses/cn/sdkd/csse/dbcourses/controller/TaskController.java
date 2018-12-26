package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.entity.Task;
import cn.sdkd.csse.dbcourses.service.ITaskService;
import cn.sdkd.csse.dbcourses.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/task")
public class TaskController extends BaseController {

    @Resource
    public ITaskService taskService;

    @RequestMapping("/list")
    @ResponseBody
    public String getList()
    {
       // System.out.println("tast!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return taskService.getTasklist().toString();
    }
    @RequestMapping("/listknow")
    @ResponseBody
    public String getListkonw(@RequestParam("page") String page, @RequestParam("rows") String rows, HttpServletRequest req)
    {
        String approver =req.getParameter("approver");
        String beforetime =req.getParameter("beforetime");
        String aftertime =req.getParameter("aftertime");
        String submitter =req.getParameter("submitter");
        String type =req.getParameter("type");
        String status =req.getParameter("taskstatus");
        System.out.println("----------------------------------------------------");
        System.out.println("1"+approver+"1");
        System.out.println(beforetime);
        System.out.println(aftertime);
        System.out.println(submitter);
        System.out.println(type);
        int min =Integer.parseInt(page);
        int row =Integer.parseInt(rows);
        HashMap<String,Object> pages=new HashMap<>();
        pages.put("typelike","1_");
        if(approver!=null&&!approver.equals("")){
            pages.put("approver", "%"+approver+"%");
        }
        if(submitter!=null&&!submitter.equals("")){
            pages.put("submitter", "%"+submitter+"%");
        }
        if(type!=null&&!type.equals("null")){
            pages.put("type",type);
        }
        if(beforetime!=null&&!beforetime.equals("")) {
            pages.put("beforetime", beforetime);
        }
        if(aftertime!=null&&!aftertime.equals("")) {
            pages.put("aftertime", aftertime);
        }
        pages.put("status",status);
        pages.put("min",(min-1)*row);
        pages.put("size",row);
        int total =taskService.selectTaskCount(pages);
        return "{\"total\":"+total+",\"rows\":"+taskService.getTasklistknow(pages).toString()+"}";

    }
    @RequestMapping("/listpara")
    @ResponseBody
    public String getListpara(@RequestParam("page") String page, @RequestParam("rows") String rows, HttpServletRequest req)
    {
        String approver =req.getParameter("approver");
        String beforetime =req.getParameter("beforetime");
        String aftertime =req.getParameter("aftertime");
        String submitter =req.getParameter("submitter");
        String type =req.getParameter("type");
        String status =req.getParameter("taskstatus");

        int min =Integer.parseInt(page);
        int row =Integer.parseInt(rows);
        HashMap<String,Object> pages=new HashMap<>();
        pages.put("typelike","2_");
        if(approver!=null&&!approver.equals("")){
            pages.put("approver", "%"+approver+"%");
        }
        if(submitter!=null&&!submitter.equals("")){
            pages.put("submitter", "%"+submitter+"%");
        }
        if(type!=null&&!type.equals("null")){
            pages.put("type",type);
        }
        if(beforetime!=null&&!beforetime.equals("")) {
            pages.put("beforetime", beforetime);
        }
        if(aftertime!=null&&!aftertime.equals("")) {
            pages.put("aftertime", aftertime);
        }
        pages.put("status",status);
        pages.put("min",(min-1)*row);
        pages.put("size",row);
        System.out.println((min-1)*row+"------1111111111111---------"+row);
        int total =taskService.selectTaskCount(pages);
        // System.out.println("tast!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "{\"total\":"+total+",\"rows\":"+taskService.getTasklistpara(pages).toString()+"}";
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
        try {
            ids.put("approverTime", DateUtil.getCurrentDateStr());
        }catch (Exception e){
            return renderError("错误");
        }
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
        try {
            task.setCreateTime(DateUtil.getCurrentDateStr());
        }catch (Exception e)
        {
            e.printStackTrace();
        }
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
        try {
            params.put("approverTime", DateUtil.getCurrentDateStr());
        }
        catch (Exception e){
            return renderError("错误");
        }
        taskService.updateParaEdit(params);
        return  renderSuccess();
    }
    @RequestMapping("/paradelete")
    @ResponseBody
    public Object paradelete(@Valid Task task)
    {
        try {
            task.setSubmitter((String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user"));
            task.setCreateTime(DateUtil.getCurrentDateStr());
            taskService.insert(task);
            return renderSuccess("删除成功,等待审核");
        }
        catch (org.springframework.dao.DuplicateKeyException e){
            System.out.println("创建任务失败,或以存在相同的任务");
            return renderError("创建任务失败,或以存在相同的任务");
        }
        catch (Exception ex)
        {
            return renderError("创建任务失败");
        }
    }
    @RequestMapping("/knowdelete")
    @ResponseBody
    public Object knowdelete(@Valid Task task)
    {
        try {
            task.setSubmitter((String) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user"));
            task.setCreateTime(DateUtil.getCurrentDateStr());
            taskService.insert(task);
            return renderSuccess("删除成功,等待审核");
        } catch (org.springframework.dao.DuplicateKeyException e){
            System.out.println("创建任务失败,或以存在相同的任务");
            return renderError("创建任务失败,或以存在相同的任务");
        }

        catch (Exception ex) {
            return renderError("创建任务失败");
        }
    }

    @ResponseBody
    @RequestMapping("/auditdp")
    public Object auditdeletepara(@RequestParam("mainid")String mainid,@RequestParam("taskid") String taskid){
        String userName= (String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user");
        HashMap<String,Object> params= new HashMap<>();
        params.put("mainid",mainid);
        params.put("taskid",taskid);
        params.put("userName",userName);
        try {
            params.put("approverTime", DateUtil.getCurrentDateStr());
        }catch (Exception e){
            return renderError("错误");
        }
        taskService.deleteParaAudit(params);
        return renderSuccess();
    }

    @ResponseBody
    @RequestMapping("/auditdk")
    public Object auditdeleteknow(@RequestParam("mainid")String mainid,@RequestParam("taskid") String taskid){
        String userName= (String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user");
        HashMap<String,Object> params= new HashMap<>();
        params.put("mainid",mainid);
        params.put("taskid",taskid);
        params.put("userName",userName);
        try {
            params.put("approverTime", DateUtil.getCurrentDateStr());
        }catch (Exception e){
            return renderError("错误");
        }
        taskService.deleteKnowAudit(params);
        return renderSuccess();
    }

}
