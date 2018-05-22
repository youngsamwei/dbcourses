package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import cn.sdkd.csse.dbcourses.utils.DateUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import org.apache.log4j.Logger;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

import cn.sdkd.csse.dbcourses.utils.solr;


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
    if (knowledgepoint.getId() != null) {
      ew.eq("id", knowledgepoint.getId());
    } else if (knowledgepoint.getKnowledgepointName() != null){
      ew.like("knowledgepointName", MessageFormat.format("%{0}%", knowledgepoint.getKnowledgepointName()));
    }else{
      /*错误处理*/
    }
    List<Knowledgepoint> ls = knowledgepointService.selectList(ew);
    return ls;
  }

  @RequestMapping("/add")
  @ResponseBody
  public Object add(Knowledgepoint knowledgepoint) {
    if (knowledgepoint.getId() != null){
      return knowledgepointService.selectById(knowledgepoint);
    }else{
      try {
        knowledgepoint.setKnowledgepointCreateDate(DateUtil.getCurrentDateStr());
        knowledgepointService.insert(knowledgepoint);
        return renderSuccess("添加成功！");

      } catch (Exception e) {
        log.error(e.getMessage(), e);
        return this.renderError(e.getLocalizedMessage());
      }
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


  /**
   * solr 内容搜索
   * created by weihongwei
   * 2018.5.7
   */
  @RequestMapping("/solr")
  @ResponseBody
  public SolrDocumentList solr(Knowledgepoint knowledgepoint) {
    solr s = new solr();
      SolrDocumentList result = null;
    try {
        result= s.querySolr(knowledgepoint.getKnowledgepointName());
    }catch (Exception e) {
    }
      return result;
  }
}
