/**
 * 
 */
package com.phn.bean;


/**
 * @author 潘海南
 * @email phnlove@163.com
 */
public class Favor {
	//id为代理主键，因为这里的主键为复合主键，复合主键是focusid和用户id
	private int id;
	//这里的focusid不是自增长的，是和topic的id是一致的
	//private int focusid;
	//这里和topics是一对一
	private Topics topics;
	private Users users;
	/*private Integer countnewcomments;*/
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Topics getTopics() {
		return topics;
	}
	public void setTopics(Topics topics) {
		this.topics = topics;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
/*	public Integer getCountnewcomments() {
		return countnewcomments;
	}
	public void setCountnewcomments(Integer countnewcomments) {
		this.countnewcomments = countnewcomments;
	}*/
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj instanceof Favor) {
			Favor favor=(Favor)obj;
			if (this.id==favor.getId()) {
				return true;
			}else if (this.topics.equals(favor.getTopics())) {
				return true;
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
}
