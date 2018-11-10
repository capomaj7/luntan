package com.phn.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.phn.bean.Focus;
import com.phn.bean.Users;
import com.phn.service.FocusService;
import com.phn.service.UserService;

@SuppressWarnings("all")
public class FocusAction extends ActionSupport implements ModelDriven<Focus> {
	private FocusService focusService;
	private UserService userService;
	private Focus focus = new Focus();
	HttpSession session = ServletActionContext.getRequest().getSession();
	HttpServletResponse response = ServletActionContext.getResponse();
	HttpServletRequest request = ServletActionContext.getRequest();
	private Users user;
	//需要生成service的set方法
	
	//关注别人
	public String FocusPerson() throws Exception {
		Users us = (Users) session.getAttribute("tu");
		//user.getid()是传过来的值，session中的是用户自己的
		//离线查询的方法
		String msg="";
		int isfocus=1;
		if (user.getId()==us.getId()) {
			msg="自己不能关注自己";
			isfocus=0;
		}else {
			DetachedCriteria dc=DetachedCriteria.forClass(Focus.class);
			dc.add(Restrictions.eq("focusid", user.getId())).add(Restrictions.eq("users.id", us.getId()));
			//通过自己和关注者的id来找相关的记录
			List<Focus>focusList=focusService.getFocusList(dc);
			if (focusList==null||focusList.size()<=0) {
				System.out.println("结果为空");
				focus.setFocusid(user.getId());
				focus.setUsers(us);
				focusService.save(focus);
			}else {
				System.out.println("结果不为空");
				System.out.println(focusList);
				msg="已经关注此人了";
			}
		}
		this.user=userService.find(user.getId());
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("isfocus", isfocus);
		ActionContext.getContext().put("focusmsg", msg);
		return "user_goUser_ok";
	}
	//取消关注别人
	public String UnFocusPerson() throws Exception {
		Users us = (Users) session.getAttribute("tu");
		//user.getid()是传过来的值，session中的是用户自己的
		//离线查询的方法
		String msg="";
		int isfocus=0;
		if (user.getId()==us.getId()) {
			msg="自己不能关注自己";
			isfocus=1;
		}else {
			DetachedCriteria dc=DetachedCriteria.forClass(Focus.class);
			dc.add(Restrictions.eq("focusid", user.getId())).add(Restrictions.eq("users.id", us.getId()));
			//通过自己和关注者的id来找相关的记录
			List<Focus>focusList=focusService.getFocusList(dc);
			if (focusList==null||focusList.size()<=0) {
				/*focus.setFocusid(user.getId());
				focus.setUsers(us);
				focusService.save(focus);*/
				msg="已经取关此人了";
			}else  {
				Focus focus2=focusList.get(0);
				focusService.deleteFocus(focus2);
				
			}
		}
		this.user=userService.find(user.getId());
		ActionContext.getContext().put("user", user);
		ActionContext.getContext().put("isfocus", isfocus);
		ActionContext.getContext().put("focusmsg", msg);
		return "user_goUser_ok";
	}
	//获取我关注的人的集合
	public String GetFollowings() throws Exception {
		Users us = (Users) session.getAttribute("tu");
		DetachedCriteria dc=DetachedCriteria.forClass(Focus.class);
		dc.add(Restrictions.eq("users.id", us.getId()));
		List<Focus>focusList=focusService.getFocusList(dc);
		List<Users>userList=new ArrayList<Users>();
		if (focusList!=null&&focusList.size()>0) {
			for(Focus fs:focusList) {
				Users user=userService.find(fs.getFocusid());
				userList.add(user);
			}
		}
		ActionContext.getContext().put("users", userList);
		return "followeringslist";
	}
	//关注我的人的集合
	public String GetFollowers() throws Exception {
		Users us = (Users) session.getAttribute("tu");
		DetachedCriteria dc=DetachedCriteria.forClass(Focus.class);
		dc.add(Restrictions.eq("focusid", us.getId()));
		List<Focus>focusList=focusService.getFocusList(dc);
		List<Users>userList=new ArrayList<Users>();
		if (focusList!=null&&focusList.size()>0) {
			for(Focus fs:focusList) {
				Users user=fs.getUsers();
				userList.add(user);
			}
		}
		ActionContext.getContext().put("users", userList);
		return "followerslist";
	}
	@Override
	public Focus getModel() {
		// TODO Auto-generated method stub
		return focus;
	}

	public void setFocusService(FocusService focusService) {
		this.focusService = focusService;
	}
	public Users getUser() {
		return user;
	}
	public void setUser(Users user) {
		this.user = user;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
