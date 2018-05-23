package cn.sdkd.csse.dbcourses.entity;

import java.io.Serializable;

/**
 * Created by Sam on 2018/1/6.
 */
public class Knowledgepoint  implements Serializable {
  private Integer id;//主键
  private String knowledgepointName;//文章标题
  private String knowledgepointCreateDate;//创建日期
  private String addName;//添加者
  private Integer xiangguandu;//临时记录相关度

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }
  public Integer getXiangguandu(){ return xiangguandu; }
  public void setXiangguandu(int xiangguandu){ this.xiangguandu = xiangguandu; }

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

  public void setXiangguandu(Integer xiangguandu) {
    this.xiangguandu = xiangguandu;
  }

  public String getAddName() {
    return addName;
  }

  public void setAddName(String addName) {
    this.addName = addName;
  }
}
