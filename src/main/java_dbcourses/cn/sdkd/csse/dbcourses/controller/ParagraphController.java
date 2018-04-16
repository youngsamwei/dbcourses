package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.entity.Paragraph;

import cn.sdkd.csse.dbcourses.service.IParagraphService;
import cn.sdkd.csse.dbcourses.utils.DateUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2018/1/6.
 */
@Controller
@RequestMapping("/paragraph")
public class ParagraphController extends BaseController {
  @Resource
  IParagraphService paragraphService;
  private static final long serialVersionUID = 1L;
  private static final Logger log = Logger.getLogger(ParagraphController.class);// 日志文件

  /*查询指定knowledgepointId的paragraph*/
  @ResponseBody
  @RequestMapping("/list")
  public List<Paragraph> list(Paragraph paragraph) {
    EntityWrapper ew = new EntityWrapper();
    ew.eq("knowledgepointId", paragraph.getKnowledgepointId())
    .orderBy("paragraphOrder");
    List<Paragraph> ls = paragraphService.selectList(ew);
    return ls;
  }

  @RequestMapping("/add")
  @ResponseBody
  public Object add(@Valid Paragraph paragraph) {

    try {
      paragraph.setParagraphCreateDate(DateUtil.getCurrentDateStr());
      paragraphService.insert(paragraph);
      return renderSuccess("添加成功！");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return this.renderError(e.getLocalizedMessage());
    }
  }

  @RequestMapping("/edit")
  @ResponseBody
  public Object edit(@Valid Paragraph paragraph) {
    paragraphService.updateAllColumnById(paragraph);
    return renderSuccess("编辑成功！");
  }

  @RequestMapping("/delete")
  @ResponseBody
  public Object delete(@Valid Paragraph paragraph) {
    paragraphService.deleteById(paragraph);
    return renderSuccess("删除成功！");
  }

  @RequestMapping("/sort")
  @ResponseBody
  public Object sort(@Valid Paragraph paragraph) {
      paragraphService.sort(paragraph);
      return renderSuccess("添加成功！");
  }
}
