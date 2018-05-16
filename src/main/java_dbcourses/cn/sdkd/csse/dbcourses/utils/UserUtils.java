package cn.sdkd.csse.dbcourses.utils;

public class UserUtils {

    public static int[] spiltId(String idlist ) {
        StringBuilder id = new StringBuilder(idlist);
        String ids[] = id.substring(1, id.length() - 1).split(",");
        int[] list = new int[ids.length];
        int index = 0;
        for (String arr : ids) {
            list[index] = Integer.parseInt(arr);
            index++;
        }
        return list;
    }
}
