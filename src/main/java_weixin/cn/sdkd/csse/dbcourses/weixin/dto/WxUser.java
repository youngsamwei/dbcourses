package cn.sdkd.csse.dbcourses.weixin.dto;

import java.io.Serializable;
/**
 *
 * @ClassName WxUser
 * @author ZHANGDAN
 * @date 2018年5月14日
 * @Description TODO
 */

public class WxUser implements Serializable {
    private Integer id;
    private String userName;
    private String passWord;
    private String nickName;
    private String openid;

    public Integer getId() {
        return id;
    }

    public String getAccount() { return userName; }

    public String getPassword() { return passWord; }

    public String getNickname() { return nickName; }

    public String getOpenid() {
        return openid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccount(String userName) {
        this.userName = userName;
    }

    public void setPassword(String passWord) {
        this.passWord = passWord;
    }

    public void setNickname(String nickname) {
        this.nickName = nickName;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
