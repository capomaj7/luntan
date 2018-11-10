/**
 * 
 */
package com.phn.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.phn.bean.Focus;
import com.phn.bean.Users;

/**
 * @author 潘海南
 * @email phnlove@163.com
 */
public interface FocusService {

	/**
	 * @param dc
	 * @return
	 */
	List<Focus> getFocusList(DetachedCriteria dc);

	/**
	 * @param focus
	 */
	void save(Focus focus);

	/**
	 * @param focus2
	 */
	void deleteFocus(Focus focus2);

	/**
	 * @param dc
	 * @return
	 */

}
