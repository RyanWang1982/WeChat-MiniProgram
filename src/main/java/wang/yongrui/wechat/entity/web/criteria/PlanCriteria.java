/**
 * 
 */
package wang.yongrui.wechat.entity.web.criteria;

import java.util.Calendar;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.enumeration.Grade;
import wang.yongrui.wechat.entity.enumeration.Purpose;

/**
 * @author Wang Yongrui
 *
 */
@Getter
@Setter
public class PlanCriteria {

	private Long userId;

	@Enumerated(value = EnumType.STRING)
	private Purpose purpose;

	@Enumerated(value = EnumType.STRING)
	private Grade grade;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar fromDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar toDate;

}
