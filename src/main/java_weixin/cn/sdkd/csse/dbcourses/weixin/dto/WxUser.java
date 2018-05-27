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
    private String account;
    private String password;
    private String nickname;
    private String openid;

    public Integer getId() {
        return id;
    }

    public String getAccount() { return account; }

    public String getPassword() { return password; }

    public String getNickname() { return nickname; }

    public String getOpenid() {
        return openid;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
