package cn.sdkd.csse.dbcourses.service;

import cn.sdkd.csse.dbcourses.entity.Paragraph;
import cn.sdkd.csse.dbcourses.entity.User;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;
import java.util.Map;


public interface IUserService extends IService<User> {

    public List<User> selectAllByName(Map<String, Object> params);
    public User selectPassword(String username);
    public List<User> selectAll(Map<String,Object> params);
    public List<User> selectChildren(String usergroup,int[] ids);
    public Integer selectCount(Map<String,Object> params);
    public boolean deleteUsersBatch(String idlist);
    public User selectUserById(int userId);
    public boolean updateUser(Map params);
    public List<User> selectByUserpower(String powerCode);
    public boolean insertUser(Map user);
}

