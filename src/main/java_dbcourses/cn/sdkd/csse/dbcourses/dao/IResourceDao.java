package cn.sdkd.csse.dbcourses.dao;

import cn.sdkd.csse.dbcourses.entity.Resource;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface IResourceDao extends BaseMapper<Resource> {
    public List<Resource> selectAllResource(int id);
}
