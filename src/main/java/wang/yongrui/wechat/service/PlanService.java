/**
 * 
 */
package wang.yongrui.wechat.service;

import java.util.Set;

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
	 * @param planSet
	 */
	public boolean createPredefinedSet(Set<Plan> planSet);

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
	 * @param planCriteria
	 * @return
	 */
	public Set<Plan> retrieveAllPredefinedOnes(PlanCriteria planCriteria);

	/**
	 * @param plan
	 * @return
	 */
	public Plan putUpdate(Plan plan);

}
