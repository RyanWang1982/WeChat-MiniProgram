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
	 * @param id
	 * @return
	 */
	public User retrieveOneById(Long id);

	/**
	 * @param wechatUnionId
	 * @return
	 */
	public User retrieveOneByWechatUnionId(String wechatUnionId);

	/**
	 * @param id
	 * @return
	 */
	public User retrieveOneWithPlanById(Long id);

	/**
	 * @param id
	 * @return
	 */
	public User retrieveOneWithRealityById(Long id);

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
