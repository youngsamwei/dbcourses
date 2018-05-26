package cn.sdkd.csse.dbcourses.dao;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2018/1/6.
 */
public interface IParagraphDao extends BaseMapper<Paragraph> {
  public int updateParagraphOrder(Paragraph paragraph);

  public int sortUp1(Paragraph paragraph);
  public int sortUp2(Paragraph paragraph);
  public int sortUp3(Paragraph paragraph);
  public int sortUp4(Paragraph paragraph);
  public int sortDown1(Paragraph paragraph);
  public int sortDown2(Paragraph paragraph);
  public int sortDown3(Paragraph paragraph);
  public boolean insertATaskP(Map params);
  public boolean insertPara(Paragraph paragraph);
  public String  selectPkname(int id);
  public int subParagraphOrder(Paragraph paragraph);

  public List getParagraphsByKid(Integer kid);
}
