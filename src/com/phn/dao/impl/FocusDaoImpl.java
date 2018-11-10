/**
 * 
 */
package com.phn.dao.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.phn.bean.Focus;
import com.phn.dao.FocusDao;

/**
 * @author 潘海南
 * @email phnlove@163.com
 */
//注解控制相应的事务的
public class FocusDaoImpl extends BaseDaoImpl<Focus> implements FocusDao{

	
}
