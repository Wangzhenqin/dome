package com.imooc;

import com.imooc.entity.Enum.JudgeEnum;
import com.imooc.entity.Page;
import com.imooc.entity.dto.TitleDTO;
import com.imooc.entity.req.*;
import com.imooc.web.TimeService;
import com.imooc.web.TitleService;
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
	@Autowired
	TimeService timeService;
	@Autowired
	TitleService titleService;
	@Test
	public void contextLoads() {
		EnterReq enterReq = new EnterReq();
		enterReq.setUserId(1L);
		enterReq.setCode("1");
		System.out.println(userService.enter(enterReq).getRet());
	}

	@Test
	public void updateTime() {
		UpdateTimeReq req = new UpdateTimeReq();
		req.setUserId(2L);
		req.setStartTime(1L);
		req.setEndTime(1L);
		req.setEvent(JudgeEnum.SELECT_TITLE.getDesc());
		System.out.println(timeService.updateTime(req));
	}
	@Test
	public void selectUser() {
		GetUserReq getUserReq=new GetUserReq();
		Page page = new Page();
		page.setPageNum(1);
		page.setPageSize(10);
		getUserReq.setUserType(0);
		getUserReq.setPage(page);
		System.out.println(userService.selectUser(getUserReq));
	}
	@Test
	public void getTitleByDTO() {
		GetTitleByDTOReq req=new GetTitleByDTOReq();
		Page page = new Page();
		page.setPageNum(1);
		page.setPageSize(10);
		TitleDTO titleDTO = new TitleDTO();
		titleDTO.setFacultyId(1L);
		req.setTitleDTO(titleDTO);
		req.setPage(page);
		System.out.println(titleService.getTitleByDTO(req));
	}

	@Test
	public void getTeacherTitle() {
		GetTeacherTitleReq req=new GetTeacherTitleReq();
		Page page = new Page();
		page.setPageNum(1);
		page.setPageSize(10);
		req.setPage(page);
		req.setUserId(2L);
		System.out.println(titleService.getTeacherTitle(req));
	}
}
