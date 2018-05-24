package cn.sdkd.csse.dbcourses.vo;

import cn.sdkd.csse.dbcourses.entity.Paragraph;

import java.util.List;

public class KnowledegointVo {

    private Integer id;//主键
    private String knowledgepointName;//文章标题
    private String knowledgepointCreateDate;//创建日期
    private String addName;//添加者
    private List<Paragraph> paragraphlist;
    private Integer page;//页码

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public List<Paragraph> getParagraphlist() {
    return paragraphlist;
  }

  public void setParagraphlist(List<Paragraph> paragraphlist) {
    this.paragraphlist = paragraphlist;
  }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getKnowledgepointName() {
        return knowledgepointName;
    }

    public void setKnowledgepointName(String knowledgepointName) {
        this.knowledgepointName = knowledgepointName;
    }

    public String getKnowledgepointCreateDate() {
        return knowledgepointCreateDate;
    }

    public void setKnowledgepointCreateDate(String knowledgepointCreateDate) {
        this.knowledgepointCreateDate = knowledgepointCreateDate;
    }

    public String getAddName() {
        return addName;
    }

    public void setAddName(String addName) {
        this.addName = addName;
    }
}
