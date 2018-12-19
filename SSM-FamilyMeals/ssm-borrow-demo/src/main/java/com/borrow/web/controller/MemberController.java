/**
 * 
 */
package com.borrow.web.controller;

import com.borrow.pojo.Member;
import com.borrow.service.MemberService;
import com.borrow.web.util.Constants;
import com.borrow.web.util.WebUtils;
import com.borrow.web.vo.MemberVo;
import com.borrow.web.vo.MemberVo.MemberGroupSequence;
import com.github.pagehelper.PageInfo;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServlet;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author Awan
 * @Description //TODO 会员列表控制器
 * @Date Created in 15:16 2018/12/4
 */
@Controller
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 7232231078243071291L;
	
	private final Logger LOG = Logger.getLogger(MemberController.class);
	
	@Autowired
	private MemberService memberService;
	
	@GetMapping("memberList")
	public String memberList(Integer pageNum, Integer pageSize, String keyword, Model model) {
		LOG.debug("分页查询会员数据");
		pageNum = WebUtils.getValue("member_pageNum", pageNum, 1);
		pageSize = WebUtils.getValue("member_pageSize", pageSize, 1);

		WebUtils.setSession("member_pageNum", pageNum);
		WebUtils.setSession("member_pageSize", pageSize);
		
		//查询当前页数据
		PageInfo<Member> pageInfo = memberService.getMemberList(pageNum, pageSize, keyword);
		
		//存储分页相关数据
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("keyword", keyword);
		LOG.debug("会员分页数据，会员集合：" + pageInfo + "，关键字：" + keyword);
		
		//把session作用域的消息(如果有)，转存到request作用域
		WebUtils.sessionToRequest(Constants.MAP);
		
		//转发到会员列表页面
		LOG.info("转发到/WEB-INF/views/memberList.jsp页面");
		return "memberList";
	}
	
	@GetMapping("addMember")
	public String addMember(MemberVo memberVo) {
		LOG.info("导航到新增页面");
		return "addMember";
	}
	
	@GetMapping("checkIdentityCard")
	@ResponseBody
	public Map<String, Boolean> checkIdentityCard(Long id, String identityCard) {
		LOG.info("检查身份证号唯一性");
		boolean flag = memberService.isUniqueByIdentityCard(id, identityCard);
		Map<String, Boolean> map = new HashMap<>();
		map.put(Constants.VALID, flag);
		return map;
	}
	
	@GetMapping("checkPhone")
	@ResponseBody
	public Map<String, Boolean> checkPhone(Long id, String phone) {
		LOG.info("检查联系电话唯一性");
		boolean flag = memberService.isUniqueByPhone(id, phone);
		Map<String, Boolean> map = new HashMap<>();
		map.put(Constants.VALID, flag);
		return map;
	}

	@PostMapping("addMember")
	public String addMember(@Validated(MemberGroupSequence.class) MemberVo memberVo, Errors errors, Model model) {
		LOG.info("新增会员");
		
		//验证判断
		if (errors.hasErrors()) {
			return "addMember";
		}
		
		if (!memberService.isUniqueByIdentityCard(null, memberVo.getIdentityCard())) {
			errors.rejectValue("identityCard", "member.identityCard.exists", "身份证号已经存在，请更换");
			return "addMember";
		}
		
		if (!memberService.isUniqueByPhone(null, memberVo.getPhone())) {
			errors.rejectValue("phone", "member.phone.exists", "电话号码已经存在，请更换");
			return "addMember";
		}
		
		//调用方法
		Member member = new Member();
		BeanUtils.copyProperties(memberVo, member);
		boolean flag = memberService.addMember(member);
		
		//判断转向
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.STATUS, flag);
		if (flag) {
			LOG.info("添加会员成功");
			//把添加成功的消息存入session作用域
			map.put(Constants.MSG, "添加会员成功");
			WebUtils.setSession(Constants.MAP, map);
			//重定向到会员列表
			return "redirect:memberList";
		} else {
			LOG.info("添加会员失败");
			//把添加失败的消息存入request作用域
			map.put(Constants.MSG, "添加会员失败");
			WebUtils.setRequest(Constants.MAP, map);
			//转发到修改会员页面
			LOG.info("转发到/WEB-INF/views/addMember.jsp页面");
			return "addMember";
		}
	}
	
	@GetMapping("updateMember")
	public String updateMember(Long id, Model model) {
		LOG.info("导航到修改会员页面");
		//查询会员
		Member member = memberService.getMemberById(id);
		
		//把会员存入request作用域
		model.addAttribute("memberVo", member);
		
		//转发到修改会员页面
		LOG.info("转发到/WEB-INF/views/updateMember.jsp页面");
		return "updateMember";
	}
	
	@PostMapping("updateMember")
	public String updateMember(@Validated(MemberGroupSequence.class) MemberVo memberVo, Errors errors, Model model) {
		LOG.info("修改会员");

		//验证判断
		if (errors.hasErrors()) {
			return "updateMember";
		}
		
		if (!memberService.isUniqueByIdentityCard(memberVo.getId(), memberVo.getIdentityCard())) {
			errors.rejectValue("identityCard", "member.identityCard.exists", "身份证号已经存在，请更换");
			return "updateMember";
		}
		
		if (!memberService.isUniqueByPhone(memberVo.getId(), memberVo.getPhone())) {
			errors.rejectValue("phone", "member.phone.exists", "电话号码已经存在，请更换");
			return "updateMember";
		}
		
		//调用方法
		Member member = new Member();
		BeanUtils.copyProperties(memberVo, member);
		boolean flag = memberService.updateMember(member);
		
		//判断转向
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.STATUS, flag);
		if (flag) {
			LOG.info("修改会员成功");
			//把修改成功的消息存入session作用域
			map.put(Constants.MSG, "修改会员成功");
			WebUtils.setSession(Constants.MAP, map);
			//重定向到会员列表
			return "redirect:memberList";
		} else {
			LOG.info("修改会员失败");
			//把修改失败的消息存入request作用域
			map.put(Constants.MSG, "修改会员失败");
			WebUtils.setRequest(Constants.MAP, map);
			//4.3.转发到修改会员页面
			LOG.info("转发到/WEB-INF/views/updateMember.jsp页面");
			return "updateMember";
		}
	}
	
	@GetMapping("deleteMember")
	public String deleteMember(Long id) {
		LOG.info("删除会员");
		//删除会员
		boolean flag = memberService.deleteMemberById(id);
		
		//判断转向
		Map<String, Object> map = new HashMap<>();
		map.put(Constants.STATUS, flag);
		map.put(Constants.MSG, flag ? "删除会员成功" : "删除会员失败，该会员有借阅的图书尚未归还");
		WebUtils.setSession(Constants.MAP, map);
		return "redirect:memberList";
	}
	
	@GetMapping("existsIdentityCard")
	@ResponseBody
	public Map<String, Boolean> existsIdentityCard(String identityCard) {
		LOG.info("检查身份证号是否存在");
		boolean flag = memberService.isUniqueByIdentityCard(null, identityCard);
		Map<String, Boolean> map = new HashMap<>();
		map.put(Constants.VALID, !flag);
		return map;
	}
}
