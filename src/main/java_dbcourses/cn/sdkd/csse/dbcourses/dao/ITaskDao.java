package cn.sdkd.csse.dbcourses.dao;

import cn.sdkd.csse.dbcourses.entity.Task;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface ITaskDao extends BaseMapper<Task>{
    public List<Task>  selectAllTask();
    public boolean updateTask(Map ids);
    public boolean updateKnow(Map ids);
    public boolean updateParaEdit(Map params);
    public boolean updatePara(Map params);
}

