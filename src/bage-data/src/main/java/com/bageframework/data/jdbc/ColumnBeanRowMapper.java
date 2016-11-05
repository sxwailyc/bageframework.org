package com.bageframework.data.jdbc;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.simple.ParameterizedRowMapper;

public class ColumnBeanRowMapper<T> extends BeanPropertyRowMapper<T> implements ParameterizedRowMapper<T> {

}
