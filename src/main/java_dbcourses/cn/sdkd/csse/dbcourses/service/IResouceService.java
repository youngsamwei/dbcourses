package cn.sdkd.csse.dbcourses.service;

import cn.sdkd.csse.dbcourses.entity.Resource;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface IResouceService extends IService<Resource> {

    public List<Resource> selectAllResource(int id);

}
