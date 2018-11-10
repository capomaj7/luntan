/**
 * 
 */
package com.phn.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.phn.bean.Favor;
import com.phn.bean.PageBean;

/**
 * @author 潘海南
 * @email phnlove@163.com
 */
public interface FavorService {

	/**
	 * @param favor
	 */
	void saveFavor(Favor favor);

	/**
	 * @param dc
	 * @return
	 */
	List<Favor> findFavorByTidAndUid(DetachedCriteria dc);

	/**
	 * @param favor2
	 */
	void deleteByTidAndUid(Favor favor2);

	/**
	 * @param dc
	 * @return
	 */
	List<Favor> findAllFavorsByUserId(DetachedCriteria dc);

	/**
	 * @param dc
	 * @param currentPage
	 * @param pageSize
	 * @return
	 */
	PageBean<Favor> getPageBeanByUserId(DetachedCriteria dc,
			Integer currentPage, Integer pageSize);

}
