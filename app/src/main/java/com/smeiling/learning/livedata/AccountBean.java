package com.smeiling.learning.livedata;

/**
 * @Author: Smeiling
 * @Date: 2019-02-20 17-23
 * @Description:
 */
public class AccountBean {

    private String name;
    private String phone;
    private String blog;

    public AccountBean(String name, String phone, String blog) {
        this.name = name;
        this.phone = phone;
        this.blog = blog;
    }

    public String getName() {
        return name == null ? "" : name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone == null ? "" : phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBlog() {
        return blog == null ? "" : blog;
    }

    public void setBlog(String blog) {
        this.blog = blog;
    }

    @Override
    public String toString() {
        return "AccountBean{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", blog='" + blog + '\'' +
                '}';
    }
}
