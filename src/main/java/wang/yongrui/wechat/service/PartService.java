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
	public boolean createPredefinedSet(Set<Part> partSet);

	/**
	 * @return
	 */
	public Set<Part> retrieveAllPredefinedSet();

	/**
	 * @param part
	 * @return
	 */
	public Part putUpdate(Part part);

	/**
	 * @param part
	 * @return
	 */
	public Part patchUpdate(Part part);

}
