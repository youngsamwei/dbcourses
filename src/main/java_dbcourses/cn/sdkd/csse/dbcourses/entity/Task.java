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
    private String approverTime;

    public String getApproverTime() {
        return approverTime;
    }


    public void setApproverTime(String approverTime) {
        this.approverTime = approverTime;
    }

    public int getId() {
        return id;
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
        if(createTime!=null) {
            sb.append(",\"createTime\":\"")
                    .append(createTime.replace(".0","")).append('\"');
        }
        if(approver!=null) {
            sb.append(",\"approver\":\"")
                    .append(approver).append('\"');
        }
        sb.append(",\"submitter\":\"")
                .append(submitter).append('\"');

        if(approverTime!=null) {
            sb.append(",\"approverTime\":\"")
                    .append(approverTime.replace(".0","")).append('\"');
        }
        sb.append('}');
        return sb.toString();
    }

}
