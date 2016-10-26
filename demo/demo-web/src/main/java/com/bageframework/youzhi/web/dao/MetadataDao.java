package com.bageframework.demo.web.dao;

import java.util.List;

import com.bageframework.coder.model.Column;
import com.bageframework.dao.base.IDAO;
import com.bageframework.demo.web.model.Metadata;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
public interface MetadataDao extends IDAO<Metadata, String> {

	public List<Column> getColumns(String table);
}
