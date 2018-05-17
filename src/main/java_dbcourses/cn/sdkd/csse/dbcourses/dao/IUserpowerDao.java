package cn.sdkd.csse.dbcourses.dao;

import cn.sdkd.csse.dbcourses.entity.UserPower;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface IUserpowerDao extends BaseMapper<UserPower>{
    public boolean insertPower(List<UserPower> list);
    public boolean deleteUserpower(String powerCode,int[] idlist);
    public String[] selectuserPower(Map params);
}
