mybatis的基本使用方法
1 定义实体类,例如Article
2 定义dao接口，例如ArticleDao，定义访问数据库的方法，例如findArticles(Map<String, Object> map);
3 定义mapper，使用sql实现ArticleDao中定义的方法例如findArticles，
也可以实现delete，update，insert等操作。


然后再使用service封装。
