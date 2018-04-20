import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.io.IOException;
public class JiebaTest {
    public static void main(String[] args) throws IOException{
//        WordDictionary dictADD=WordDictionary.getInstance();
//        File file= new File("F:/github/dict.txt");
//        dictADD.loadUserDict(file);
        WordDictionary.getInstance().init(Paths.get("F:/github/计算机专业词库.scel"));//skipwords
        String text="关系数据库，是建立在关系数据库模型基础上的数据库，借助于集合代数等概念和方法来处理数据库中的数据，同时也是一个被组织成一组拥有正式描述性的表格，该形式的表格作用的实质是装载着数据项的特殊收集体，这些表格中的数据能以许多不同的方式被存取或重新召集而不需要重新组织数据库表格。关系数据库的定义造成元数据的一张表格或造成表格、列、范围和约束的正式描述。每个表格（有时被称为一个关系）包含用列表示的一个或更多的数据种类。 每行包含一个唯一的数据实体，这些数据是被列定义的种类。当创造一个关系数据库的时候，你能定义数据列的可能值的范围和可能应用于那个数据值的进一步约束。而SQL语言是标准用户和应用程序到关系数据库的接口。其优势是容易扩充，且在最初的数据库创造之后，一个新的数据种类能被添加而不需要修改所有的现有应用软件。主流的关系数据库有oracle、db2、sqlserver、sybase、mysql 等。";
        //String text="是对系统中数据的详尽描述，它提供对数据库数据描述的集中管理。它的处理功能是存储和检索元数据，并且为数据库管理员提供有关的报告。对数据库设计来说，数据字典是进行详细的数据收集和数据分析所获得的主要成果。主要包括四个部分：数据项、数据结构、数据流、数据存储。";
        JiebaSegmenter s=new JiebaSegmenter();
       ArrayList<String> list=new ArrayList<String>(s.sentenceProcess(text));
       // System.out.println(s.process(text));
        int i;
        HashMap<String,Integer> hashMap=new HashMap<String, Integer>();
        for(i=0;i<list.size();++i){
            String c=list.get(i);
            int n=c.length();
            if(n>=2){
                if(!hashMap.containsKey(c)){
                    hashMap.put(c,new Integer(1));
                }
                else{
                    int k=hashMap.get(c).intValue()+1;
                    hashMap.put(c,new Integer(k));
                }
            }
            //System.out.print(c+" ");
        }
        Iterator iterator=hashMap.keySet().iterator();
        while(iterator.hasNext()){
            String word=(String)iterator.next();
            System.out.println(word+"\t"+hashMap.get(word));
        }

    }
}
