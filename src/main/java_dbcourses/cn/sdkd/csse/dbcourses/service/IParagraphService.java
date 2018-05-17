package cn.sdkd.csse.dbcourses.service;

import cn.sdkd.csse.dbcourses.entity.Paragraph;
import com.baomidou.mybatisplus.service.IService;

import java.util.Map;

/**
 * Created by Sam on 2018/1/6.
 */
public interface IParagraphService extends IService<Paragraph> {
    public boolean insert(Paragraph entity);
}
