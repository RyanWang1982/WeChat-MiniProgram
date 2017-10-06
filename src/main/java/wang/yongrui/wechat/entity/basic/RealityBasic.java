/**
 * 
 */
package wang.yongrui.wechat.entity.basic;

import java.util.Calendar;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.fundamental.AuditingEntity;

/**
 * @author Wang Yongrui
 *
 */
@MappedSuperclass
@Getter
@Setter
public class RealityBasic extends AuditingEntity {

	@Id
	@GeneratedValue
	private Long id;

	private Calendar date;

}
