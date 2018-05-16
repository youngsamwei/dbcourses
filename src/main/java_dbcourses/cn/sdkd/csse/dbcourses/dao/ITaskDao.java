package cn.sdkd.csse.dbcourses.dao;

import cn.sdkd.csse.dbcourses.entity.Task;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface ITaskDao extends BaseMapper<Task>{
    public List<Task>  selectAllTask();
}

