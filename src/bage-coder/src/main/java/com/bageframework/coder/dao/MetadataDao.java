package com.bageframework.coder.dao;

import java.util.List;

import com.bageframework.coder.model.Column;
import com.bageframework.coder.model.Metadata;
import com.bageframework.data.base.IDAO;

public interface MetadataDao extends IDAO<Metadata, String> {

	public List<Column> getColumns(String table);

}
