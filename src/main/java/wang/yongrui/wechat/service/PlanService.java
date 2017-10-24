/**
 * 
 */
package wang.yongrui.wechat.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import wang.yongrui.wechat.entity.web.Plan;
import wang.yongrui.wechat.entity.web.criteria.PlanCriteria;

/**
 * @author Wang Yongrui
 *
 */
public interface PlanService {

	/**
	 * @param plan
	 * @return
	 */
	public Plan create(Plan plan);

	/**
	 * @param id
	 * @return
	 */
	public Plan retrieveOne(Long id);

	/**
	 * @param planCriteria
	 * @param pageable
	 * @return
	 */
	public Page<Plan> retrievePage(PlanCriteria planCriteria, Pageable pageable);

	/**
	 * @param plan
	 * @return
	 */
	public Plan putUpdate(Plan plan);

	/**
	 * @param plan
	 * @return
	 */
	public Plan patchUpdate(Plan plan);

}
