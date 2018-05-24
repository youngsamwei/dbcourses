package cn.sdkd.csse.dbcourses.service;

import cn.sdkd.csse.dbcourses.entity.Task;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;

public interface ITaskService extends IService<Task> {
    public List<Task> getTasklist();
    public List<Task> getTasklistknow(Map params);
    public List<Task> getTasklistpara(Map params);
    public boolean updateTaskKnow(Map ids);
    public boolean updateParaEdit(Map params);
    public boolean updateTaskPara(Map params);
    public boolean deleteParaAudit(Map params);
    public boolean deleteKnowAudit(Map params);
}
