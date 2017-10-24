/**
 * 
 */
package wang.yongrui.wechat.service.impl;

import static org.springframework.beans.BeanUtils.*;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.JoinType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.wechat.entity.jpa.ActionEntity;
import wang.yongrui.wechat.entity.jpa.ActionEntity_;
import wang.yongrui.wechat.entity.jpa.CircleDayEntity;
import wang.yongrui.wechat.entity.jpa.CircleDayEntity_;
import wang.yongrui.wechat.entity.jpa.ExerciseEntity;
import wang.yongrui.wechat.entity.jpa.ExerciseEntity_;
import wang.yongrui.wechat.entity.jpa.GroupEntity;
import wang.yongrui.wechat.entity.jpa.PlanEntity;
import wang.yongrui.wechat.entity.jpa.PlanEntity_;
import wang.yongrui.wechat.entity.jpa.UserEntity;
import wang.yongrui.wechat.entity.web.CircleDay;
import wang.yongrui.wechat.entity.web.Exercise;
import wang.yongrui.wechat.entity.web.Group;
import wang.yongrui.wechat.entity.web.Plan;
import wang.yongrui.wechat.entity.web.criteria.PlanCriteria;
import wang.yongrui.wechat.repository.PlanRepository;
import wang.yongrui.wechat.repository.UserRepository;
import wang.yongrui.wechat.service.PlanService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class PlanServiceImpl implements PlanService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PlanRepository planRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.PlanService#create(wang.yongrui.wechat.entity
	 * .web.Plan)
	 */
	@Override
	public Plan create(Plan plan) {
		PlanEntity planEntity = new PlanEntity();
		copyProperties(plan, planEntity);
		UserEntity userEntity = userRepository.findOne(plan.getUser().getId());
		planEntity.setUserEntity(userEntity);

		Set<CircleDayEntity> circleDayEntitySet = new LinkedHashSet<>();
		for (CircleDay circleDay : plan.getCircleDaySet()) {
			CircleDayEntity circleDayEntity = new CircleDayEntity();
			copyProperties(circleDay, circleDayEntity);
			Set<ExerciseEntity> exerciseEntitySet = new LinkedHashSet<>();
			for (Exercise exercise : circleDay.getExerciseSet()) {
				ExerciseEntity exerciseEntity = new ExerciseEntity();
				copyProperties(exercise, exerciseEntity);
				ActionEntity actionEntity = new ActionEntity();
				copyProperties(exercise.getAction(), actionEntity);
				exerciseEntity.setActionEntity(actionEntity);
				Set<GroupEntity> groupEntitySet = new LinkedHashSet<>();
				for (Group group : exercise.getGroupSet()) {
					GroupEntity groupEntity = new GroupEntity();
					copyProperties(group, groupEntity);
					groupEntity.setExerciseEntity(exerciseEntity);
					groupEntitySet.add(groupEntity);
				}
				exerciseEntity.setGroupEntitySet(groupEntitySet);
				exerciseEntity.setCircleDayEntity(circleDayEntity);
				exerciseEntitySet.add(exerciseEntity);
			}
			circleDayEntity.setExerciseEntitySet(exerciseEntitySet);
			circleDayEntity.setPlanEntity(planEntity);
			circleDayEntitySet.add(circleDayEntity);
		}
		planEntity.setCircleDayEntitySet(circleDayEntitySet);
		planEntity = planRepository.saveAndFlush(planEntity);
		entityManager.refresh(planEntity);

		return retrieveOne(planEntity.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.wechat.service.PlanService#retrieveOne(java.lang.Long)
	 */
	@Override
	public Plan retrieveOne(Long id) {
		PlanEntity planEntity = planRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			if (Long.class != criteriaQuery.getResultType()) {
				root.fetch(PlanEntity_.userEntity, JoinType.LEFT);
				root.fetch(PlanEntity_.circleDayEntitySet, JoinType.LEFT)
						.fetch(CircleDayEntity_.exerciseEntitySet, JoinType.LEFT)
						.fetch(ExerciseEntity_.actionEntity, JoinType.LEFT)
						.fetch(ActionEntity_.partEntitySet, JoinType.LEFT);
				root.fetch(PlanEntity_.circleDayEntitySet, JoinType.LEFT)
						.fetch(CircleDayEntity_.exerciseEntitySet, JoinType.LEFT)
						.fetch(ExerciseEntity_.groupEntitySet, JoinType.LEFT);
			}
			return criteriaBuilder.equal(root.get(PlanEntity_.id), id);
		});

		return new Plan(planEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.PlanService#retrievePage(wang.yongrui.wechat.
	 * entity.web.criteria.PlanCriteria,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Plan> retrievePage(PlanCriteria planCriteria, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.PlanService#putUpdate(wang.yongrui.wechat.
	 * entity.web.Plan)
	 */
	@Override
	public Plan putUpdate(Plan plan) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.PlanService#patchUpdate(wang.yongrui.wechat.
	 * entity.web.Plan)
	 */
	@Override
	public Plan patchUpdate(Plan plan) {
		// TODO Auto-generated method stub
		return null;
	}

}
