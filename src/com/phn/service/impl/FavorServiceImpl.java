/**
 * 
 */
package com.phn.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.phn.bean.Favor;
import com.phn.bean.PageBean;
import com.phn.dao.FavorDao;
import com.phn.service.FavorService;


public class FavorServiceImpl implements FavorService {
	private FavorDao favorDao;

	public void setFavorDao(FavorDao favorDao) {
		this.favorDao = favorDao;
	}

	/* (non-Javadoc)
	 * @see com.phn.service.FavorService#saveFavor(com.phn.bean.Favor)
	 */
	@Override
	public void saveFavor(Favor favor) {
		// TODO Auto-generated method stub
		favorDao.save(favor);
	}

	/* (non-Javadoc)
	 * @see com.phn.service.FavorService#findFavorByTidAndUid(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public List<Favor> findFavorByTidAndUid(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return favorDao.getAllByDc(dc);
	}

	
	@Override
	public void deleteByTidAndUid(Favor favor2) {
		// TODO Auto-generated method stub
		favorDao.delete(favor2);
	}

	/* (non-Javadoc)
	 * @see com.phn.service.FavorService#findAllFavorsByUserId(org.hibernate.criterion.DetachedCriteria)
	 */
	@Override
	public List<Favor> findAllFavorsByUserId(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return favorDao.getAllByDc(dc);
	}

	/* (non-Javadoc)
	 * @see com.phn.service.FavorService#getPageBeanByUserId(org.hibernate.criterion.DetachedCriteria, java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public PageBean<Favor> getPageBeanByUserId(DetachedCriteria dc,
			Integer currentPage, Integer pageSize) {
		// TODO Auto-generated method stub
		Integer totalCount =favorDao.getTotalCount(dc);
		PageBean<Favor>pageBean=new PageBean<>(currentPage, totalCount, pageSize);
		List<Favor>favors=favorDao.getPageList(dc, pageBean.getStart(), pageBean.getPageSize());
		pageBean.setList(favors);
		return pageBean;
	}

	
}
