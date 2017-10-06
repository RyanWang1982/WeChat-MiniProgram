/**
 * 
 */
package wang.yongrui.wechat.entity.web;

import org.springframework.beans.BeanUtils;

import wang.yongrui.wechat.entity.basic.GroupBasic;
import wang.yongrui.wechat.entity.jpa.GroupEntity;

/**
 * @author Wang Yongrui
 *
 */
public class Group extends GroupBasic {

	/**
	 * 
	 */
	public Group() {
		super();
	}

	/**
	 * @param groupEntity
	 */
	public Group(GroupEntity groupEntity) {
		super();
		if (null != groupEntity) {
			BeanUtils.copyProperties(groupEntity, this);
		}
	}

}
