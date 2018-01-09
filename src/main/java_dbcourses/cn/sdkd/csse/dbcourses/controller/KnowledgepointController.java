package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
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
    ew.eq("id", knowledgepoint.getId());
    List<Knowledgepoint> ls = knowledgepointService.selectList(ew);
    return ls;
  }


  @RequestMapping("/add")
  @ResponseBody
  public Object add(Knowledgepoint knowledgepoint) {
    knowledgepointService.insert(knowledgepoint);
    return renderSuccess("添加成功！");
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

}
