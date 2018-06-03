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

    public List<Task> getTasklistknow(Map params) {
        return this.baseMapper.selectAllTaskknow(params);
    }
    public List<Task> getTasklistpara(Map params) {
        return this.baseMapper.selectAllTaskpara(params);
    }
    public boolean updateTaskKnow(Map ids){
        boolean flag=false;
        flag=(this.baseMapper.updateATask(ids)&&this.baseMapper.updateKnow(ids));
        return flag;
    }
    public boolean updateParaEdit(Map params){
        return this.baseMapper.updateParaEdit(params);
    }
    public boolean updateTaskPara(Map params)
    {
        boolean flag=false;
        flag=(this.baseMapper.updateATask(params)&&this.baseMapper.updatePara(params));
        return flag;
    }
    public boolean deleteParaAudit(Map params){
        boolean flag=false;
        flag=(this.baseMapper.deletePbyid(params)&&this.baseMapper.updateATask(params));
        return flag;
    }
    public boolean deleteKnowAudit(Map params){
        boolean flag=false;
        flag=(this.baseMapper.deleteKbyid(params)&&this.baseMapper.updateATask(params));
        return flag;
    }
    public Integer selectTaskCount(Map params)
    {
        return  this.baseMapper.selectTaskCount(params);
    };
}
