package com.ren.test;

import com.ren.wwzq.common.utils.SHA1Util;
import org.junit.Test;

public class CommonTest {

    @Test
    public void test1() {
        String s = "{\"nickName\":\"Band\",\"gender\":1,\"language\":\"zh_CN\",\"city\":\"Guangzhou\",\"province\":\"Guangdong\",\"country\":\"CN\",\"avatarUrl\":\"http://wx.qlogo.cn/mmopen/vi_32/1vZvI39NWFQ9XM4LtQpFrQJ1xlgZxx3w7bQxKARol6503Iuswjjn6nIGBiaycAjAtpujxyzYsrztuuICqIM5ibXQ/0\"}HyVFkGl5F5OQWJZZaNzBBg==";
        try {
            String s2 = SHA1Util.encode(s);
            System.out.println(s2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
