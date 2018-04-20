package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.IParagraphDao;
import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.service.IParagraphService;
import cn.sdkd.csse.dbcourses.utils.Add_lianjie;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

/**
 * Created by Sam on 2018/1/7.
 */
@Service
public class ParagraphServiceImpl extends ServiceImpl<IParagraphDao, Paragraph> implements IParagraphService {

  /*增加一个知识点段落，需要指定一个位置编号，并将此编号后的所有顺序编号+1。*/
  @Override
  public boolean insert(Paragraph entity){
   ////////南宁添加 分词、增加链接功能
//    ArrayList<String> list;
//    String text=Add_lianjie.rmhtml(entity.getParagraphContent());
//    list=Add_lianjie.fenci(text);
//    for(int i=0;i<list.size();++i){
//      System.out.println(list+"        0000000000000000000000000000000000000000000000000000000000");
//    }
    this.baseMapper.updateParagraphOrder(entity);
    return super.insert(entity);
  }
  /*上移知识点段落*/

  public int sort(Paragraph entity){
    return this.baseMapper.sortUp(entity);
  }


}
