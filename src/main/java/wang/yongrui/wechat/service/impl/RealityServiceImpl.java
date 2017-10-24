/**
 * 
 */
package wang.yongrui.wechat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.wechat.entity.web.Reality;
import wang.yongrui.wechat.entity.web.criteria.RealityCriteria;
import wang.yongrui.wechat.repository.RealityRepository;
import wang.yongrui.wechat.service.RealityService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class RealityServiceImpl implements RealityService {

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
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.RealityService#retrieveOne(java.lang.String)
	 */
	@Override
	public Reality retrieveOne(String id) {
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
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
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see wang.yongrui.wechat.service.RealityService#patchUpdate(wang.yongrui.
	 * wechat.entity.web.Reality)
	 */
	@Override
	public Reality patchUpdate(Reality reality) {
		// TODO Auto-generated method stub
		return null;
	}

}
