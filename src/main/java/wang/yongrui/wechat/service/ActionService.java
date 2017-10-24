/**
 * 
 */
package wang.yongrui.wechat.service;

import java.util.Set;

import wang.yongrui.wechat.entity.web.Action;

/**
 * @author Wang Yongrui
 *
 */
public interface ActionService {

	/**
	 * @param action
	 */
	public Action createCustomedOne(Action action);

	/**
	 * @param userId
	 * @return
	 */
	public Set<Action> retrieveAllCustomedSetByUser(Long userId);

	/**
	 * @param action
	 * @return
	 */
	public Action putUpdate(Action action);

	/**
	 * @param action
	 * @return
	 */
	public Action patchUpdate(Action action);

}
