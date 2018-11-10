/**
 * 
 */
package com.phn.bean;

//关注者的类
public class Focus {
	//这个id使用序号id，没有其他的含义
	private int id;
	//关注的人的id
	private int focusid;
	//被关注者或者自己
	private Users users;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	public int getFocusid() {
		return focusid;
	}
	public void setFocusid(int focusid) {
		this.focusid = focusid;
	}

}
