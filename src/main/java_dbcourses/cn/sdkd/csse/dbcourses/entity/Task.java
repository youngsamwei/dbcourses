package cn.sdkd.csse.dbcourses.entity;

import java.io.Serializable;

public class Task implements Serializable {
    private int id;
    private int mainid;
    private String type;
    private String content;
    private String status;
    private String createTime;
    private String approver;
    private String submitter;

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"mainid\":")
                .append(mainid);
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"content\":\"")
                .append(content).append('\"');
        sb.append(",\"status\":\"")
                .append(status).append('\"');
        sb.append(",\"createTime\":\"")
                .append(createTime).append('\"');
        sb.append(",\"approver\":\"")
                .append(approver).append('\"');
        sb.append(",\"submitter\":\"")
                .append(submitter).append('\"');
        sb.append('}');
        return sb.toString();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMainid() {
        return mainid;
    }

    public void setMainid(int mainid) {
        this.mainid = mainid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getApprover() {
        return approver;
    }

    public void setApprover(String approver) {
        this.approver = approver;
    }

    public String getSubmitter() {
        return submitter;
    }

    public void setSubmitter(String submitter) {
        this.submitter = submitter;
    }

}
