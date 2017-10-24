/**
 * 
 */
package wang.yongrui.wechat.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import wang.yongrui.wechat.entity.web.Action;
import wang.yongrui.wechat.repository.ActionRepository;
import wang.yongrui.wechat.service.ActionService;

/**
 * @author Wang Yongrui
 *
 */
@Service
@Transactional
public class ActionServiceImpl implements ActionService {

	@Autowired
	private ActionRepository actionRepository;

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.ActionService#createCustomedOne(wang.yongrui.
	 * wechat.entity.web.Action)
	 */
	@Override
	public Action createCustomedOne(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.ActionService#retrieveAllCustomedSetByUser(
	 * java.lang.Long)
	 */
	@Override
	public Set<Action> retrieveAllCustomedSetByUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.ActionService#putUpdate(wang.yongrui.wechat.
	 * entity.web.Action)
	 */
	@Override
	public Action putUpdate(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * wang.yongrui.wechat.service.ActionService#patchUpdate(wang.yongrui.wechat
	 * .entity.web.Action)
	 */
	@Override
	public Action patchUpdate(Action action) {
		// TODO Auto-generated method stub
		return null;
	}

}
