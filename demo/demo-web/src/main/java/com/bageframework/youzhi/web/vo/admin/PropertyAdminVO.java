package com.bageframework.youzhi.web.vo.admin;

import org.springframework.beans.BeanUtils;

import com.bageframework.youzhi.web.model.Property;

import java.util.Date;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class PropertyAdminVO {

    private Integer id;
    
    private String metadataId;
    
    private String name;
    
    private String type;
    
    private String remark;
    
    private Integer search;
    
    private Integer edit;
    
    private Integer show;
    
    private Integer sort;
    
    private Date createdTime;
    
    private Integer formType;
    
  
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
  
    public String getMetadataId() {
        return metadataId;
    }

    public void setMetadataId(String metadataId) {
        this.metadataId = metadataId;
    }
  
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
  
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
  
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
  
    public Integer getSearch() {
        return search;
    }

    public void setSearch(Integer search) {
        this.search = search;
    }
  
    public Integer getEdit() {
        return edit;
    }

    public void setEdit(Integer edit) {
        this.edit = edit;
    }
  
    public Integer getShow() {
        return show;
    }

    public void setShow(Integer show) {
        this.show = show;
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
  
    public Integer getFormType() {
        return formType;
    }

    public void setFormType(Integer formType) {
        this.formType = formType;
    }

    public static PropertyAdminVO create(Property bean) {
		PropertyAdminVO vo = new PropertyAdminVO();
		BeanUtils.copyProperties(bean, vo);
		return vo;
	}

}