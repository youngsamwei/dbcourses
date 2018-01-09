package cn.sdkd.csse.dbcourses.weixin.handler;

import cn.sdkd.csse.dbcourses.entity.Knowledgepoint;
import cn.sdkd.csse.dbcourses.service.IKnowledgepointService;
import cn.sdkd.csse.dbcourses.weixin.builder.TextBuilder;
import cn.sdkd.csse.dbcourses.weixin.service.WeixinService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.message.WxMpXmlMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutMessage;
import me.chanjar.weixin.mp.bean.message.WxMpXmlOutNewsMessage;
import me.chanjar.weixin.mp.builder.outxml.NewsBuilder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang
 */
@Component
public class MsgHandler extends AbstractHandler {
  @Resource
  private IKnowledgepointService knowledgepointService;

  /**
   * emoji表情转换(hex -> utf-16)
   *
   * @param hexEmoji
   * @return
   */
  public static String emoji(int hexEmoji) {
    return String.valueOf(Character.toChars(hexEmoji));
  }

  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService wxMpService,
                                  WxSessionManager sessionManager) {

    WeixinService weixinService = (WeixinService) wxMpService;

    if (!wxMessage.getMsgType().equals(WxConsts.XmlMsgType.EVENT)) {
      // 可以选择将消息保存到本地
    }

    EntityWrapper ew = new EntityWrapper();
    ew.like("knowledgepointName", wxMessage.getContent());
    List<Knowledgepoint> kps = knowledgepointService.selectList(ew);
    if (kps.size() > 0) {
      NewsBuilder nb = WxMpXmlOutMessage.NEWS();
      /*TODO：1)控制单个消息的条数，不超过10条
      * TODO：2)获取第一个paragraph作为描述, 最好能与Knowledgepoint一次查询得出结果
      * TODO：3)最后一条可以是“查看更多查询结果”的链接。
      * */
      for (Knowledgepoint kp : kps) {
        WxMpXmlOutNewsMessage.Item article1 = new WxMpXmlOutNewsMessage.Item();
        article1.setTitle(kp.getKnowledgepointName());
        article1.setDescription("");
        article1.setUrl("http://dbcourses.free.ngrok.cc/index.html?q=" + kp.getId());
        nb.addArticle(article1);
      }
      return nb.fromUser(wxMessage.getToUser())
        .toUser(wxMessage.getFromUser()).build();
    }
        /*自定义测试消息*/
    else if ("6".equals(wxMessage.getContent())) {
      WxMpXmlOutNewsMessage.Item item = new WxMpXmlOutNewsMessage.Item();
      item.setTitle("数据库课程资源");
      item.setDescription("数据库知识点展示");
      item.setPicUrl("");
      item.setUrl("http://dbcourses.free.ngrok.cc/index.html?q=2");
      return WxMpXmlOutMessage.NEWS().addArticle(item).fromUser(wxMessage.getToUser())
        .toUser(wxMessage.getFromUser()).build();
    }

    String content = "抱歉，没有查询到您输入的内容。";
    return new TextBuilder().build(content, wxMessage, weixinService);

  }

}
