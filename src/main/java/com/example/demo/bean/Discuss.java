package com.example.demo.bean;

/**
 * 评论
 * 实现思路：
 * 1.不设时间字段，直接按id排序,id自增
 * 2.回复直接显示。例如： 夏末：我觉得我很帅！ 墨夏 回复 夏末 ： 我也觉得！确实很帅！
 */
public class Discuss {
    private int id;
    //评论的博客
    private int boke_id;
    //发表的人
    private String account_id;
    private String account_name;
    private String account_avatar;
    private String text;
    //回复的人
    private String to_account_id;
    //在根据id查找后查找名字放入
    private String to_account_name;
    private String to_account_avatar;

    public int getId() {
        return id;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
    }

    public String getAccount_avatar() {
        return account_avatar;
    }

    public void setAccount_avatar(String account_avatar) {
        this.account_avatar = account_avatar;
    }

    public String getTo_account_avatar() {
        return to_account_avatar;
    }

    public void setTo_account_avatar(String to_account_avatar) {
        this.to_account_avatar = to_account_avatar;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBoke_id() {
        return boke_id;
    }

    public void setBoke_id(int boke_id) {
        this.boke_id = boke_id;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTo_account_id() {
        return to_account_id;
    }

    public void setTo_account_id(String to_account_id) {
        this.to_account_id = to_account_id;
    }

    public String getTo_account_name() {
        return to_account_name;
    }

    public void setTo_account_name(String to_account_name) {
        this.to_account_name = to_account_name;
    }

    @Override
    public String toString() {
        return "Discuss{" +
                "id=" + id +
                ", boke_id=" + boke_id +
                ", account_id='" + account_id + '\'' +
                ", text='" + text + '\'' +
                ", to_account_id='" + to_account_id + '\'' +
                ", to_account_name='" + to_account_name + '\'' +
                '}';
    }
}
