/**
 * 
 */
package wang.yongrui.wechat.service.impl;

import static wang.yongrui.wechat.utils.EntityUtils.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.criteria.JoinType;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.wechat.entity.jpa.ActionEntity;
import wang.yongrui.wechat.entity.jpa.PartEntity;
import wang.yongrui.wechat.entity.jpa.PartEntity_;
import wang.yongrui.wechat.entity.jpa.UserEntity;
import wang.yongrui.wechat.entity.jpa.UserEntity_;
import wang.yongrui.wechat.entity.web.Part;
import wang.yongrui.wechat.repository.PartRepository;
import wang.yongrui.wechat.repository.UserRepository;
import wang.yongrui.wechat.service.PartService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class PartServiceImpl implements PartService {

	@Autowired
	private PartRepository partRepository;

	@Autowired
	private UserRepository userRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.PartService#createPredefinedPartSet(java.util
	 * .Set)
	 */
	@Override
	public boolean createPredefinedPartSet(Set<Part> partSet) {
		Set<PartEntity> partEntitySet = new HashSet<>();
		for (Part part : partSet) {
			PartEntity partEntity = new PartEntity();
			BeanUtils.copyProperties(part, partEntity);
			partEntity.setPredefined(true);
			partEntity.setActionEntitySet(getTargetSetFromSourceSet(part.getActionSet(), ActionEntity.class));
			partEntitySet.add(partEntity);
		}
		partRepository.save(partEntitySet);

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.PartService#createCustomedPartSet(java.util.
	 * Set, java.lang.Long)
	 */
	@Override
	public boolean createCustomedPartSet(Set<Part> partSet, Long userId) {
		boolean succeed = false;
		UserEntity userEntity = userRepository.findOne(userId);
		if (null != userEntity) {
			Set<PartEntity> partEntitySet = new HashSet<>();
			for (Part part : partSet) {
				PartEntity partEntity = new PartEntity();
				BeanUtils.copyProperties(part, partEntity);
				partEntity.setPredefined(false);
				partEntitySet.add(partEntity);
			}
			userEntity.setCustomedPartEntitySet(partEntitySet);
			userRepository.save(userEntity);
			succeed = true;
		}

		return succeed;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.PartService#retrieveAllPredefinedPartSet()
	 */
	@Override
	public Set<Part> retrieveAllPredefinedPartSet() {
		Set<Part> partSet = new HashSet<>();
		List<PartEntity> partEntityList = partRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			return criteriaBuilder.equal(root.get(PartEntity_.predefined), true);
		});
		for (PartEntity partEntity : partEntityList) {
			partSet.add(new Part(partEntity));
		}

		return partSet;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.PartService#retrieveAllCustomedPartSet(java.
	 * lang.Long)
	 */
	@Override
	public Set<Part> retrieveAllCustomedPartSet(Long userId) {
		Set<Part> partSet = new HashSet<>();
		UserEntity userEntity = userRepository.findOne((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(UserEntity_.customedPartEntitySet, JoinType.LEFT);
			return criteriaBuilder.equal(root.get(UserEntity_.id), userId);
		});
		for (PartEntity partEntity : userEntity.getCustomedPartEntitySet()) {
			partSet.add(new Part(partEntity));
		}

		return partSet;
	}

}
