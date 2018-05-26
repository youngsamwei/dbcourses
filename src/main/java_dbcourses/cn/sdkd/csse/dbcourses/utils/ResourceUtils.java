package cn.sdkd.csse.dbcourses.utils;

import cn.sdkd.csse.dbcourses.entity.Resource;

import java.util.List;

public class ResourceUtils {
    public static String getResouceList(List<Resource> rsl){
        final  StringBuilder sb =new StringBuilder();
        Resource rs =new Resource();
        rs.setParent(0);
        sb.append("[");
        sb.append("{}]");
        return sb.toString().replace(",{}","");
    }
}
