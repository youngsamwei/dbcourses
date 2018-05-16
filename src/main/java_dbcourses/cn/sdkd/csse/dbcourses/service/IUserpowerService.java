package cn.sdkd.csse.dbcourses.service;

import cn.sdkd.csse.dbcourses.entity.UserPower;
import com.baomidou.mybatisplus.service.IService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface IUserpowerService extends IService<UserPower> {
    public boolean insertPower(List<UserPower> list);
    public boolean deleteUserpower(String powerCode,String idlist);
}
