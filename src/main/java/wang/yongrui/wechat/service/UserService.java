/**
 * 
 */
package wang.yongrui.wechat.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import wang.yongrui.wechat.entity.web.User;

/**
 * @author Wang Yongrui
 *
 */
public interface UserService extends UserDetailsService {

	/**
	 * @param user
	 * @return
	 */
	public User create(User user);

	/**
	 * @param wechatMPOpenId
	 * @return
	 */
	public User retrieveOneByWechatMPOpenId(String wechatMPOpenId);

	/**
	 * @param wechatUnionId
	 * @return
	 */
	public User retrieveOneByWechatUnionId(String wechatUnionId);

	/**
	 * @param id
	 * @return
	 */
	public User retrieveOneWithPlan(Long id);

	/**
	 * @param user
	 * @return
	 */
	public User putUpdate(User user);

	/**
	 * @param user
	 * @return
	 */
	public User patchUpdate(User user);

}
