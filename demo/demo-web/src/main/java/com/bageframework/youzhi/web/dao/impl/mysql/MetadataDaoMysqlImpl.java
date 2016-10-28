package com.bageframework.youzhi.web.dao.impl.mysql;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.bageframework.coder.model.Column;
import com.bageframework.dao.base.mysql.BaseMysqlDao;
import com.bageframework.dao.jdbc.Jdbc;
import com.bageframework.youzhi.web.dao.MetadataDao;
import com.bageframework.youzhi.web.model.Metadata;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Repository
public class MetadataDaoMysqlImpl extends BaseMysqlDao<Metadata> implements MetadataDao {

	@Autowired
	private Jdbc jdbc;

	@Override
	public List<Column> getColumns(String table) {
		String sql = "show full columns from `" + table + "`";
		return jdbc.getList(sql, Column.class);
	}
}
