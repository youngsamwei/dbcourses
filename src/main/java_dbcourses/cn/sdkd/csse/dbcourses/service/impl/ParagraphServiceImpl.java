package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.IParagraphDao;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.service.IParagraphService;
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
  /*删除一个知识点段落，需要指定一个位置编号，并将此编号后的所有顺序编号-1。*/
  public boolean deleteParagraph(Paragraph entity){
      //System.out.println("service层：");
      //System.out.println("paragraphId="+ entity.getId() +"      knowledgepointId=" + entity.getKnowledgepointId() + "   paragraphOrder=" + entity.getParagraphOrder());
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
    //System.out.println("service层：");
   // System.out.println("knowledgepointId=" + entity.getKnowledgepointId() + "  paragraphOrder=" + entity.getParagraphOrder());
    this.baseMapper.sortUp1(entity);
      this.baseMapper.sortUp2(entity);
      this.baseMapper.sortUp3(entity);
      this.baseMapper.sortUp4(entity);
  }
    /* 下移知识点段落*/
    public void sortdown(Paragraph entity){
        //System.out.println("service层：");
        //System.out.println("knowledgepointId=" + entity.getKnowledgepointId() + "  paragraphOrder=" + entity.getParagraphOrder());
        this.baseMapper.sortDown1(entity);
        this.baseMapper.sortDown2(entity);
        this.baseMapper.sortDown3(entity);
    }


}
