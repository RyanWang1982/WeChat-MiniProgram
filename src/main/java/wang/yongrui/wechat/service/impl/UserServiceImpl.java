/**
 * 
 */
package wang.yongrui.wechat.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.JoinType;
import javax.persistence.metamodel.Attribute;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.wechat.entity.jpa.ActionEntity_;
import wang.yongrui.wechat.entity.jpa.CircleDayEntity_;
import wang.yongrui.wechat.entity.jpa.ExerciseEntity_;
import wang.yongrui.wechat.entity.jpa.ExtendedInfoEntity;
import wang.yongrui.wechat.entity.jpa.PlanEntity_;
import wang.yongrui.wechat.entity.jpa.RoleEntity;
import wang.yongrui.wechat.entity.jpa.UserEntity;
import wang.yongrui.wechat.entity.jpa.UserEntity_;
import wang.yongrui.wechat.entity.web.ExtendedInfo;
import wang.yongrui.wechat.entity.web.Role;
import wang.yongrui.wechat.entity.web.User;
import wang.yongrui.wechat.repository.RoleRepository;
import wang.yongrui.wechat.repository.UserRepository;
import wang.yongrui.wechat.service.UserService;
import wang.yongrui.wechat.utils.PatchBeanUtils;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#
	 * loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User(userRepository.findByLoginName(username));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.wechat.service.UserService#create(com.sap.cloud.
	 * scp.sso.security.entity.web.User)
	 */
	@Override
	public User create(User user) {
		UserEntity userEntity = new UserEntity();
		copyProperties(user, userEntity);
		UserEntity newUserEntity = userRepository.saveAndFlush(userEntity);

		return new User(newUserEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.UserService#retrieveOneByWechatMPOpenId(java.
	 * lang.String)
	 */
	@Override
	public User retrieveOneByWechatMPOpenId(String wechatMPOpenId) {
		return new User(userRepository.findByWechatMPOpenId(wechatMPOpenId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.UserService#retrieveOneByWechatUnionId(java.
	 * lang.String)
	 */
	@Override
	public User retrieveOneByWechatUnionId(String wechatUnionId) {
		return new User(userRepository.findByWechatUnionId(wechatUnionId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.UserService#retrieveOneWithPlan(java.lang.
	 * Long)
	 */
	@Override
	public User retrieveOneWithPlan(Long id) {
		UserEntity userEntity = userRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			if (Long.class != criteriaQuery.getResultType()) {
				root.fetch(UserEntity_.planEntitySet, JoinType.LEFT)
						.fetch(PlanEntity_.circleDayEntitySet, JoinType.LEFT)
						.fetch(CircleDayEntity_.exerciseEntitySet, JoinType.LEFT)
						.fetch(ExerciseEntity_.actionEntity, JoinType.LEFT)
						.fetch(ActionEntity_.partEntitySet, JoinType.LEFT);
				root.fetch(UserEntity_.planEntitySet, JoinType.LEFT)
						.fetch(PlanEntity_.circleDayEntitySet, JoinType.LEFT)
						.fetch(CircleDayEntity_.exerciseEntitySet, JoinType.LEFT)
						.fetch(ExerciseEntity_.groupEntitySet, JoinType.LEFT);
			}
			return criteriaBuilder.equal(root.get(UserEntity_.id), id);
		});

		Set<Attribute<?, ?>> includedAttributeSet = new HashSet<>();
		includedAttributeSet.add(UserEntity_.planEntitySet);
		return new User(userEntity, includedAttributeSet);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.wechat.service.UserService#putUpdate(com.sap.
	 * cloud.scp.sso.security.entity.web.User)
	 */
	@Override
	public User putUpdate(User user) {
		User updatedUser = null;
		if (null != user.getId()) {
			UserEntity userEntity = userRepository.findOne(user.getId());
			if (null != userEntity) {
				putProperties(user, userEntity);
				updatedUser = new User(userRepository.saveAndFlush(userEntity));
			}
		}

		return updatedUser;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.wechat.service.UserService#patchUpdate(com.sap.
	 * cloud.scp.sso.security.entity.web.User)
	 */
	@Override
	public User patchUpdate(User user) {
		User updatedUser = null;
		if (null != user.getId()) {
			UserEntity userEntity = userRepository.findOne(user.getId());
			if (null != userEntity) {
				patchProperties(user, userEntity);
				updatedUser = new User(userRepository.saveAndFlush(userEntity));
			}
		}

		return updatedUser;
	}

	/**
	 * @param user
	 * @param userEntity
	 */
	private void copyProperties(User user, UserEntity userEntity) {
		if (null != user) {
			if (CollectionUtils.isNotEmpty(user.getRoleSet())) {
				Set<RoleEntity> roleEntitySet = new HashSet<>();
				for (Role eachRole : user.getRoleSet()) {
					RoleEntity eachRoleEntity = new RoleEntity();
					BeanUtils.copyProperties(eachRole, eachRoleEntity);
					roleEntitySet.add(eachRoleEntity);
				}
				userEntity.setRoleEntitySet(roleEntitySet);
			} else {
				userEntity.setRoleEntitySet(null);
			}

			if (null != user.getExtendedInfoMap()) {
				List<ExtendedInfoEntity> extendedInfoEntityList = new ArrayList<>();
				Set<String> nameSet = new HashSet<>();
				for (String eachKey : user.getExtendedInfoMap().keySet()) {
					if (!nameSet.contains(eachKey)) {
						ExtendedInfoEntity eachExtendedInfoEntity = new ExtendedInfoEntity();
						BeanUtils.copyProperties(user.getExtendedInfoMap().get(eachKey), eachExtendedInfoEntity);
						eachExtendedInfoEntity.setName(eachKey);
						extendedInfoEntityList.add(eachExtendedInfoEntity);
					}
					nameSet.add(eachKey);
				}
				userEntity.setExtendedInfoEntityList(extendedInfoEntityList);
			} else {
				userEntity.setExtendedInfoEntityList(null);
			}

			BeanUtils.copyProperties(user, userEntity);
		}
	}

	/**
	 * PUT means add new properties, replace existing properties, remove other
	 * properties
	 * 
	 * @param user
	 * @param userEntity
	 */
	private void putProperties(User user, UserEntity userEntity) {
		if (null != user) {
			updateRole(user, userEntity, true);
			updateExtendedInfo(user, userEntity, true);
			BeanUtils.copyProperties(user, userEntity);
		}
	}

	/**
	 * PATCH means only update not-null properties and add new ones
	 * 
	 * @param user
	 * @param userEntity
	 */
	private void patchProperties(User user, UserEntity userEntity) {
		if (null != user) {
			updateRole(user, userEntity, false);
			updateExtendedInfo(user, userEntity, false);
			PatchBeanUtils.patchProperties(user, userEntity);
		}
	}

	/**
	 * @param user
	 * @param userEntity
	 * @param isPutUpdate
	 */
	private void updateRole(User user, UserEntity userEntity, boolean isPutUpdate) {
		if (CollectionUtils.isNotEmpty(user.getRoleSet())) {
			Set<RoleEntity> roleEntitySet = (CollectionUtils.isNotEmpty(userEntity.getRoleEntitySet()) && !isPutUpdate)
					? userEntity.getRoleEntitySet() : new HashSet<>();

			List<Long> roleIdList = new ArrayList<>();
			for (Role eachRole : user.getRoleSet()) {
				if (null != eachRole.getId()) {
					roleIdList.add(eachRole.getId());
				}
			}
			if (CollectionUtils.isNotEmpty(roleIdList)) {
				List<RoleEntity> roleEntityList = roleRepository.findAll(roleIdList);
				roleEntitySet.addAll(roleEntityList);
				if (isPutUpdate) {
					roleEntitySet.retainAll(roleEntityList);
				}
				userEntity.setRoleEntitySet(roleEntitySet);
			}
		} else if (isPutUpdate) {
			userEntity.getRoleEntitySet().clear();
		}
	}

	/**
	 * This method is only for PUT or PATCH updating
	 * 
	 * @param user
	 * @param userEntity
	 * @param isPutUpdate
	 */
	private void updateExtendedInfo(User user, UserEntity userEntity, boolean isPutUpdate) {
		if (null != user.getExtendedInfoMap()) {
			List<ExtendedInfoEntity> existExtendedInfoEntityList = userEntity.getExtendedInfoEntityList();
			List<ExtendedInfoEntity> toBeRemovedExtendedInfoEntityList = new ArrayList<>();
			toBeRemovedExtendedInfoEntityList.addAll(existExtendedInfoEntityList);
			Set<String> nameSet = new HashSet<>();
			Set<ExtendedInfoEntity> stayExtendedInfoEntitySet = new HashSet<>();
			for (ExtendedInfoEntity eachExistExtendedInfoEntity : existExtendedInfoEntityList) {
				for (String eachKey : user.getExtendedInfoMap().keySet()) {
					ExtendedInfo eachExtendedInfo = user.getExtendedInfoMap().get(eachKey);
					if (null != eachExtendedInfo.getId()
							&& eachExistExtendedInfoEntity.getId().equals(eachExtendedInfo.getId())) {
						// Update exist ones
						PatchBeanUtils.updateProperties(eachExtendedInfo, eachExistExtendedInfoEntity, isPutUpdate);
						eachExistExtendedInfoEntity.setName(eachKey);
						stayExtendedInfoEntitySet.add(eachExistExtendedInfoEntity);
						nameSet.add(eachExistExtendedInfoEntity.getName());
						break;
					}
				}
			}

			// Remove others
			/*
			 * Below code does not work, will check later
			 * existExtendedInfoEntityList.retainAll(stayExtendedInfoEntitySet);
			 */
			toBeRemovedExtendedInfoEntityList.removeAll(stayExtendedInfoEntitySet);
			existExtendedInfoEntityList.removeAll(toBeRemovedExtendedInfoEntityList);

			for (String eachKey : user.getExtendedInfoMap().keySet()) {
				ExtendedInfo eachExtendedInfo = user.getExtendedInfoMap().get(eachKey);
				if (null == eachExtendedInfo.getId() && !nameSet.contains(eachKey)) {
					// Add new ones
					ExtendedInfoEntity extendedInfoEntity = new ExtendedInfoEntity();
					PatchBeanUtils.updateProperties(eachExtendedInfo, extendedInfoEntity, isPutUpdate);
					extendedInfoEntity.setName(eachKey);
					existExtendedInfoEntityList.add(extendedInfoEntity);
				}
				nameSet.add(eachKey);
			}
			userEntity.setExtendedInfoEntityList(existExtendedInfoEntityList);
		} else if (isPutUpdate) {
			// DO NOT USE userEntity.setExtendedInfoEntityList(null),
			// this may cause: "A collection with cascade="all-delete-orphan"
			// was no longer referenced by the owning entity instance" error
			userEntity.getExtendedInfoEntityList().clear();
		}
	}

}
