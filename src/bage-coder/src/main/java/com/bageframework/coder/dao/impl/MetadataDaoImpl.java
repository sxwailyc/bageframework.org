package com.bageframework.coder.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bageframework.coder.dao.MetadataDao;
import com.bageframework.coder.model.Column;
import com.bageframework.coder.model.Metadata;
import com.bageframework.dao.base.mysql.BaseMysqlDao;
import com.bageframework.dao.jdbc.Jdbc;

@Repository
public class MetadataDaoImpl extends BaseMysqlDao<Metadata> implements MetadataDao {

	@Autowired
	private Jdbc jdbc;

	@Override
	public List<Column> getColumns(String table) {
		String sql = "show full columns from `" + table + "`";
		return jdbc.getList(sql, Column.class);
	}

}
