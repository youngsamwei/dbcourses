package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.IParagraphDao;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.service.IParagraphService;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * Created by Sam on 2018/1/7.
 */
@Service
public class ParagraphServiceImpl extends ServiceImpl<IParagraphDao, Paragraph> implements IParagraphService {

  /*增加一个知识点段落，需要指定一个位置编号，并将此编号后的所有顺序编号+1。*/
  @Override
  public boolean insert(Paragraph entity){
    this.baseMapper.updateParagraphOrder(entity);
    return super.insert(entity);
  }

  /*上移知识点段落*/

  public int sort(Paragraph entity){
    return this.baseMapper.sortUp(entity);
  }

}
