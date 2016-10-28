package com.bageframework.youzhi.web.vo;

import com.bageframework.dao.annotation.AutoDate;
import java.util.Date;
import com.bageframework.dao.annotation.PrimaryKey;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class SpiderTaskVO {

    @PrimaryKey
    private Integer id;
    
    private Integer type;
    
    private String param;
    
    @AutoDate
    private Date createdTime;
    
    private Date lastSpiderTime;
    
    private Integer status;
    
  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
  
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
  
    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }
  
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
  
    public Date getLastSpiderTime() {
        return lastSpiderTime;
    }

    public void setLastSpiderTime(Date lastSpiderTime) {
        this.lastSpiderTime = lastSpiderTime;
    }
  
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}