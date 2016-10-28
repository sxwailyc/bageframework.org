package com.bageframework.data.jdbc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.ResultSetExtractor;

import com.bageframework.data.helper.SQLHelper;
import com.bageframework.data.sql.SQL;

public class JdbcImpl implements Jdbc {

	private static Logger logger = Logger.getLogger(JdbcImpl.class);

	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public int update(final String sql, final SqlParameter parameter) {

		long start = System.currentTimeMillis();

		int effectRow = this.jdbcTemplate.update(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				if (parameter != null) {
					for (Entry<Integer, Object> entry : parameter.getParams().entrySet()) {
						ps.setObject(entry.getKey(), entry.getValue());
					}
				}
			}
		});

		if (logger.isDebugEnabled()) {
			long time = System.currentTimeMillis() - start;
			logger.debug("execute.time[" + time + "], sql[" + getSql(sql, parameter) + "]");
		}

		return effectRow;
	}

	private String getSql(String sql, SqlParameter parameter) {

		if (parameter == null || parameter.getParams() == null) {
			return sql;
		}

		for (Object obj : parameter.getParams().values()) {
			if (obj == null) {
				obj = "null";
			}
			sql = sql.replaceFirst("\\?", "`" + obj.toString() + "`");
		}

		return sql;
	}

	public <T> T get(final String sql, Class<T> cls, final SqlParameter parameter) {

		long start = System.currentTimeMillis();

		List<T> list = this.getList(sql, cls, parameter);
		if (list != null) {
			if (list.size() == 1) {
				return list.get(0);
			} else if (list.size() > 0) {
				throw new RuntimeException("return more than one record while get one object!!!");
			}
		}

		if (logger.isDebugEnabled()) {
			long time = System.currentTimeMillis() - start;
			logger.debug("execute.time[" + time + "], sql[" + getSql(sql, parameter) + "]");
		}

		return null;
	}

	public <T> List<T> getList(String sql, Class<T> cls) {

		return this.getList(sql, cls, null);
	}

	public <T> List<T> getList(String sql, final Class<T> cls, final SqlParameter parameter) {

		long start = System.currentTimeMillis();

		ColumnBeanRowMapper<T> mapper = new ColumnBeanRowMapper<T>();
		mapper.setMappedClass(cls);

		List<T> list = this.jdbcTemplate.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				if (parameter != null) {
					for (Entry<Integer, Object> entry : parameter.getParams().entrySet()) {
						Object value = entry.getValue();
						if (value instanceof String) {
							ps.setString(entry.getKey(), value != null ? value.toString() : null);
						} else {
							ps.setObject(entry.getKey(), value);
						}
					}
				}
			}
		}, mapper);

		if (logger.isDebugEnabled()) {
			long time = System.currentTimeMillis() - start;
			logger.debug("execute.time[" + time + "], sql[" + getSql(sql, parameter) + "]");
		}

		return list;
	}

	public int getInt(String sql, final SqlParameter parameter) {

		long start = System.currentTimeMillis();

		int value = this.jdbcTemplate.query(sql, new PreparedStatementSetter() {

			public void setValues(PreparedStatement ps) throws SQLException {
				if (parameter != null) {
					for (Entry<Integer, Object> entry : parameter.getParams().entrySet()) {
						ps.setObject(entry.getKey(), entry.getValue());
					}
				}
			}
		}, new ResultSetExtractor<Integer>() {

			public Integer extractData(ResultSet rs) throws SQLException {

				if (rs.next()) {
					return rs.getInt(1);
				}
				return 0;
			}
		});

		if (logger.isDebugEnabled()) {
			long time = System.currentTimeMillis() - start;
			logger.debug("execute.time[" + time + "], sql[" + getSql(sql, parameter) + "]");
		}

		return value;

	}

	public <T> int insert(T t) {

		long start = System.currentTimeMillis();

		SQL sql = SQLHelper.createInsertSQL(t);
		int effectCount = this.update(sql.getSql(), sql.getParams());

		if (logger.isDebugEnabled()) {
			long time = System.currentTimeMillis() - start;
			logger.debug("execute.time[" + time + "], sql[" + getSql(sql.getSql(), sql.getParams()) + "]");
		}

		return effectCount;

	}

	public <T> int update(T t) {

		long start = System.currentTimeMillis();

		SQL sql = SQLHelper.createUpdateSQL(t);
		int effectCount = this.update(sql.getSql(), sql.getParams());

		if (logger.isDebugEnabled()) {
			long time = System.currentTimeMillis() - start;
			logger.debug("execute.time[" + time + "], sql[" + getSql(sql.getSql(), sql.getParams()) + "]");
		}

		return effectCount;

	}

	public <T> int insert(String tableName, T t) {

		long start = System.currentTimeMillis();

		SQL sql = SQLHelper.createInsertSQL(t, tableName);
		int effectCount = this.update(sql.getSql(), sql.getParams());

		if (logger.isDebugEnabled()) {
			long time = System.currentTimeMillis() - start;
			logger.debug("execute.time[" + time + "], sql[" + getSql(sql.getSql(), sql.getParams()) + "]");
		}

		return effectCount;
	}

	public <T> int replace(T t) {

		long start = System.currentTimeMillis();

		BeanSQL beanSql = SqlHelper.createBeanSql(t, "REPLACE");
		int effectCount = this.update(beanSql.getSql(), beanSql.getParams());

		if (logger.isDebugEnabled()) {
			long time = System.currentTimeMillis() - start;
			logger.debug("execute.time[" + time + "], sql[" + getSql(beanSql.getSql(), beanSql.getParams()) + "]");
		}

		return effectCount;

	}

	public <T> int[] insert(final List<T> list) {

		long start = System.currentTimeMillis();

		BeansSQL beansSql = SqlHelper.crateBeanSql(list);
		final List<SqlParameter> parameters = beansSql.getParams();

		int[] effectCounts = this.jdbcTemplate.batchUpdate(beansSql.getSql(), new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				SqlParameter parameter = parameters.get(i);
				for (Entry<Integer, Object> entry : parameter.getParams().entrySet()) {
					ps.setObject(entry.getKey(), entry.getValue());
				}
			}

			public int getBatchSize() {
				return list.size();
			}
		});

		if (logger.isDebugEnabled()) {
			long time = System.currentTimeMillis() - start;
			logger.debug("execute.time[" + time + "], sql[" + beansSql.getSql() + "]");
		}

		return effectCounts;
	}

	public <T> int[] insert(String tableName, final List<T> list) {

		long start = System.currentTimeMillis();

		BeansSQL beansSql = SqlHelper.crateBeanSql(tableName, list);
		final List<SqlParameter> parameters = beansSql.getParams();

		int[] effectCounts = this.jdbcTemplate.batchUpdate(beansSql.getSql(), new BatchPreparedStatementSetter() {

			public void setValues(PreparedStatement ps, int i) throws SQLException {
				SqlParameter parameter = parameters.get(i);
				for (Entry<Integer, Object> entry : parameter.getParams().entrySet()) {
					ps.setObject(entry.getKey(), entry.getValue());
				}
			}

			public int getBatchSize() {
				return list.size();
			}
		});

		if (logger.isDebugEnabled()) {
			long time = System.currentTimeMillis() - start;
			logger.debug("execute.time[" + time + "], sql[" + beansSql.getSql() + "]");
		}

		return effectCounts;
	}

}
