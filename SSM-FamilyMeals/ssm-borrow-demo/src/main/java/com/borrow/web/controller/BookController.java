package com.borrow.web.controller;

import com.borrow.pojo.Book;
import com.borrow.service.BookService;
import com.borrow.web.util.Constants;
import com.borrow.web.util.WebUtils;
import com.borrow.web.vo.BookVo;
import com.github.pagehelper.PageInfo;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Author Awan
 * @Description //TODO 图书控制器
 * @Date Created in 14:38 2018/12/4
 */
@Controller
public class BookController {
	private final Logger LOG = Logger.getLogger(BookController.class);
	
	@Autowired
	private BookService bookService;
	
	@GetMapping("bookList")
	public String bookList(String currView, Integer pageNum, Integer pageSize, String keyword, Model model) {
		LOG.debug("分页查询图书数据");
		currView = WebUtils.getValue("book_currView", currView, "list");
		pageNum = WebUtils.getValue("book_pageNum", pageNum, 1);
		pageSize = WebUtils.getValue("book_pageSize", pageSize, 1);

		WebUtils.setSession("book_currView", currView);
		WebUtils.setSession("book_pageNum", pageNum);
		WebUtils.setSession("book_pageSize", pageSize);
		
		//查询当前页数据
		PageInfo<Book> pageInfo = bookService.getBookList(pageNum, pageSize, keyword);
		
		//存储分页相关数据
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("keyword", keyword);
		LOG.debug("图书分页数据，图书集合：" + pageInfo + "，关键字：" + keyword);
		
		//把session作用域的消息(如果有)，转存到request作用域
		WebUtils.sessionToRequest(Constants.MAP);
		
		//转发到图书列表页面
		LOG.info("转发到/WEB-INF/views/bookList.jsp页面");
		return "list".equals(currView) ? "bookList_list" : "bookList_table";
	}
	
	@GetMapping("deleteBook")
	public String deleteBook(Long id) {
		LOG.debug("删除图书");
		//删除图书
		boolean flag = bookService.deleteBookById(id);
		
		//判断转向
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.STATUS, flag);
		map.put(Constants.MSG, flag ? "删除图书成功" : "删除图书失败，该图书已被借阅尚未归还");
		WebUtils.setSession(Constants.MAP, map);
		
		return "redirect:bookList";
	}
	
	@GetMapping("addBook")
	public String addBook(BookVo bookVo) {
		LOG.info("导航到添加图书页面");
		
		String startDate = DateFormatUtils.format(DateUtils.addYears(new Date(), -100), "yyyy-MM-dd");
		String endDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

		WebUtils.setSession("addStartDate", startDate);
		WebUtils.setSession("addEndDate", endDate);
		
		LOG.info("转发到/WEB-INF/views/addBook.jsp页面");
		return "addBook";
	}
	
	@PostMapping("addBook")
	public String addBook(MultipartFile file, @Valid BookVo bookVo, Errors errors, Model model) throws IllegalStateException, IOException {
		LOG.info("添加图书");
		
		if (errors.hasErrors()) {
			return "addBook";
		}
		
		if (!bookService.isUniqueByName(null, bookVo.getName())) {
			errors.rejectValue("name", "book.name.isexists", "书名已经存在，请更换");
			return "addBook";
		}
		
		//获取原始文件名称
		String oldFilename = file.getOriginalFilename();
		//构造新的文件名称，目的是为了避免文件重名而造成的文件覆盖
		String newFilename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(oldFilename);
		//设置图书的封面图片
		bookVo.setCover(newFilename);
		//取得相对路径的绝对路径
		String savePath = WebUtils.getRealPath("/WEB-INF/classes/static/pages/imgs");
		//由绝对路径和新的文件名构造文件对象
		File destFile = new File(savePath, newFilename);
		file.transferTo(destFile);
		
		Book book = new Book();
		BeanUtils.copyProperties(bookVo, book);
		boolean flag = bookService.addBook(book);
		
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.STATUS, flag);
		if (flag) {
			LOG.info("添加图书成功");
			//把添加成功的消息存入session作用域
			map.put(Constants.MSG, "添加图书成功");
			WebUtils.setSession(Constants.MAP, map);
			//把添加图书相关作用域的数据删除
			WebUtils.removeSession("addStartDate", "addEndDate");
			//重定向到图书列表Servlet
			return "redirect:bookList";
		} else {
			LOG.info("添加图书失败");
			//把添加失败的消息存入request作用域
			map.put(Constants.MSG, "添加图书失败");
			WebUtils.setRequest(Constants.MAP, map);
			//转发到修改图书页面
			LOG.info("转发到/WEB-INF/views/addBook.jsp页面");
			return "addBook";
		}
	}
	
	@GetMapping("checkName")
	@ResponseBody
	public Map<String, Boolean> checkName(Long id, String name) {
		LOG.info("检查书名唯一性");
		//调用方法
		boolean flag = bookService.isUniqueByName(id, name);
		//返回JSON数据
		Map<String, Boolean> map = new HashMap<>();
		map.put(Constants.VALID, flag);
		return map;
	}
	
	@GetMapping("updateBook")
	public String updateBook(Long id, Model model) {
		LOG.info("导航到修改图书页面");
		
		Book book = bookService.getBookById(id);
		BookVo bookVo = new BookVo();
		BeanUtils.copyProperties(book, bookVo);
		model.addAttribute("bookVo", bookVo);
		
		String startDate = DateFormatUtils.format(DateUtils.addYears(new Date(), -100), "yyyy-MM-dd");
		String endDate = DateFormatUtils.format(new Date(), "yyyy-MM-dd");

		WebUtils.setSession("updateStartDate", startDate);
		WebUtils.setSession("updateEndDate", endDate);
		WebUtils.setSession("cover", book.getCover());
		
		LOG.info("转发到/WEB-INF/views/updateBook.jsp页面");
		return "updateBook";
	}
	
	@PostMapping("updateBook")
	public String updateBook(MultipartFile file, @Valid BookVo bookVo, Errors errors, Model model) throws IllegalStateException, IOException {
		LOG.info("修改图书");
		
		if (errors.hasErrors()) {
			return "updateBook";
		}
		
		if (!bookService.isUniqueByName(bookVo.getId(), bookVo.getName())) {
			errors.rejectValue("name", "book.name.isexists", "书名已经存在，请更换");
			return "updateBook";
		}
		
		String oldCover = WebUtils.getSession("cover");
		if (!file.isEmpty()) {
			//获取原始文件名称
			String oldFilename = file.getOriginalFilename();
			//构造新的文件名称，目的是为了避免文件重名而造成的文件覆盖
			String newFilename = UUID.randomUUID().toString() + "." + FilenameUtils.getExtension(oldFilename);
			//设置图书的封面图片
			bookVo.setCover(newFilename);
			//取得相对路径的绝对路径
			String savePath = WebUtils.getRealPath("/WEB-INF/classes/static/pages/imgs");
			//由绝对路径和新的文件名构造文件对象
			File destFile = new File(savePath, newFilename);
			file.transferTo(destFile);
			//删除旧文件
			File oldFile = new File(savePath, oldCover);
			FileUtils.deleteQuietly(oldFile);
		} else {
			//设置图书的封面图片
			bookVo.setCover(oldCover);
		}
		
		Book book = new Book();
		BeanUtils.copyProperties(bookVo, book);
		boolean flag = bookService.updateBook(book);
		
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.STATUS, flag);
		if (flag) {
			LOG.info("修改图书成功");
			map.put(Constants.MSG, "修改图书成功");
			WebUtils.setSession(Constants.MAP, map);
			WebUtils.removeSession("updateStartDate", "updateEndDate", "cover");
			return "redirect:bookList";
		} else {
			LOG.info("修改图书失败");
			map.put(Constants.MSG, "修改图书失败");
			WebUtils.setRequest(Constants.MAP, map);
			LOG.info("转发到/WEB-INF/views/updateBook.jsp页面");
			return "updateBook";
		}
	}
	
	@GetMapping("allowBorrowList")
	@ResponseBody
	public PageInfo<Book> allowBorrowList(Integer pageNum, Integer pageSize, String keyword) {
		return bookService.getAllowBorrowBookList(pageNum, pageSize, keyword);
	}
}
