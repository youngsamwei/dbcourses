package cn.sdkd.csse.dbcourses.entity;

import java.io.Serializable;

public class User  implements Serializable {

    private Integer userId;
    private String userName;
    private String passWord;
    private String nickName;
    private int power;
    private String remark;
    private String userGroup;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getUserGroup() {
        return userGroup;
    }

    public void setUserGroup(String userGroup) {
        this.userGroup = userGroup;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userId\":")
                .append(userId);
        sb.append(",\"userName\":\"")
                .append(userName).append('\"');
        sb.append(",\"passWord\":\"")
                .append(passWord).append('\"');
        sb.append(",\"nickName\":\"")
                .append(nickName).append('\"');
        sb.append(",\"power\":")
                .append(power);
        sb.append(",\"remark\":\"")
                .append(remark).append('\"');
        sb.append(",\"userGroup\":\"")
                .append(userGroup).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
