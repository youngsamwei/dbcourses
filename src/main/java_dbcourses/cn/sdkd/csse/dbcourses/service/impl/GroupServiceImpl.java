package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.IGroupDao;
import cn.sdkd.csse.dbcourses.entity.Group;
import cn.sdkd.csse.dbcourses.service.IGroupService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupServiceImpl extends ServiceImpl<IGroupDao,Group> implements IGroupService{
    @Override
    public List<Group> selectAllGroup() {
        return this.baseMapper.selectAllGroup();
    }
}
