package cn.sdkd.csse.dbcourses.entity;

import java.io.Serializable;

public class Resource implements Serializable {
    private int id;
    private String number;
    private String text;
    private int parent;
    private String state;
    private String remark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
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
                .append(text).append('\"');
        sb.append(",\"parent\":")
                .append(parent);
        sb.append(",\"state\":\"")
                .append(state).append('\"');
        sb.append(",\"remark\":\"")
                .append(remark).append('\"');
        sb.append(",\"attributes\":{");
        sb.append("\"number\":\"")
                .append(number).append('\"');
        sb.append("}");
        sb.append('}');
        return sb.toString();
    }

    public String toChildren(){
        final StringBuilder sb =new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"text\":\"")
                .append(text).append('\"');

        return sb.toString();
    }
}
