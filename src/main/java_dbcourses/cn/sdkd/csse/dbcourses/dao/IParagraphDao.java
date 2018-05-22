package cn.sdkd.csse.dbcourses.dao;

import cn.sdkd.csse.dbcourses.entity.Paragraph;
import com.baomidou.mybatisplus.mapper.BaseMapper;

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

  public int subParagraphOrder(Paragraph paragraph);

}
