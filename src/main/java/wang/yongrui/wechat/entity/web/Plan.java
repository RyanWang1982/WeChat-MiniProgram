/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import java.util.LinkedHashSet;
import java.util.Set;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;

import lombok.Getter;
import lombok.Setter;
import wang.yongrui.wechat.entity.basic.PlanBasic;
import wang.yongrui.wechat.entity.jpa.PartEntity;
import wang.yongrui.wechat.entity.jpa.PlanEntity;

/**
 * @author Wang Yongrui
 *
 */
public class Plan extends PlanBasic {

	@Getter
	@Setter
	private Set<Part> partSet;

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
			if (CollectionUtils.isNotEmpty(planEntity.getPartEntitySet())) {
				Set<Part> partSet = new LinkedHashSet<>();
				for (PartEntity partEntity : planEntity.getPartEntitySet()) {
					partSet.add(new Part(partEntity));
				}
				setPartSet(partSet);
			}
		}
	}

}
