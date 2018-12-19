package com.borrow.web.vo;

import com.borrow.pojo.Publishing;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Awan
 * @Description //TODO 图书业务对象，
 * 是存活在业务层的，是业务逻辑使用的，它存活的目的就是为数据提供一个生存的地方。
 * @Date Created in  14:07 2018/12/4
 */
@Data
public class BookVo {
	/**
	 *  图书ID
	 */
	private Long id;

	@NotBlank(message="书名不能为空")
	@Length(max=100, message="书名长度不能超过{max}个字符")
	/**
	 * 图书名称
	 */
	private String name;

	@NotBlank(message="作者不能为空")
	@Length(max=50, message="作者长度不能超过{max}个字符")
	/**
	 * 图书作者
	 */
	private String author;

	@NotNull(message="价格不能为空")
	@DecimalMin(value="0.0", inclusive=false, message="价格必须大于{value}")
	/**
	 * 图书价格
	 */
	private BigDecimal price;

	@NotNull(message="请选择出版社")
	/**
	 *出版社
	 */
	private Publishing publishing;

	@NotNull(message="请选择出版日期")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	/**
	 * 出版日期
	 */
	private Date createDate;

	/**
	 * 封面图片
	 */
	private String cover;

	@Length(max=1000, message="图书摘要长度不能超过{max}个字符")
	/**
	 * 图书摘要
	 */
	private String summary;
}
