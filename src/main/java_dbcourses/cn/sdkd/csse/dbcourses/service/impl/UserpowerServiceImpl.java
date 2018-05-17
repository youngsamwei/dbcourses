package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.IUserpowerDao;
import cn.sdkd.csse.dbcourses.entity.UserPower;
import cn.sdkd.csse.dbcourses.service.IUserService;
import cn.sdkd.csse.dbcourses.service.IUserpowerService;
import cn.sdkd.csse.dbcourses.utils.UserUtils;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserpowerServiceImpl extends ServiceImpl<IUserpowerDao,UserPower> implements IUserpowerService {
    public boolean insertPower(List<UserPower> list)
    {
        return this.baseMapper.insertPower(list);
    }
    public boolean deleteUserpower(String powerCode,String idlist)
    {
       int ids[] =UserUtils.spiltId(idlist);
        return this.baseMapper.deleteUserpower(powerCode,ids);
    }
    public String[] selectuserPower(Map params){
        return this.baseMapper.selectuserPower(params);
    }

}
