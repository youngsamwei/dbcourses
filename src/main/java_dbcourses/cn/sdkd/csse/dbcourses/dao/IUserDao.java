package cn.sdkd.csse.dbcourses.dao;

import cn.sdkd.csse.dbcourses.entity.User;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

public interface IUserDao extends BaseMapper<User> {

    public List<User> selectAllByName (Map<String, Object> params);
    public User selectPassword(String userName);
    public List<User> selectAll (Map<String,Object> params);
    public Integer selectCount (Map<String,Object> params);
    public boolean deleteUsersBatch(int[] idlist);
    public User selectUserById(int userId);
    public boolean updateUser(Map params);
    public List<User> selectByUserpower(String powerCode);
    public boolean insertUser(Map user);
    public List<User> selectChildren(String usergroup,int[] ids);
    public String selectadminPower(String userName);
}
