package cn.sdkd.csse.dbcourses.entity;

import java.io.Serializable;
import org.apache.solr.client.solrj.beans.Field;
/**
 * Created by Sam on 2018/1/6.
 */
public class Knowledgepoint  implements Serializable {
  /**
   * created by weihongwei 2018/5/14
   * DO:为solr添加Field，包括id和knowledgepointName，paragraphContent在paragraph实体中
   */
  @Field("id")
  private Integer id;//主键
  @Field("knowledgepointName")
  private String knowledgepointName;//文章标题

  private String knowledgepointCreateDate;//创建日期
  private String addName;//添加者

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

  @Override
  public String toString() {
    return "knowledge [id=" + id + ", knowledgepointName=" + knowledgepointName + "]";
  }
}
