package com.imooc;

import com.imooc.entity.req.EnterReq;
import com.imooc.web.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {
	@Autowired
	UserService userService;
	@Test
	public void contextLoads() {
		EnterReq enterReq = new EnterReq();
		enterReq.setUserId(1L);
		enterReq.setCode("1");
		System.out.println(userService.enter(enterReq).getRet());
	}

}
