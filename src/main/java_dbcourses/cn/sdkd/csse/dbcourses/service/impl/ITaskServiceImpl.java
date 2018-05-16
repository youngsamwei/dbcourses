package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.ITaskDao;
import cn.sdkd.csse.dbcourses.entity.Task;
import cn.sdkd.csse.dbcourses.service.ITaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ITaskServiceImpl extends ServiceImpl<ITaskDao,Task>  implements ITaskService {
    @Override
    public List<Task> getTasklist() {
        return this.baseMapper.selectAllTask();
    }
}
