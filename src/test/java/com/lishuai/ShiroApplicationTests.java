package com.lishuai;

import com.lishuai.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShiroApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    RedisUtil redisUtil;

    /**
     * 测试 redisUtil
     */
    @Test
    public void testRedis(){
        redisUtil.set("test","11111");
        Object test = redisUtil.get("test");
        System.out.println(test);
    }

}
