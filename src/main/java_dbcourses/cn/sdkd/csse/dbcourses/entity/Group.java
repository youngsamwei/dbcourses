package cn.sdkd.csse.dbcourses.entity;

import java.io.Serializable;

public class Group implements Serializable {
    private Integer id;
    private String name;
    private String remark;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"text\":\"")
                .append(name).append('\"');
        sb.append(",\"state\":\"")
                .append("closed").append('\"');
        sb.append(",\"remark\":\"")
                .append(remark).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
