package cn.sdkd.csse.dbcourses.weixin.service;

import cn.sdkd.csse.dbcourses.weixin.dao.WxChatDao;
import cn.sdkd.csse.dbcourses.weixin.dto.WxUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @ClassName WxChatService
 * @author ZHANGDAN
 * @date 2018年5月14日
 * @Description TODO（微信公共方法）
 */

@Service
public class WxChatService {


    //查询方法  微信有没有绑定 appid微信的唯一标示符
    public String getSel(String appid){
        String sql = "SELECT * FROM user WHERE OPENID=? ";
        WxChatDao wxChatDao = new WxChatDao();
        return wxChatDao.select(sql,new Object[]{appid});
    }
    //修改
    public boolean getUp(String openid,String acc,String pass){
        String sql = "UPDATE user SET OPENID =? WHERE USERNAME =? AND PASSWORD =? ";
        WxChatDao wxChatDao = new WxChatDao();
        return wxChatDao.update(sql, new Object[]{openid,acc,pass});
    }

    //查询方法
    public WxUser getAccount(String userName){
        String sql = "SELECT * FROM user WHERE USERNAME=? ";
        WxChatDao wxChatDao = new WxChatDao();
        return wxChatDao.selectAccountPasswd(sql,new Object[]{userName});
    }
    public WxUser getAccountByOpenid(String opid)
    {
        String sql = "SELECT * FROM user WHERE openid=? ";
        WxChatDao wxChatDao = new WxChatDao();
        return wxChatDao.selectUser(sql,new Object[]{opid});
    }
}
