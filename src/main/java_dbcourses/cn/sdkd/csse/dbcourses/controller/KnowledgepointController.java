package cn.sdkd.csse.dbcourses.controller;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import cn.sdkd.csse.dbcourses.service.IParagraphService;
import cn.sdkd.csse.dbcourses.utils.DateUtil;
import cn.sdkd.csse.dbcourses.vo.KnowledegointVo;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.log4j.Logger;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map;
import java.util.*;
import cn.sdkd.csse.dbcourses.utils.solr;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


/**
 * @author weihongwei 20180304
 * @author
 * @author
 *
 * Created by Sam on 2018/1/6.
 */
@Controller
@RequestMapping("/knowledgepoint")
public class KnowledgepointController extends BaseController {

  @Resource
  private IKnowledgepointService knowledgepointService;
  @Resource
  private IParagraphService iParagraphService;

  private static final long serialVersionUID = 1L;
  private static final Logger log = Logger.getLogger(KnowledgepointController.class);// 日志文件

  /**
   * @author
   * @param knowledgepoint
   * @return
   */
  @ResponseBody
  @RequestMapping("/list")
  public List<KnowledegointVo> list(Knowledgepoint knowledgepoint, HttpServletRequest request) {
    EntityWrapper ew = new EntityWrapper();
//    knowledgepoint.setKnowledgepointName("数据");
//    ew.like("knowledgepointName", MessageFormat.format("%{0}%", knowledgepoint.getKnowledgepointName().toUpperCase()));
    String pageC = request.getParameter("page");
    int a = 1;
    if(pageC==null||Integer.parseInt(pageC)<=1){
        a = 1;
    }else{
      a = Integer.parseInt(pageC);
    }
    List<Knowledgepoint> ls =  new ArrayList<>();
    if (knowledgepoint.getId() != null) {
      ew.eq("id", knowledgepoint.getId());
      ew.where("status=1");
      ls = knowledgepointService.selectList(ew);
    } else if (knowledgepoint.getKnowledgepointName() != null){
      ew.like("knowledgepointName", MessageFormat.format("%{0}%", knowledgepoint.getKnowledgepointName().toUpperCase()));
      //这里可以动态设置每页显示知识点的条数；
      ew.where("status=1");
      Page<Knowledgepoint> page = new Page<>(a,10);
      Page<Map<String, Object>> map = knowledgepointService.selectMapsPage(page,ew);
      List list = map.getRecords();
      for(int i= 0;i<list.size();i++) {
        HashMap<String, Object> knowMap = (HashMap<String, Object>) list.get(i);
        Knowledgepoint kw = new Knowledgepoint();
        kw.setId(Integer.valueOf(knowMap.get("id").toString()));
        kw.setKnowledgepointName(knowMap.get("knowledgepointName").toString());
        kw.setAddName(knowMap.get("addName").toString());
        kw.setKnowledgepointCreateDate(knowMap.get("knowledgepointCreateDate").toString());
        ls.add(kw);
      }
    }else{
      /*错误处理*/

    }
    return copyKnowledgepiintListToVo(ls,a);
  }

    @ResponseBody
    @RequestMapping("/list1")
    public List<Knowledgepoint> list1(Knowledgepoint knowledgepoint) {
//    EntityWrapper ew = new EntityWrapper();
//    if (knowledgepoint.getId() != null) {
//      ew.eq("id", knowledgepoint.getId());
//    } else if (knowledgepoint.getKnowledgepointName() != null){
//      ew.like("knowledgepointName", MessageFormat.format("%{0}%", knowledgepoint.getKnowledgepointName()));
//    }else{
//      /*错误处理*/
//
//    }
//    List<Knowledgepoint> ls = knowledgepointService.selectList(ew);
//    System.out.println("xiangguan");
//    return ls;
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("knowledgepointName", knowledgepoint.getKnowledgepointName());
        List<Knowledgepoint> ls = knowledgepointService.selectByName(params);
        //System.out.println("tianjia");
        return ls;
    }


//    @ResponseBody
//    @RequestMapping("/ljxlist")
//    public List<Knowledgepoint> list(Knowledgepoint knowledgepoint) {
//        EntityWrapper ew = new EntityWrapper();
//        ew.eq("status","1");
//        if (knowledgepoint.getId() != null) {
//            ew.eq("id", knowledgepoint.getId());
//        } else if (knowledgepoint.getKnowledgepointName() != null){
//            ew.like("knowledgepointName", MessageFormat.format("%{0}%", knowledgepoint.getKnowledgepointName()));
//            ew.where("id!=1");
//        }else{
//      /*错误处理*/
//        }
//        List<Knowledgepoint> ls = knowledgepointService.selectList(ew);
//        return ls;
//    }
//  @RequestMapping("/add")
//  @ResponseBody
//  public Object add(Knowledgepoint knowledgepoint) {
//      try {
//          HashMap<String,Object> params=new HashMap<>();
//          String userName= (String)((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user");
//          params.put("userName",userName);
//          params.put("createTime",DateUtil.getCurrentDateStr());
//          knowledgepoint.setAddName(userName);
//          knowledgepoint.setKnowledgepointCreateDate(DateUtil.getCurrentDateStr());
//          knowledgepointService.insertKnow(knowledgepoint,params);
//          return renderSuccess("添加成功");
//      } catch (Exception e) {
//          log.error(e.getMessage(), e);
//          return this.renderError(e.getLocalizedMessage());
//      }
//  }
    @RequestMapping("/add")
    @ResponseBody
    public Object add(Knowledgepoint knowledgepoint) {
        Knowledgepoint find = knowledgepointService.selectKnowledgepointByName(knowledgepoint.getKnowledgepointName());
        if (find != null) {
            return find.getId();
        } else {
            try {
                HashMap<String, Object> params = new HashMap<>();
                String userName = (String) ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().getAttribute("user");
                params.put("userName", userName);
                params.put("createTime", DateUtil.getCurrentDateStr());
                knowledgepoint.setAddName(userName);
                knowledgepoint.setKnowledgepointCreateDate(DateUtil.getCurrentDateStr());
                knowledgepointService.insertKnow(knowledgepoint, params);
                return renderSuccess("添加成功");
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

    private List<KnowledegointVo> copyKnowledgepiintListToVo(List<Knowledgepoint> ls,Integer a){
        List<KnowledegointVo> vos = null;
        if(ls!=null && ls.size()>0){
            vos = new ArrayList<>();
            for (Knowledgepoint k:ls) {
                KnowledegointVo vo = new KnowledegointVo();
                vo.setId(k.getId());
                vo.setAddName(k.getAddName());
                vo.setKnowledgepointCreateDate(k.getKnowledgepointCreateDate());
                vo.setKnowledgepointName(k.getKnowledgepointName());
                List<Paragraph> pList = iParagraphService.getParagraphsByKid(k.getId());
                vo.setParagraphlist(pList);
                vo.setPage(a);
                vos.add(vo);
            }
        }
        return vos;
    }


    @RequestMapping("/audit")
    @ResponseBody
    public Knowledgepoint getAudit(@RequestParam("id") String id) {
        Knowledgepoint knowledgepoint=new Knowledgepoint();
        knowledgepoint.setId(Integer.parseInt(id));
        return knowledgepointService.selectById(knowledgepoint);
    }
}
