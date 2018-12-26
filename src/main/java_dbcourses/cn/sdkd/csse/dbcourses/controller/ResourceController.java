package cn.sdkd.csse.dbcourses.controller;


import cn.sdkd.csse.dbcourses.entity.Resource;
import cn.sdkd.csse.dbcourses.service.IResouceService;
import cn.sdkd.csse.dbcourses.utils.ResourceUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.List;

@Controller
@RequestMapping("/power")
public class ResourceController extends BaseController{
    @javax.annotation.Resource
    private IResouceService resouceService;
    @RequestMapping("/list")
    @ResponseBody
    public List<Resource> getPowerList()
    {
        int id =0;
        List<Resource> rsl = resouceService.selectAllResource(id);
        String res= ResourceUtils.getResouceList(rsl);
       // System.out.println("11111111111111"+rsl.toString());
        return rsl;
    }


    @RequestMapping("/children")
    @ResponseBody
    public String getChildren(@RequestParam("id") String rid)
    {
      //  System.out.println("111111111111111"+rid);
        int id =Integer.parseInt(rid);
        List<Resource> rsl = resouceService.selectAllResource(id);
        String res= ResourceUtils.getResouceList(rsl);
       // System.out.println("11111111111111"+rsl.toString());
        return rsl.toString();
    }

    @RequestMapping("/test")
    @ResponseBody
    public String getPowerListtext()
    {

        return "[\n" +
                "{\"id\":1,\n" +
                "\"text\":\"资源\",\n" +
                "\"attributes\":{\"number\":\"01\"}, \n" +
                "\"children\":\n" +
                "[{\n" +
                "\"id\":2,\n" +
                "\"text\":\"数据库\",\n" +
                "\"attributes\":{\"number\":\"0101\"}\n" +
                "}]}]";
    }


//     return "[{  \n" +
//             "    \"id\":1,  \n" +
//             "    \"text\":\"My Documents\",  \n" +
//             "    \"children\":[{  \n" +
//             "        \"id\":22,  \n" +
//             "        \"text\":\"Photos\",  \n" +
//             "        \"state\":\"closed\",  \n" +
//             "        \"children\":[{  \n" +
//             "            \"id\":111,  \n" +
//             "            \"text\":\"Friend\"  \n" +
//             "        },{  \n" +
//             "            \"id\":112,  \n" +
//             "            \"text\":\"Wife\"  \n" +
//             "        },{  \n" +
//             "            \"id\":113,  \n" +
//             "            \"text\":\"Company\"  \n" +
//             "        }]  \n" +
//             "    },{  \n" +
//             "        \"id\":12,  \n" +
//             "        \"text\":\"Program Files\",  \n" +
//             "        \"children\":[{  \n" +
//             "            \"id\":121,  \n" +
//             "            \"text\":\"Intel\"  \n" +
//             "        },{  \n" +
//             "            \"id\":122,  \n" +
//             "            \"text\":\"Java\",  \n" +
//             "            \"attributes\":{  \n" +
//             "                \"p1\":\"Custom Attribute1\",  \n" +
//             "                \"p2\":\"Custom Attribute2\"  \n" +
//             "            }  \n" +
//             "        },{  \n" +
//             "            \"id\":123,  \n" +
//             "            \"text\":\"Microsoft Office\"  \n" +
//             "        },{  \n" +
//             "            \"id\":124,  \n" +
//             "            \"text\":\"Games\",  \n" +
//             "            \"checked\":true  \n" +
//             "        }]  \n" +
//             "    },{  \n" +
//             "        \"id\":13,  \n" +
//             "        \"text\":\"index.html\"  \n" +
//             "    },{  \n" +
//             "        \"id\":14,  \n" +
//             "        \"text\":\"about.html\"  \n" +
//             "    },{  \n" +
//             "        \"id\":15,  \n" +
//             "        \"text\":\"welcome.html\"  \n" +
//             "    }]  \n" +
//             "}] ";
}
