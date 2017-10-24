/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import static wang.yongrui.wechat.utils.EntityUtils.*;

import java.io.Serializable;
import java.util.Set;

import org.springframework.beans.BeanUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.PlanBasic;
import wang.yongrui.wechat.entity.jpa.CircleDayEntity;
import wang.yongrui.wechat.entity.jpa.PlanEntity;

/**
 * @author Wang Yongrui
 *
 */
@JsonIgnoreProperties(value = { "createdDate", "createdBy", "lastModifiedDate", "lastModifiedBy" })
@JsonInclude(value = Include.NON_EMPTY)
public class Plan extends PlanBasic implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	@Setter
	private User user;

	@Getter
	@Setter
	private Set<CircleDay> circleDaySet;

	/**
	 * 
	 */
	public Plan() {
		super();
	}

	/**
	 * @param planEntity
	 */
	public Plan(PlanEntity planEntity) {
		super();
		if (null != planEntity) {
			BeanUtils.copyProperties(planEntity, this);
			setCircleDaySet(getObjectSetFromEntitySetWithHeritage(planEntity.getCircleDayEntitySet(),
					CircleDayEntity.class, CircleDay.class));
		}
	}

}
