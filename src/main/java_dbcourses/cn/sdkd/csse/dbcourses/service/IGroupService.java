package cn.sdkd.csse.dbcourses.service;

import cn.sdkd.csse.dbcourses.entity.Group;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface IGroupService extends IService<Group>{
     public List<Group> selectAllGroup();
}
