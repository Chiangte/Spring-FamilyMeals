package com.borrow.web.controller;

import com.borrow.pojo.Borrow;
import com.borrow.service.BorrowService;
import com.borrow.web.util.Constants;
import com.borrow.web.util.WebUtils;
import com.borrow.web.vo.AddBorrowVo;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Awan
 * @Description //TODO 借阅控制器
 * @Date Created in 14:39 2018/12/4
 */
@Controller
public class BorrowController {
	private final Logger LOG = Logger.getLogger(BorrowController.class);
	
	@Autowired
	private BorrowService borrowService;
	
	@GetMapping("borrowList")
	public String borrowList(String currView, Integer pageNum, Integer pageSize, String keyword, Model model) {
		LOG.debug("分页查询借阅数据");
		currView = WebUtils.getValue("borrow_currView", currView, "list");
		pageNum = WebUtils.getValue("borrow_pageNum", pageNum, 1);
		pageSize = WebUtils.getValue("borrow_pageSize", pageSize, 1);

		WebUtils.setSession("borrow_currView", currView);
		WebUtils.setSession("borrow_pageNum", pageNum);
		WebUtils.setSession("borrow_pageSize", pageSize);
		
		//查询当前页数据
		PageInfo<Borrow> pageInfo = borrowService.getBorrowList(pageNum, pageSize, keyword);
		
		//存储分页相关数据
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("keyword", keyword);
		LOG.debug("借阅分页数据，借阅集合：" + pageInfo + "，关键字：" + keyword);
		
		//把session作用域的消息(如果有)，转存到request作用域
		WebUtils.sessionToRequest(Constants.MAP);
		
		//转发到借阅列表页面
		LOG.info("转发到/WEB-INF/views/borrowList.jsp页面");
		return "list".equals(currView) ? "borrowList_list" : "borrowList_table";
	}
	
	@GetMapping("returnBook")
	public String returnBook(Long id) {
		LOG.info("归还图书");
		//归还图书
		boolean flag = borrowService.updateBorrowById(id);
		
		//判断转向
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.STATUS, flag);
		map.put(Constants.MSG, flag ? "归还图书成功" : "归还图书失败");
		WebUtils.setSession(Constants.MAP, map);
		return "redirect:borrowList";
	}
	
	@GetMapping("deleteBorrow")
	public String deleteBorrow(Long id) {
		LOG.info("删除借阅");
		
		//删除借阅
		boolean flag = borrowService.deleteBorrowById(id);
		
		//判断转向
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.STATUS, flag);
		map.put(Constants.MSG, flag ? "删除借阅成功" : "删除借阅失败，该借阅记录对应的图书尚未归还");
		WebUtils.setSession(Constants.MAP, map);
		return "redirect:borrowList";
	}
	
	@GetMapping("addBorrow")
	public String addBorrow() {
		LOG.info("导航到添加借阅页面");
		return "addBorrow";
	}
	
	@PostMapping("addBorrow")
	@ResponseBody
	public Map<String, Object> addBorrow(@RequestBody AddBorrowVo addBorrowVo) {
		LOG.info("添加借阅");
		
		boolean flag = borrowService.addBorrow(addBorrowVo.getIdentityCard(), addBorrowVo.getBookIds());
	
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.STATUS, flag);
		map.put(Constants.MSG, flag ? "借阅成功" : "借阅失败");
		return map;
	}
}
