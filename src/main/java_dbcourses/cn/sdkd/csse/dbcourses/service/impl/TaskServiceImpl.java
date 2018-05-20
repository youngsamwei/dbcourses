package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.ITaskDao;
import cn.sdkd.csse.dbcourses.entity.Task;
import cn.sdkd.csse.dbcourses.service.ITaskService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TaskServiceImpl extends ServiceImpl<ITaskDao,Task>  implements ITaskService {
    @Override
    public List<Task> getTasklist() {
        return this.baseMapper.selectAllTask();
    }
    public boolean updateTaskKnow(Map ids){
        boolean flag=false;
        flag=(this.baseMapper.updateTask(ids)&&this.baseMapper.updateKnow(ids));
        return flag;
    }
    public boolean updateParaEdit(Map params){
        return this.baseMapper.updateParaEdit(params);
    }
    public boolean updateTaskPara(Map params)
    {
        boolean flag=false;
        flag=(this.baseMapper.updateTask(params)&&this.baseMapper.updatePara(params));
        return flag;
    }
}
