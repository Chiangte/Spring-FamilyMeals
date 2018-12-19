package com.borrow.web.vo;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.groups.Default;

/**
 * @Author Awan
 * @Description //TODO 会员业务对象
 * 是存活在业务层的，是业务逻辑使用的，它存活的目的就是为数据提供一个生存的地方。
 * @Date  Created in 14:11 2018/12/4
 */
@Data
public class MemberVo {

	private Long id;

	@NotBlank(message="姓名不能为空", groups=GroupA.class)
	@Length(max=20, message="姓名长度不能超过{max}个字符", groups=GroupB.class)
	/**
	 * 会员姓名
	 */
	private String name;

	@NotBlank(message="身份证号不能为空", groups=GroupA.class)
	@Pattern(regexp="\\d{17}[\\dXx]{1}", message="身份证号格式不正确", groups=GroupB.class)
	/**
	 * 会员身份证号
	 */
	private String identityCard;

	@NotBlank(message="联系电话不能为空", groups=GroupA.class)
	@Pattern(regexp="((00|\\+)?(86(?:-| )))?((\\d{11})|(\\d{3}[- ]{1}\\d{4}[- ]{1}\\d{4})|((\\d{2,4}[- ]){1}(\\d{7,8}|(\\d{3,4}[- ]{1}\\d{4}))([- ]{1}\\d{1,4})?))", message="电话号码格式不正确", groups=GroupB.class)
	/**
	 * 会员电话
	 */
	private String phone;

	@GroupSequence({Default.class, GroupA.class, GroupB.class})
	public interface MemberGroupSequence {}
}
