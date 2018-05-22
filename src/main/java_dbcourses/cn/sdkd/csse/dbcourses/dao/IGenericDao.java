package cn.sdkd.csse.dbcourses.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by Sam on 2018/1/6.
 */
public interface IGenericDao<T> {
  /**
   * 返回相应的数据集合
   *
   * @param map
   * @return
   */
  public List<T> find(Map<String, Object> map);

  /**
   * 数据数目
   *
   * @param map
   * @return
   */
  public Long getTotalCount(Map<String, Object> map);

  /**
   * 添加文章
   *
   * @return
   */
  public int insert(T o);

  /**
   * 修改文章
   *
   * @return
   */
  public int update(T o);

  /**
   * 删除
   *
   * @param id
   * @return
   */
  public int delete(int id);

  /**
   * 根据id查找
   *
   * @param id
   * @return
   */
  public T getById(int id);

}
