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

/**
 * Created by Sam on 2018/1/7.
 */
@Service
public class ParagraphServiceImpl extends ServiceImpl<IParagraphDao, Paragraph> implements IParagraphService {
  @Resource
  private IKnowledgepointService knowledgepointService;
  /*增加一个知识点段落，需要指定一个位置编号，并将此编号后的所有顺序编号+1。*/
  public boolean insert(Paragraph entity){
    Integer id=entity.getKnowledgepointId();
    //System.out.println(entity.getParagraphContent()+"nn00");
    ArrayList<String> list;
    ArrayList<String> GL=new ArrayList<>();
    String text=Add_lianjie.rmhtml(entity.getParagraphContent());
    list=Add_lianjie.fenci(text);
    //System.out.println(list+"nn0");
    String text1=Add_lianjie.rmlianjie(entity.getParagraphContent());
   // System.out.println(text1+"nn1");
    System.out.println(list+"nanning0");
    for(String s:list){
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("knowledgepointName", "%" + s + "%");

      List<Knowledgepoint> kps = knowledgepointService.selectTopTenByName(params);
      //System.out.println("修改成功");
      //List<Knowledgepoint> kps = this.baseMapper.selectTopTenByName(params);
      if (kps.size() >= 1) {//c查找成功
        Boolean flag=true;
        for(String c:list){
          if(!GL.contains(c)&&c.contains(s)&&!c.equals(s))
            flag=false;//无需添加链接
        }
        if(flag){
          for (Knowledgepoint kp : kps) {
           // if(kp.getKnowledgepointName().equals(list.get(i))&&kp.getId()!=entity.getKnowledgepointId())//无法使用
            if(kp.getKnowledgepointName().equals(s) ){//两个判断
              // System.out.println("匹配成功");
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
        //System.out.println("查找成功");
      }
         //System.out.println(list+"nanning1");
    }
    entity.setParagraphContent(text1);
    this.baseMapper.updateParagraphOrder(entity);
    return super.insert(entity);
  }
  public boolean updateById(Paragraph entity){
//    Integer id=entity.getKnowledgepointId();备份
//    //System.out.println(entity.getParagraphContent()+"nn00");
//    ArrayList<String> list;
//    String text=Add_lianjie.rmhtml(entity.getParagraphContent());
//    list=Add_lianjie.fenci(text);
//    //System.out.println(list+"nn0");
//    String text1=Add_lianjie.rmlianjie(entity.getParagraphContent());
//   // System.out.println(text1+"nn1");
//    for(int i=0;i<list.size();++i) {
//      Map<String, Object> params = new HashMap<String, Object>();
//      params.put("knowledgepointName", "%" + list.get(i) + "%");
//      List<Knowledgepoint> kps = knowledgepointService.selectTopTenByName(params);
//      if (kps.size() >= 1) {
//        //System.out.println("查找成功");
//        for (Knowledgepoint kp : kps) {
//          if(kp.getKnowledgepointName().equals(list.get(i))&&kp.getId()!=entity.getKnowledgepointId()){
//            //System.out.println("匹配成功");
//            text1=Add_lianjie.addlianjie(kp.getKnowledgepointName(),text1,kp.getId());
//            break;
//          }
//        }
//      }
//    }
    Integer id=entity.getKnowledgepointId();
    System.out.println(entity.getParagraphContent()+"nn00");
    ArrayList<String> list;
    ArrayList<String> GL=new ArrayList<>();
    String text=Add_lianjie.rmhtml(entity.getParagraphContent());
    list=Add_lianjie.fenci(text);
    //System.out.println(list+"nn0");
    String text1=Add_lianjie.rmlianjie(entity.getParagraphContent());
    // System.out.println(text1+"nn1");
    System.out.println(list+"nanning0");
    for(String s:list){
      Map<String, Object> params = new HashMap<String, Object>();
      params.put("knowledgepointName", "%" + s + "%");

      List<Knowledgepoint> kps = knowledgepointService.selectTopTenByName(params);
      //System.out.println("修改成功");
      //List<Knowledgepoint> kps = this.baseMapper.selectTopTenByName(params);
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
              // System.out.println("匹配成功");
              text1=Add_lianjie.addlianjie(kp.getKnowledgepointName(),text1,kp.getId());
              System.out.println(kp.getKnowledgepointName()+" 123123");
              break;
            }
            else {//没有添加链接
              GL.add(s);
            }
          }
        }
        //System.out.println("查找成功");
      }
      else {
        GL.add(s);
      }
      //System.out.println(list+"nanning1");
    }
    entity.setParagraphContent(text1);
   // System.out.println(entity.getParagraphContent()+"nn3");
  //  System.out.println("======================================");
    return super.updateById(entity);
  }
  /*上移知识点段落*/

  public int sort(Paragraph entity){
    return this.baseMapper.sortUp(entity);
  }


}
