package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.IParagraphDao;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.service.IParagraphService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Sam on 2018/1/7.
 */
@Service
public class ParagraphServiceImpl extends ServiceImpl<IParagraphDao, Paragraph> implements IParagraphService {

  /*增加一个知识点段落，需要指定一个位置编号，并将此编号后的所有顺序编号+1。*/
  @Override
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
