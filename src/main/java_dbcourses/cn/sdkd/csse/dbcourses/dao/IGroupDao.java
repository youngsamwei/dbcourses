package cn.sdkd.csse.dbcourses.dao;

import cn.sdkd.csse.dbcourses.entity.Group;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface IGroupDao extends BaseMapper<Group> {
       public List<Group> selectAllGroup();
       public List<Group> selectSomeGroup();
       public boolean insertuserGroup(String name);
}
