package com.bageframework.coder.dao;

import java.util.List;

import com.bageframework.coder.model.Column;

public interface MetadataDao {

	public List<Column> getColumns(String table);

}
