/**
 * 
 */
package wang.yongrui.wechat.entity.basic;

import java.util.Calendar;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.enumeration.Purpose;
import wang.yongrui.wechat.entity.fundamental.AuditingEntity;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class PlanBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	private String name;

	private String description;

	@Enumerated(value = EnumType.STRING)
	private Purpose purpose;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar fromDate;

	@JsonFormat(pattern = "yyyy-MM-dd")
	private Calendar toDate;

	private String recurring;

}
