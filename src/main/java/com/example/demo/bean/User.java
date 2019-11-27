package com.example.demo.bean;


public class User {
	private String id;
	private String pwd;
	private String name;
	private String boke_name;
	private String avatar;
	private String index_one;
	private String index_two;
	private String index_three;
    private String routes;
    private String roles;

    public String getRoutes() {
        return routes;
    }
    public void setRoutes(String routes) {
        this.routes = routes;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }

	public String getIndex_one() {
		return index_one;
	}
	public void setIndex_one(String index_one) {
		this.index_one = index_one;
	}
	public String getIndex_two() {
		return index_two;
	}
	public void setIndex_two(String index_two) {
		this.index_two = index_two;
	}
	public String getIndex_three() {
		return index_three;
	}
	public void setIndex_three(String index_three) {
		this.index_three = index_three;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	
	
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBoke_name() {
		return boke_name;
	}
	public void setBoke_name(String boke_name) {
		this.boke_name = boke_name;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", pwd=" + pwd + ", name=" + name + ", boke_name=" + boke_name + ", boke_head_img="
				+ ", avatar=" + avatar + ", index_one=" + index_one + ", index_two=" + index_two
				+ ", index_three=" + index_three + "]";
	}
	
	
	
	
	
}
