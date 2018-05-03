package cn.sdkd.csse.dbcourses.service.impl;


import cn.sdkd.csse.dbcourses.dao.IUserDao;
import cn.sdkd.csse.dbcourses.entity.User;
import cn.sdkd.csse.dbcourses.service.IUserService;
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
        return this.baseMapper.selectPassword(username);
    }

    @Override
    public List<User> selectAll(Map<String,Object> params) {
        return this.baseMapper.selectAll(params);
    }

    @Override
    public boolean deleteUsersBatch(String idlist) {
        StringBuilder id =new StringBuilder(idlist);
        String ids[] =id.substring(1,id.length()-1).split(",");
        int[] list =new int[ids.length];
        int index =0;
        for (String arr:ids) {
            list[index]= Integer.parseInt(arr);
            index++;
        }
        System.out.println(list);
        return this.baseMapper.deleteUsersBatch(list);
    }

    public User selectUserById(int userId){return this.baseMapper.selectUserById(userId);}

    public Integer selectCount(Map<String,Object> params){return this.baseMapper.selectCount(params);}
    public boolean updateUser(Map params){return this.baseMapper.updateUser(params);}
}
