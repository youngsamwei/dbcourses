package cn.sdkd.csse.dbcourses.utils;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;
import java.nio.file.Paths;
import java.util.ArrayList;


public class Add_lianjie {
    public static String rm_html(String paragraph){
        Document doc=Jsoup.parse(paragraph);
        String text=doc.body().text();
        return text;
    }
    public static ArrayList<String> fenci(String paragraph){
        WordDictionary.getInstance().init(Paths.get("F:/github/计算机专业词库.scel"));//skipwords
        JiebaSegmenter s=new JiebaSegmenter();
        ArrayList<String> list=new ArrayList<String>(s.sentenceProcess(paragraph));
        return list;
        // System.out.println(s.process(text))
            //System.out.print(c+" ");
    }
}
