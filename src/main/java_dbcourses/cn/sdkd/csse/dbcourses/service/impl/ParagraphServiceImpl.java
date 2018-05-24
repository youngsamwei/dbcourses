package cn.sdkd.csse.dbcourses.service.impl;
////////南宁：对前台写入的文本进行处理，去掉原有的链接，添加知识点链接
import cn.sdkd.csse.dbcourses.dao.IParagraphDao;
import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import cn.sdkd.csse.dbcourses.service.IParagraphService;
import cn.sdkd.csse.dbcourses.utils.Add_lianjie;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import cn.sdkd.csse.dbcourses.dao.IKnowledgepointDao;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.util.List;

/**
 * Created by Sam on 2018/1/7.
 */
@Service
public class ParagraphServiceImpl extends ServiceImpl<IParagraphDao, Paragraph> implements IParagraphService {
  @Resource
  private IKnowledgepointService knowledgepointService;
  /*增加一个知识点段落，需要指定一个位置编号，并将此编号后的所有顺序编号+1。*/
  public boolean insert(Paragraph entity,Map lparams){
    Integer id=entity.getKnowledgepointId();
    //System.out.println(entity.getParagraphContent()+"nn00");
    ArrayList<String> list;
    ArrayList<String> GL=new ArrayList<>();
    String text=Add_lianjie.rmhtml(entity.getParagraphContent());
    list=Add_lianjie.fenci(text);
    String text1=Add_lianjie.rmlianjie(entity.getParagraphContent());
    System.out.println(list+"nanning0");
    for(String s:list){
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("knowledgepointName", "%" + s + "%");

      List<Knowledgepoint> kps = knowledgepointService.selectTopTenByName(params);
      if (kps.size() >= 1) {//c查找成功
        Boolean flag=true;
        for(String c:list){
          if(!GL.contains(c)&&c.contains(s)&&!c.equals(s))
            flag=false;//无需添加链接
        }
        if(flag){
          for (Knowledgepoint kp : kps) {
            if(kp.getKnowledgepointName().equals(s) ){//两个判断
              System.out.println(kp.getKnowledgepointName()+"  "+s+"  "+kp.getId()+"  "+entity.getKnowledgepointId()+"  "+"tianchuan");
              text1=Add_lianjie.addlianjie(kp.getKnowledgepointName(),text1,kp.getId());
              System.out.println(kp.getKnowledgepointName()+" 123123");
              break;
            }else {
              GL.add(s);
            }
          }
        }
        else
          GL.add(s);
      }
    }
    entity.setParagraphContent(text1);
    this.baseMapper.insertPara(entity);
    int pid =entity.getId();
    System.out.println("11111111111111111111111111111"+pid);
    lparams.put("mainid",pid);
    this.baseMapper.updateParagraphOrder(entity);
    return this.baseMapper.insertATaskP(lparams);
  }
  public boolean updateById(Paragraph entity){

    Integer id=entity.getKnowledgepointId();
    System.out.println(entity.getParagraphContent()+"nn00");
    ArrayList<String> list;
    ArrayList<String> GL=new ArrayList<>();
    String text=Add_lianjie.rmhtml(entity.getParagraphContent());
    list=Add_lianjie.fenci(text);
    String text1=Add_lianjie.rmlianjie(entity.getParagraphContent());
    System.out.println(list+"nanning0");
    for(String s:list){
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("knowledgepointName", "%" + s + "%");

      List<Knowledgepoint> kps = knowledgepointService.selectTopTenByName(params);
      if (kps.size() >= 1) {
        Boolean flag=true;
        for(String c:list){
          if(!GL.contains(c)&&c.contains(s)&&!c.equals(s))
            flag=false;//无需添加链接
        }
        if(flag){
          for (Knowledgepoint kp : kps) {
            if(kp.getKnowledgepointName().equals(s)){//两个判断
              System.out.println(kp.getKnowledgepointName()+"  "+s+"  "+kp.getId()+entity.getKnowledgepointId()+"  "+"tianchuan");
              text1=Add_lianjie.addlianjie(kp.getKnowledgepointName(),text1,kp.getId());
              System.out.println(kp.getKnowledgepointName()+" 123123");
              break;
            }
            else {//没有添加链接
              GL.add(s);
            }
          }
        }
      }
      else {
        GL.add(s);
      }
    }
    entity.setParagraphContent(text1);
    return super.updateById(entity);
  }

    /*删除一个知识点段落，需要指定一个位置编号，并将此编号后的所有顺序编号-1。*/
    public boolean deleteParagraph(Paragraph entity){
        this.baseMapper.subParagraphOrder(entity);
        return super.deleteById(entity.getId());
    }




  /**
   * create by weihongwei 2018/5/15
   * 移动知识点段落。缺点是数据库update多次
   * @param entity
   */
  /*上移知识点段落*/
  public void sortup(Paragraph entity){
    this.baseMapper.sortUp1(entity);
    this.baseMapper.sortUp2(entity);
    this.baseMapper.sortUp3(entity);
    this.baseMapper.sortUp4(entity);
  }
  /* 下移知识点段落*/
  public void sortdown(Paragraph entity){
    this.baseMapper.sortDown1(entity);
    this.baseMapper.sortDown2(entity);
    this.baseMapper.sortDown3(entity);
  }

  @Override
  public List<Paragraph> getParagraphsByKid(Integer kid) {
    return this.baseMapper.getParagraphsByKid(kid);
  }

  public boolean insertPara(Paragraph entity,Map params){
    this.baseMapper.updateParagraphOrder(entity);
    this.baseMapper.insertPara(entity);
    int id =entity.getId();
    System.out.println("11111111111111111111111111111"+id);
    params.put("mainid",id);
    return this.baseMapper.insertATaskP(params);
  }
  public String selectPkname(int id)
  {
    return this.baseMapper.selectPkname(id);
  }
}
