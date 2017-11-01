/**
 * 
 */
package wang.yongrui.wechat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import wang.yongrui.wechat.entity.web.Reality;
import wang.yongrui.wechat.entity.web.criteria.RealityCriteria;

/**
 * @author Wang Yongrui
 *
 */
public interface RealityService {

	/**
	 * @param reality
	 * @return
	 */
	public Reality create(Reality reality);

	/**
	 * @param id
	 * @return
	 */
	public Reality retrieveOne(Long id);

	/**
	 * @param realityCriteria
	 * @param pageable
	 * @return
	 */
	public Page<Reality> retrievePage(RealityCriteria realityCriteria, Pageable pageable);

	/**
	 * @param reality
	 * @return
	 */
	public Reality putUpdate(Reality reality);

}
