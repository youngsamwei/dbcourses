package cn.sdkd.csse.dbcourses.service.impl;

import cn.sdkd.csse.dbcourses.dao.IResourceDao;
import cn.sdkd.csse.dbcourses.entity.Resource;
import cn.sdkd.csse.dbcourses.service.IResouceService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ResourceServiceImpl extends ServiceImpl<IResourceDao,Resource> implements IResouceService{
    @Override
    public List<Resource> selectAllResource(int id) {
        return this.baseMapper.selectAllResource(id);
    }
}
