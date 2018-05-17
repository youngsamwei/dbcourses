package cn.sdkd.csse.dbcourses.dao;

import cn.sdkd.csse.dbcourses.entity.Paragraph;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.Map;

/**
 * Created by Sam on 2018/1/6.
 */
public interface IParagraphDao extends BaseMapper<Paragraph> {
  public int updateParagraphOrder(Paragraph paragraph);
  public boolean insertATask(Map params);
}
