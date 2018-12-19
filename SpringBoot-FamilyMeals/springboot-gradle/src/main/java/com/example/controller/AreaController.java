package com.example.controller;

import com.example.beans.PageResultBean;
import com.example.entity.Area;
import com.example.service.AreaServcie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Awan
 * @Description:
 * @Date Created in 18:48  2018/11/23
 */
@RestController
@RequestMapping("area")
public class AreaController {

		private final AreaServcie areaServcie;
		@Autowired
		public  AreaController(AreaServcie areaServcie){
			this.areaServcie = areaServcie;
		}

	/**
	 *    这里的@RequestParam(name = "pagesize",required = false,defaultValue = "10")
	 *    -name 为传输时为接受key为pagesize的参数
	 *    -required 为是否为必须传输的参数
	 *    *-default 就是如果没有接收到值 就给予默认值
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */

/*	//方式一 无 接口返回的统一化
	@RequestMapping("query")
	public List<Area> areaList(
			@RequestParam(name = "pagenum", required = false, defaultValue = "1") Integer pageNum,
			@RequestParam(name = "pagesize", required = false, defaultValue = "10") Integer pageSize){
			return  areaServcie.selectAreaAll(pageNum,pageSize);
		}*/

	@GetMapping("query")
	public PageResultBean<List<Area>> arealist(
				@RequestParam(name = "pagenum", required = false, defaultValue = "1") Integer pageNum,
				@RequestParam(name = "pagesize", required =  false, defaultValue = "10") Integer pageSize){
		return new PageResultBean<>(areaServcie.selectAreaAll(pageNum,pageSize));
	}
}
