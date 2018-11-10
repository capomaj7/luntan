package com.phn.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.phn.bean.Favor;
import com.phn.bean.PageBean;
import com.phn.bean.Topics;
import com.phn.bean.Users;
import com.phn.service.FavorService;
import com.phn.service.TopicService;
import com.phn.service.UserService;

@SuppressWarnings("all")
public class FavorAction extends ActionSupport implements ModelDriven<Favor> {
	private FavorService favorService;
	private TopicService topicService;
	private UserService userService;
	private Integer currentPage;
	private Integer pageSize;
	private Favor favor = new Favor();
	private Topics topic;
	//收藏帖子功能
	public String addFavorByTopicidAndUserid() throws Exception {
		Users user=userService.find(favor.getUsers().getId());
		Topics topic2=topicService.find(topic.getId());
		favor.setUsers(user);
		favor.setTopics(topic2);
		favorService.saveFavor(favor);
		return "topic_goTopic";
	}
	//取消收藏帖子的功能
	public String deleteFavorByTopicidAndUserid() throws Exception {
		DetachedCriteria dc=DetachedCriteria.forClass(Favor.class);
		dc.add(Restrictions.eq("topics.id", topic.getId()))
		.add(Restrictions.eq("users.id", favor.getUsers().getId()));
		List<Favor>favors=favorService.findFavorByTidAndUid(dc);
		if (favors==null||favors.size()<=0||favors.size()>1) {
			return "topic_goTopic";
		}
		Favor favor2=favors.get(0);
		favorService.deleteByTidAndUid(favor2);
		return "topic_goTopic";
	}
	//获取当前用户的收藏帖子，并且进行分页
	public String GetFavors() throws Exception {
		Users user=(Users) ActionContext.getContext().getSession().get("tu");
		DetachedCriteria dc =DetachedCriteria.forClass(Favor.class);
		dc.add(Restrictions.eq("users.id", user.getId()));
		PageBean<Favor> pageBean=favorService.getPageBeanByUserId(dc,currentPage,pageSize);
		ActionContext.getContext().put("pageBean", pageBean);
		return "favorlist";
	}
	@Override
	public Favor getModel() {
		// TODO Auto-generated method stub
		return favor;
	}

	public void setFavorService(FavorService favorService) {
		this.favorService = favorService;
	}

	public void setTopicService(TopicService topicService) {
		this.topicService = topicService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public Topics getTopic() {
		return topic;
	}

	public void setTopic(Topics topic) {
		this.topic = topic;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
