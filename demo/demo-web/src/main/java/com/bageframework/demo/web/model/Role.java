package com.bageframework.demo.web.model;


import java.util.Date;


/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class Role {


    private Integer id;

    private String name;

    private Date createdTime;
  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

}