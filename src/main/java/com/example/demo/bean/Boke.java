package com.example.demo.bean;


public class Boke {
	private Integer id;
	private String title;
	private String sim_content;
	private String content;
	private String author;
	private Integer watch_num;
	private String create_time;
	private Boolean is_enabled;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getSim_content() {
		return sim_content;
	}
	public void setSim_content(String sim_content) {
		this.sim_content = sim_content;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Integer getWatch_num() {
		return watch_num;
	}
	public void setWatch_num(Integer watch_num) {
		this.watch_num = watch_num;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public Boolean getIs_enabled() {
		return is_enabled;
	}
	public void setIs_enabled(Boolean is_enabled) {
		this.is_enabled = is_enabled;
	}
	@Override
	public String toString() {
		return "Boke [id=" + id + ", title=" + title + ", sim_content=" + sim_content + ", content=" + content
				+ ", author=" + author + ", watch_num=" + watch_num + ", create_time=" + create_time + ", is_enabled="
				+ is_enabled + "]";
	}
	public Boke(String title, String sim_content, String content, String author, Integer watch_num, String create_time,
			Boolean is_enabled) {
		super();
		this.title = title;
		this.sim_content = sim_content;
		this.content = content;
		this.author = author;
		this.watch_num = watch_num;
		this.create_time = create_time;
		this.is_enabled = is_enabled;
	}
	public Boke() {
		super();
	}
	
	
}
