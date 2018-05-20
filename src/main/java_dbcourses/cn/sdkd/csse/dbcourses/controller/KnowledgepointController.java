package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import cn.sdkd.csse.dbcourses.utils.DateUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Sam on 2018/1/6.
 */
@Controller
@RequestMapping("/knowledgepoint")
public class KnowledgepointController extends BaseController {

  @Resource
  private IKnowledgepointService knowledgepointService;

  private static final long serialVersionUID = 1L;
  private static final Logger log = Logger.getLogger(KnowledgepointController.class);// 日志文件

  @ResponseBody
  @RequestMapping("/list")
  public List<Knowledgepoint> list(Knowledgepoint knowledgepoint) {
    EntityWrapper ew = new EntityWrapper();
    ew.eq("status","1");
    if (knowledgepoint.getId() != null) {
      ew.eq("id", knowledgepoint.getId());
    } else if (knowledgepoint.getKnowledgepointName() != null){
      ew.like("knowledgepointName", MessageFormat.format("%{0}%", knowledgepoint.getKnowledgepointName()));
      ew.where("id!=1");
    }else{
      /*错误处理*/
    }
    List<Knowledgepoint> ls = knowledgepointService.selectList(ew);
    return ls;
  }


  @RequestMapping("/add")
  @ResponseBody
  public Object add(Knowledgepoint knowledgepoint) {
    try {
      HashMap<String,Object> params=new HashMap<>();
      String userName= (String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user");
      params.put("userName",userName);
      knowledgepoint.setAddName(userName);
      knowledgepoint.setKnowledgepointCreateDate(DateUtil.getCurrentDateStr());
      knowledgepointService.insertKnow(knowledgepoint,params);
      return renderSuccess("添加成功");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return this.renderError(e.getLocalizedMessage());
    }
  }

  @RequestMapping("/edit")
  @ResponseBody
  public Object edit(Knowledgepoint knowledgepoint) {
    knowledgepointService.updateById(knowledgepoint);
    return renderSuccess("编辑成功！");
  }

  @RequestMapping("/delete")
  @ResponseBody
  public Object delete(Knowledgepoint knowledgepoint) {
    knowledgepointService.deleteById(knowledgepoint.getId());
    return renderSuccess("删除成功！");
  }

  @RequestMapping("/audit")
  @ResponseBody
  public Knowledgepoint getAudit(@RequestParam("id") String id) {
    Knowledgepoint knowledgepoint=new Knowledgepoint();
    knowledgepoint.setId(Integer.parseInt(id));
    return knowledgepointService.selectById(knowledgepoint);
  }
}
