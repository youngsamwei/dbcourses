package cn.sdkd.csse.dbcourses.service;

import cn.sdkd.csse.dbcourses.entity.Task;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;

public interface ITaskService extends IService<Task> {
    public List<Task> getTasklist();
}
