/**
 * 
 */
package com.phn.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.phn.bean.Focus;
import com.phn.bean.Users;
import com.phn.dao.FocusDao;
import com.phn.service.FocusService;

/**
 * @author 潘海南
 * @email phnlove@163.com
 */
public class FocusServiceImpl implements FocusService{
	private FocusDao focusDao;
	public void setFocusDao(FocusDao focusDao) {
		this.focusDao = focusDao;
	}
	/* (non-Javadoc)
	 * @see com.phn.service.FocusService#getUser(org.hibernate.criterion.DetachedCriteria)
	 */
	/* (non-Javadoc)
	 * @see com.phn.service.FocusService#getFocusList(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public List<Focus> getFocusList(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return focusDao.getAllByDc(dc);
	}
	/* (non-Javadoc)
	 * @see com.phn.service.FocusService#save(com.phn.bean.Focus)
	 */
	@Override
	public void save(Focus focus) {
		// TODO Auto-generated method stub
		focusDao.save(focus);
	}
	
	@Override
	public void deleteFocus(Focus focus2) {
		// TODO Auto-generated method stub
		focusDao.delete(focus2);
	}
	
	
}
