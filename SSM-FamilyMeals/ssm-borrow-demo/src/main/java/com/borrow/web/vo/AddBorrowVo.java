package com.borrow.web.vo;

import lombok.Data;

/**
 * @Author Awan
 * @Description //TODO 借阅业务对象
 * 是存活在业务层的，是业务逻辑使用的，它存活的目的就是为数据提供一个生存的地方。
 * @Date  Created in 14:07 2018/12/4
 */
@Data
public class AddBorrowVo {
	private String identityCard;
	private Long[] bookIds;
}
