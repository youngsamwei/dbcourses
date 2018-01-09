package cn.sdkd.csse.dbcourses.entity;

import java.io.Serializable;

/**
 * Created by Sam on 2018/1/6.
 */
public class Paragraph  implements Serializable {
  private int id;//主键
  private Integer knowledgepointId;//所属的知识点的编号
  private Integer paragraphOrder; //段落顺序编号
  private String paragraphTitle;//段落标题
  private String paragraphContent;//段落内容
  private String paragraphCreateDate; //创建时间
  private String addName;//添加者

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Integer getKnowledgepointId() {
    return knowledgepointId;
  }

  public void setKnowledgepointId(Integer knowledgepointId) {
    this.knowledgepointId = knowledgepointId;
  }

  public Integer getParagraphOrder() {
    return paragraphOrder;
  }

  public void setParagraphOrder(Integer paragraphOrder) {
    this.paragraphOrder = paragraphOrder;
  }

  public String getParagraphTitle() {
    return paragraphTitle;
  }

  public void setParagraphTitle(String paragraphTitle) {
    this.paragraphTitle = paragraphTitle;
  }

  public String getParagraphContent() {
    return paragraphContent;
  }

  public void setParagraphContent(String paragraphContent) {
    this.paragraphContent = paragraphContent;
  }

  public String getParagraphCreateDate() {
    return paragraphCreateDate;
  }

  public void setParagraphCreateDate(String paragraphCreateDate) {
    this.paragraphCreateDate = paragraphCreateDate;
  }

  public String getAddName() {
    return addName;
  }

  public void setAddName(String addName) {
    this.addName = addName;
  }
}
