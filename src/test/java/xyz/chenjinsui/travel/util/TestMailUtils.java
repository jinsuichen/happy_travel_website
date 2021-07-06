package xyz.chenjinsui.travel.util;

import org.junit.Test;

public class TestMailUtils {
    @Test
    public void testSendMail(){
        String content = "<a href='http://localhost/travel_website_war/activeUserSer?code=" + "88" + "'>点击激活【快乐旅游网】</a>";
        MailUtils.sendMail("ischenjinsui@163.com", content, "快乐旅游网-激活邮件");
    }
}
