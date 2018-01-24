/**
 * 
 */
package wang.yongrui.wechat.service.impl;

import static org.springframework.beans.BeanUtils.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.wechat.entity.jpa.ActionEntity;
import wang.yongrui.wechat.entity.jpa.ActionEntity_;
import wang.yongrui.wechat.entity.jpa.ExerciseEntity;
import wang.yongrui.wechat.entity.jpa.ExerciseEntity_;
import wang.yongrui.wechat.entity.jpa.GroupEntity;
import wang.yongrui.wechat.entity.jpa.RealityEntity;
import wang.yongrui.wechat.entity.jpa.RealityEntity_;
import wang.yongrui.wechat.entity.jpa.UserEntity;
import wang.yongrui.wechat.entity.jpa.UserEntity_;
import wang.yongrui.wechat.entity.web.Exercise;
import wang.yongrui.wechat.entity.web.Group;
import wang.yongrui.wechat.entity.web.Reality;
import wang.yongrui.wechat.entity.web.criteria.RealityCriteria;
import wang.yongrui.wechat.repository.RealityRepository;
import wang.yongrui.wechat.repository.UserRepository;
import wang.yongrui.wechat.service.RealityService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class RealityServiceImpl implements RealityService {

	@Autowired
	private EntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RealityRepository realityRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.RealityService#create(wang.yongrui.wechat.
	 * entity.web.Reality)
	 */
	@Override
	public Reality create(Reality reality) {
		RealityEntity realityEntity = new RealityEntity();
		reality.setId(null);
		copyProperties(reality, realityEntity);
		UserEntity userEntity = userRepository.findOne(reality.getUser().getId());
		realityEntity.setUserEntity(userEntity);

		Set<ExerciseEntity> exerciseEntitySet = new LinkedHashSet<>();
		for (Exercise exercise : reality.getExerciseSet()) {
			ExerciseEntity exerciseEntity = new ExerciseEntity();
			exercise.setId(null);
			exercise.setForPlan(false);
			copyProperties(exercise, exerciseEntity);
			ActionEntity actionEntity = new ActionEntity();
			copyProperties(exercise.getAction(), actionEntity);
			exerciseEntity.setActionEntity(actionEntity);
			Set<GroupEntity> groupEntitySet = new LinkedHashSet<>();
			for (Group group : exercise.getGroupSet()) {
				GroupEntity groupEntity = new GroupEntity();
				group.setId(null);
				group.setForPlan(false);
				copyProperties(group, groupEntity);
				groupEntity.setExerciseEntity(exerciseEntity);
				groupEntitySet.add(groupEntity);
			}
			exerciseEntity.setGroupEntitySet(groupEntitySet);
			exerciseEntity.setRealityEntity(realityEntity);
			exerciseEntitySet.add(exerciseEntity);
		}

		realityEntity.setExerciseEntitySet(exerciseEntitySet);
		RealityEntity newRealityEntity = realityRepository.saveAndFlush(realityEntity);
		entityManager.refresh(newRealityEntity);

		return retrieveOne(newRealityEntity.getId());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.RealityService#retrieveOne(java.lang.Long)
	 */
	@Override
	public Reality retrieveOne(Long id) {
		RealityEntity realityEntity = realityRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			if (Long.class != criteriaQuery.getResultType()) {
				root.fetch(RealityEntity_.userEntity, JoinType.LEFT);
				root.fetch(RealityEntity_.exerciseEntitySet, JoinType.LEFT)
						.fetch(ExerciseEntity_.actionEntity, JoinType.LEFT)
						.fetch(ActionEntity_.partEntitySet, JoinType.LEFT);
				root.fetch(RealityEntity_.exerciseEntitySet, JoinType.LEFT).fetch(ExerciseEntity_.groupEntitySet,
						JoinType.LEFT);
			}
			return criteriaBuilder.equal(root.get(RealityEntity_.id), id);
		});

		return new Reality(realityEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.RealityService#retrievePage(wang.yongrui.
	 * wechat.entity.web.criteria.RealityCriteria,
	 * org.springframework.data.domain.Pageable)
	 */
	@Override
	public Page<Reality> retrievePage(RealityCriteria realityCriteria, Pageable pageable) {
		Page<RealityEntity> realityEntityPage = realityRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(RealityEntity_.userEntity, JoinType.LEFT);
			root.fetch(RealityEntity_.exerciseEntitySet, JoinType.LEFT)
					.fetch(ExerciseEntity_.actionEntity, JoinType.LEFT)
					.fetch(ActionEntity_.partEntitySet, JoinType.LEFT);
			root.fetch(RealityEntity_.exerciseEntitySet, JoinType.LEFT).fetch(ExerciseEntity_.groupEntitySet,
					JoinType.LEFT);

			Join<RealityEntity, UserEntity> courseJoin = root.join(RealityEntity_.userEntity);
			Predicate restrictions = criteriaBuilder.conjunction();
			if (null != realityCriteria.getUserId()) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.equal(courseJoin.get(UserEntity_.id), realityCriteria.getUserId()));
			}

			if (null != realityCriteria.getFromDate()) {
				restrictions = criteriaBuilder.and(restrictions, criteriaBuilder
						.greaterThanOrEqualTo(root.get(RealityEntity_.date), realityCriteria.getFromDate()));
			}
			if (null != realityCriteria.getToDate()) {
				restrictions = criteriaBuilder.and(restrictions,
						criteriaBuilder.lessThanOrEqualTo(root.get(RealityEntity_.date), realityCriteria.getToDate()));
			}
			return restrictions;
		}, pageable);

		List<Reality> realityList = new ArrayList<>();
		for (RealityEntity eachRealityEntity : realityEntityPage.getContent()) {
			realityList.add(new Reality(eachRealityEntity));
		}

		return new PageImpl<Reality>(realityList, pageable, realityEntityPage.getTotalElements());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.RealityService#putUpdate(wang.yongrui.wechat.
	 * entity.web.Reality)
	 */
	@Override
	public Reality putUpdate(Reality reality) {
		realityRepository.delete(reality.getId());
		return create(reality);
	}

}
