/**
 * 
 */
package wang.yongrui.wechat.service.impl;

import static org.springframework.beans.BeanUtils.*;
import static wang.yongrui.wechat.utils.EntityUtils.*;
import static wang.yongrui.wechat.utils.PatchBeanUtils.*;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.criteria.JoinType;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.wechat.entity.jpa.ActionEntity;
import wang.yongrui.wechat.entity.jpa.PartEntity;
import wang.yongrui.wechat.entity.jpa.PartEntity_;
import wang.yongrui.wechat.entity.web.Action;
import wang.yongrui.wechat.entity.web.Part;
import wang.yongrui.wechat.repository.PartRepository;
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

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.PartService#createPredefinedSet(java.util.
	 * Set)
	 */
	@Override
	public boolean createPredefinedSet(Set<Part> partSet) {
		if (CollectionUtils.isNotEmpty(partSet)) {
			Set<PartEntity> partEntitySet = new LinkedHashSet<>();
			for (Part part : partSet) {
				PartEntity partEntity = new PartEntity();
				copyProperties(part, partEntity);
				if (CollectionUtils.isNotEmpty(part.getActionSet())) {
					Set<ActionEntity> actionEntitySet = new LinkedHashSet<>();
					for (Action action : part.getActionSet()) {
						ActionEntity actionEntity = new ActionEntity();
						copyProperties(action, actionEntity);
						actionEntitySet.add(actionEntity);
					}
					partEntity.setActionEntitySet(actionEntitySet);
				}
				partEntitySet.add(partEntity);
			}
			partRepository.save(partEntitySet);
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.wechat.service.PartService#retrieveAllPredefinedSet()
	 */
	@Override
	public Set<Part> retrieveAllPredefinedSet() {
		Set<PartEntity> partEntitySet = new LinkedHashSet<>();
		partEntitySet.addAll(partRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			criteriaQuery.distinct(true);
			root.fetch(PartEntity_.actionEntitySet, JoinType.LEFT);
			return criteriaBuilder.equal(root.get(PartEntity_.predefined), true);
		}));

		return getObjectSetFromEntitySetWithHeritage(partEntitySet, PartEntity.class, Part.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.PartService#putUpdate(wang.yongrui.wechat.
	 * entity.web.Part)
	 */
	@Override
	public Part putUpdate(Part part) {
		PartEntity partEntity = partRepository.findOne(part.getId());
		updateProperties(part, partEntity, false);
		partEntity = partRepository.saveAndFlush(partEntity);
		return new Part(partEntity);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.PartService#patchUpdate(wang.yongrui.wechat.
	 * entity.web.Part)
	 */
	@Override
	public Part patchUpdate(Part part) {
		PartEntity partEntity = partRepository.findOne(part.getId());
		updateProperties(part, partEntity, true);
		partEntity = partRepository.saveAndFlush(partEntity);
		return new Part(partEntity);
	}

}
