package cn.sdkd.csse.dbcourses.utils;

public class UserUtils {


    public static String checkPower(String check ,Object power)
    {
        //7 6 5 4 3 2 1
        //4 4 4 4
        //2 2     2 2
        //1   1   1   1

        int pc=Integer.parseInt(check);
        int pn=(int)power;
        if(pc==4&&(pn>=4))
        {
            return "success";
        }
        else if(pc == 2 && (pn==2||pn==3||pn==5||pn>=7)){
            return "success";
        }
        else if(pc ==1 &&(pn !=2||pn !=4||pn !=6))
        {
            return "success";
        }

        return "error";
    }

}
