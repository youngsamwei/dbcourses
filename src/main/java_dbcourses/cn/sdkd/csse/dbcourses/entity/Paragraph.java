package cn.sdkd.csse.dbcourses.entity;

import java.io.Serializable;
import org.apache.solr.client.solrj.beans.Field;

/**
 * Created by Sam on 2018/1/6.
 */
public class Paragraph  implements Serializable {
  private int id;//主键
  private Integer knowledgepointId;//所属的知识点的编号
  private Integer paragraphOrder; //段落顺序编号
  private String paragraphTitle;//段落标题

  /**
   * created by weihongwei 2018/5/14
   * DO:为solr添加Field paragraphContent，id和knowledgepointName在knowledgepoint实体中
   * 仅将段落内容用于检索
   */
  @Field("paragraphContent")
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

  @Override
  public String toString() {
    return "paragraph [paragraphContent=" + paragraphContent + "]";
  }
}
