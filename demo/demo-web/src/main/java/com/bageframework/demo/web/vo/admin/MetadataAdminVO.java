package com.bageframework.demo.web.vo.admin;

import org.springframework.beans.BeanUtils;
import java.util.Date;
import com.bageframework.demo.web.model.Metadata;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public class MetadataAdminVO {

    private String id;
    
    private String table;
    
    private String javaClass;
    
    private String remark;
    
    private Date createdTime;
    
  
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
  
    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }
  
    public String getJavaClass() {
        return javaClass;
    }

    public void setJavaClass(String javaClass) {
        this.javaClass = javaClass;
    }
  
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
  
    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public static MetadataAdminVO create(Metadata bean) {
		MetadataAdminVO vo = new MetadataAdminVO();
		BeanUtils.copyProperties(bean, vo);
		return vo;
	}

}