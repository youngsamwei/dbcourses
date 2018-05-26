package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.entity.Paragraph;

import cn.sdkd.csse.dbcourses.service.IParagraphService;
import cn.sdkd.csse.dbcourses.utils.DateUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
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
    ew.eq("knowledgepointId", paragraph.getKnowledgepointId()).where("status=1")
    .orderBy("paragraphOrder");
    List<Paragraph> ls = paragraphService.selectList(ew);
    return ls;
  }

  @RequestMapping("/add")
  @ResponseBody
  public Object add(@Valid Paragraph paragraph) {
    try {
      HashMap<String,Object> params=new HashMap<>();
      String userName= (String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user");
      params.put("userName",userName);
      params.put("createTime",DateUtil.getCurrentDateStr());
      paragraph.setParagraphCreateDate(DateUtil.getCurrentDateStr());
      paragraphService.insert(paragraph,params);
      return renderSuccess("添加成功！");
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      return this.renderError(e.getLocalizedMessage());
    }
  }

  @RequestMapping("/edit")
  @ResponseBody
  public Object edit(@Valid Paragraph paragraph) {
    paragraphService.updateById(paragraph);
    return renderSuccess("编辑成功！");
  }

  @RequestMapping("/delete")
  @ResponseBody
  public Object delete(@Valid Paragraph paragraph) {
    paragraphService.deleteParagraph(paragraph);
    return renderSuccess("删除成功！");
  }

  /**
   * created by weihongwei 2018/5/15
   * 段落排序。上下移动
   * @param paragraph
   * @return
   */
  @RequestMapping("/sortup")
  @ResponseBody
  public Object sortup(@Valid Paragraph paragraph) {
    paragraphService.sortup(paragraph);
    return renderSuccess("添加成功！");
  }
  @RequestMapping("/sortdown")
  @ResponseBody
  public Object sortdown(@Valid Paragraph paragraph) {
    paragraphService.sortdown(paragraph);
    return renderSuccess("添加成功！");
  }
  @RequestMapping("/selectbyid")
  @ResponseBody
  public Object selectByid(@RequestParam("mainid")String id)
  {
    System.out.println("1111111111111111111111    "+id);
    Paragraph paragraph=new Paragraph();
    paragraph.setId(Integer.parseInt(id));
    paragraph= paragraphService.selectById(paragraph);
    String title=paragraphService.selectPkname(Integer.parseInt(id));
    System.out.println("``````````````````"+title);
    paragraph.setParagraphTitle(title);
    return paragraph;
  }

}
