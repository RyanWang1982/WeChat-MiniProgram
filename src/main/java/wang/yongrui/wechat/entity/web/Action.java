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
import wang.yongrui.wechat.entity.basic.ActionBasic;
import wang.yongrui.wechat.entity.jpa.ActionEntity;
import wang.yongrui.wechat.entity.jpa.GroupEntity;

/**
 * @author Wang Yongrui
 *
 */
public class Action extends ActionBasic {

	@Getter
	@Setter
	private Set<Group> groupSet;

	/**
	 * 
	 */
	public Action() {
		super();
	}

	/**
	 * @param actionEntity
	 */
	public Action(ActionEntity actionEntity) {
		super();
		if (null != actionEntity) {
			BeanUtils.copyProperties(actionEntity, this);
			if (CollectionUtils.isNotEmpty(actionEntity.getGroupEntitySet())) {
				Set<Group> groupSet = new LinkedHashSet<>();
				for (GroupEntity groupEntity : actionEntity.getGroupEntitySet()) {
					groupSet.add(new Group(groupEntity));
				}
				setGroupSet(groupSet);
			}
		}
	}

}
