package com.example.beans;


import com.github.pagehelper.PageInfo;
import lombok.Getter;

import java.io.Serializable;
import java.util.List;

/**
 * @Author: Awan
 * @Description: 分页API 统一返回的bean
 *  核心：接口返回bean的统一使AOP可以很好的管理，
 *  写好切入点，对于后续需要做的日志管理，以及方法运行时间，和全局异常处理.
 * @Date Created in 14:13  2018/11/26
 */
@Getter
public class PageResultBean<T> extends ResultBean<T> implements Serializable {
	/**
	 * 总记录数
	 */
	private long totalRecord;
	/**
	 * 总页数
	 */
	private Integer pageCount;
	/**
	 * 当前页码
	 */
	private Integer pageNo;
	/**
	 * 当前页的记录数量
	 */
	private Integer pageSize;
	/**
	 *
	 */
	private List<?> data;

	public PageResultBean(List<?> data) {
		PageInfo<?> pageInfo = new PageInfo<>(data);
		this.setPageNo(pageInfo.getPageNum())
				.setPageSize(pageInfo.getPageSize())
				.setPageCount(pageInfo.getPages())
				.setTotalRecord(pageInfo.getTotal())
				.setData(data);
	}

	public PageResultBean() {
		super();
	}

	private PageResultBean setTotalRecord(long totalRecord) {
		this.totalRecord = totalRecord;
		return this;
	}

	private PageResultBean setPageCount(int pageCount) {
		this.pageCount = pageCount;
		return this;
	}

	public PageResultBean setPageNo(int pageNo) {
		this.pageNo = pageNo;
		return this;
	}

	public PageResultBean setPageSize(int pageSize) {
		this.pageSize = pageSize;
		return this;
	}

	public Integer getPageNo() {
		return pageNo == null ? ConstantBean.PAGE_NUM_DEFAULT : pageNo;
	}


	public Integer getPageSize() {
		return pageSize == null ? ConstantBean.PAGE_SIZE_DEFAULT : pageSize;
	}

	@Override
	public PageResultBean setMsg(String msg) {
		super.setMsg(msg);
		return this;
	}

	@Override
	public PageResultBean setCode(int code) {
		super.setCode(code);
		return this;
	}


	private void setData(List data) {
		this.data = data;
	}

	@Override
	public T getData() {
		return (T) data;
	}

/*	@Override
	public PageResultBean setData(T data) {
		super.setData(data);
		return  this;
	}*/

}
