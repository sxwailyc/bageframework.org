package com.bageframework.youzhi.web.vo.admin;

import org.springframework.beans.BeanUtils;
import com.bageframework.dao.annotation.AutoDate;
import com.bageframework.dao.annotation.PrimaryKey;
import com.bageframework.youzhi.web.model.Category;

import java.util.Date;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class CategoryAdminVO {

    @PrimaryKey
    private Integer id;
    
    private String name;
    
    private String remark;
    
    private Integer sort;
    
    @AutoDate
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
  
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
  
    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }
  
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public static CategoryAdminVO create(Category bean) {
		CategoryAdminVO vo = new CategoryAdminVO();
		BeanUtils.copyProperties(bean, vo);
		return vo;
	}

}