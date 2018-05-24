package cn.sdkd.csse.dbcourses.service;

import cn.sdkd.csse.dbcourses.entity.Paragraph;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

/**
 * Created by Sam on 2018/1/6.
 */
public interface IParagraphService extends IService<Paragraph> {
    /*删除一个知识点段落，需要指定一个位置编号，并将此编号后的所有顺序编号-1。*/
    public boolean deleteParagraph(Paragraph entity);

    public void sortup(Paragraph entity);
    public void sortdown(Paragraph entity);

    public List<Paragraph> getParagraphsByKid(Integer kid);

}
