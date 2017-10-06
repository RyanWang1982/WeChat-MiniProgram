/**
 * 
 */
package wang.yongrui.wechat.service;

import java.util.Set;

import wang.yongrui.wechat.entity.web.Part;

/**
 * @author Wang Yongrui
 *
 */
public interface PartService {

	/**
	 * @param partSet
	 * @return
	 */
	public boolean createPredefinedPartSet(Set<Part> partSet);

	/**
	 * @param partSet
	 * @param userId
	 * @return
	 */
	public boolean createCustomedPartSet(Set<Part> partSet, Long userId);

	/**
	 * @return
	 */
	public Set<Part> retrieveAllPredefinedPartSet();

	/**
	 * @param userId
	 * @return
	 */
	public Set<Part> retrieveAllCustomedPartSet(Long userId);

}
