package cn.sdkd.csse.dbcourses.service.impl;


import cn.sdkd.csse.dbcourses.dao.IUserDao;
import cn.sdkd.csse.dbcourses.entity.User;
import cn.sdkd.csse.dbcourses.service.IUserService;
import cn.sdkd.csse.dbcourses.utils.UserUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<IUserDao, User> implements IUserService {

    public List<User> selectAllByName(Map<String, Object> params) {
        return this.baseMapper.selectAllByName(params);
    }

    @Override
    public User selectPassword(String username) {
        User user= this.baseMapper.selectPassword(username);
        String powerCode =this.baseMapper.selectadminPower(username);
        if(powerCode.equals("9999999"))
        {
            user.setPower("9");
        }
        return user;
    }

    @Override
    public List<User> selectAll(Map<String,Object> params) {
        return this.baseMapper.selectAll(params);
    }

    @Override
    public boolean deleteUsersBatch(String idlist) {
        int[] list =UserUtils.spiltId(idlist);
        System.out.println(list);
        return this.baseMapper.deleteUsersBatch(list);
    }

    public User selectUserById(int userId){return this.baseMapper.selectUserById(userId);}

    public Integer selectCount(Map<String,Object> params){return this.baseMapper.selectCount(params);}
    public boolean updateUser(Map params){return this.baseMapper.updateUser(params);}
    public boolean insertUser(Map user){return this.baseMapper.insertUser(user);}
    public List<User> selectByUserpower(String powerCode){return this.baseMapper.selectByUserpower(powerCode);}
    public List<User> selectChildren(String usergroup,int[] ids){return  this.baseMapper.selectChildren(usergroup,ids);}
}
