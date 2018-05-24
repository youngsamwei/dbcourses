import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import com.google.common.base.Utf8;
import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.WordDictionary;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HandleText {
    public static List<String> stopWord;
    static {
        stopWord = new ArrayList<>();
        // FileInputStream i=new FileInputStream("F://stopword.txt");
        try{
            File file=new File("F://github/stopword1.txt");
            InputStreamReader i = new InputStreamReader(new FileInputStream(file),"gbk");
            BufferedReader r=new BufferedReader(i);
            String str;
            while((str=r.readLine().trim())!=null){
                stopWord.add(str);
                //System.out.println(str);
            }
            i.close();
            r.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public static String readWord(String path) {
        String s = "";
        try {
            if(path.endsWith(".doc")) {
                InputStream is = new FileInputStream(new File(path));
                WordExtractor ex = new WordExtractor(is);
                s = ex.getText();
            }else if (path.endsWith("docx")) {
                OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                s = extractor.getText();
            }else {
                System.out.println("传入的word文件不正确:"+path);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        StringBuffer bf = new StringBuffer(s);
        return s;
    }
    public static void fenci(String text) throws IOException{
        //File file=new File("F://github/fenci.txt");
        PrintWriter pw=null;
        pw=new PrintWriter("F://github/训练/fenci.txt");
        Charset charset=Charset.forName("UTF-8");
       // WordDictionary.getInstance().loadUserDict(Paths.get("F:/github/ext.txt"),charset);
        //String text="关系数据库，是建立在关系数据库模型基础上的数据库，借助于集合代数等概念和方法来处理数据库中的数据，同时也是一个被组织成一组拥有正式描述性的表格，该形式的表格作用的实质是装载着数据项的特殊收集体，这些表格中的数据能以许多不同的方式被存取或重新召集而不需要重新组织数据库表格。关系数据库的定义造成元数据的一张表格或造成表格、列、范围和约束的正式描述。每个表格（有时被称为一个关系）包含用列表示的一个或更多的数据种类。 每行包含一个唯一的数据实体，这些数据是被列定义的种类。当创造一个关系数据库的时候，你能定义数据列的可能值的范围和可能应用于那个数据值的进一步约束。而SQL语言是标准用户和应用程序到关系数据库的接口。其优势是容易扩充，且在最初的数据库创造之后，一个新的数据种类能被添加而不需要修改所有的现有应用软件。主流的关系数据库有oracle、db2、sqlserver、sybase、mysql 等。";
        //String text="是对系统中数据的详尽描述，它提供对数据库数据描述的集中管理。它的处理功能是存储和检索元数据，并且为数据库管理员提供有关的报告。对数据库设计来说，数据字典是进行详细的数据收集和数据分析所获得的主要成果。主要包括四个部分：数据项、数据结构、数据流、数据存储。";
        JiebaSegmenter s=new JiebaSegmenter();
        ArrayList<String> list=new ArrayList<String>(s.sentenceProcess(text));
        ArrayList<String> list1=new ArrayList<>();
        // System.out.println(s.process(text))
        //HashMap<String,Integer> hashMap=new HashMap<String, Integer>();//用于统计词频
        Pattern patPunc = Pattern.compile("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？·]");//正则表达
        for(int i=0;i<list.size();++i){
            String c=list.get(i);
            int n=c.length();
            Matcher matcher=patPunc.matcher(c);
            if(n>=2&&!stopWord.contains(c)&&!matcher.find()){
                list1.add(c);
                pw.print(c.trim()+" ");
                System.out.println(c);
            }

            //System.out.print(c+" ");
        }
        pw.close();
//        Iterator iterator=hashMap.keySet().iterator();
//        while(iterator.hasNext()){
//            String word=(String)iterator.next();
//            System.out.println(word+"\t"+hashMap.get(word));
        //}

    }
    public static void fenci1(String text)throws IOException{
        Charset charset=Charset.forName("UTF-8");
        WordDictionary.getInstance().loadUserDict(Paths.get("F:/github/123.txt"),charset);
        //String text="关系数据库，是建立在关系数据库模型基础上的数据库，借助于集合代数等概念和方法来处理数据库中的数据，同时也是一个被组织成一组拥有正式描述性的表格，该形式的表格作用的实质是装载着数据项的特殊收集体，这些表格中的数据能以许多不同的方式被存取或重新召集而不需要重新组织数据库表格。关系数据库的定义造成元数据的一张表格或造成表格、列、范围和约束的正式描述。每个表格（有时被称为一个关系）包含用列表示的一个或更多的数据种类。 每行包含一个唯一的数据实体，这些数据是被列定义的种类。当创造一个关系数据库的时候，你能定义数据列的可能值的范围和可能应用于那个数据值的进一步约束。而SQL语言是标准用户和应用程序到关系数据库的接口。其优势是容易扩充，且在最初的数据库创造之后，一个新的数据种类能被添加而不需要修改所有的现有应用软件。主流的关系数据库有oracle、db2、sqlserver、sybase、mysql 等。";
        //String text="是对系统中数据的详尽描述，它提供对数据库数据描述的集中管理。它的处理功能是存储和检索元数据，并且为数据库管理员提供有关的报告。对数据库设计来说，数据字典是进行详细的数据收集和数据分析所获得的主要成果。主要包括四个部分：数据项、数据结构、数据流、数据存储。";
        JiebaSegmenter s=new JiebaSegmenter();
        ArrayList<String> list=new ArrayList<String>(s.sentenceProcess(text));
        // System.out.println(s.process(text))
        Pattern patPunc = Pattern.compile("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？·]");//正则表达

        HashMap<String,Integer> hashMap=new HashMap<String, Integer>();//用于统计词频
        for(int i=0;i<list.size();++i){
            String c=list.get(i);
            Matcher matcher=patPunc.matcher(c);
            int n=c.length();
            if(n>=2&&!stopWord.contains(c)&&!matcher.find()){
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
        List<Map.Entry<String,Integer>> list1=new ArrayList<Map.Entry<String,Integer>>(hashMap.entrySet());
        Collections.sort(list1, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });//排序
        for(Map.Entry<String,Integer> mapping:list1){
            System.out.println(mapping.getKey()+"\t"+mapping.getValue());
        }

    }
    public static void main(String[] args)throws IOException{
        String s=readWord("F://github/训练/yuliao.docx");

        //s.replaceAll("\\pP|\\pS","");
        //s.replaceAll("\\pP|\\pS", "");

          String s1=s.replaceAll("更多电子书教程下载请登陆http://down.zzbaike.com/ebook\n" +
                  "本站提供的电子书教程均为网上搜集，如果该教程涉及或侵害到您的版权请联系我们。","");
        System.out.println(s1);
        fenci(s1);
        //fenci1(s1);
//        String string="数据库系统的另一个重要发展趋势是包容多媒体数据。所谓“多媒体”,指的是表示某种信号的信息。通常的多媒体数据形式包括各种编码的视频、音频、雷达信号、卫星图象以及文本或图形。这些形式的共同之处在于它们比以往形式的数据——整型、定长字符串等等——在容量上大得多,并且其容量的变化范围也大得多。多媒体数据的存储促使DBMS以几种方式扩展。比如,在多媒体数据上进行的操作与适用于传统数据形式的简单操作不同。因此,虽然我们可以通过比较每个结余和实数";
//        System.out.println(string.replaceAll("\\pP|\\pS", ""));
    }
}
