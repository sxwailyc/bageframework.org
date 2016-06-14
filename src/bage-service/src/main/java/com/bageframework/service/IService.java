package com.bageframework.service;

import java.util.List;

import com.bageframework.dao.beans.Page;
import com.bageframework.dao.beans.Query;

/**
 * 
 * @author shixiangwen03@gmail.com
 * 
 * @param <BEAN>
 *            数据库实体bean
 * @param <VO_BEAN>
 *            视图bean
 * @param <ADMIN_VO_BEAN>
 *            后台视图bean
 * @param <KEYTYPE>
 *            主键类型
 */
public interface IService<BEAN, VO_BEAN, ADMIN_VO_BEAN, KEYTYPE> {

	/**
	 * 增加
	 * 
	 * @param bean
	 * @return
	 */
	public boolean add(BEAN bean);

	/**
	 * 修改
	 * 
	 * @param bean
	 * @return
	 */
	public boolean update(BEAN bean);

	/**
	 * 查找
	 * 
	 * @param key
	 * @return
	 */
	public BEAN get(KEYTYPE key);

	/**
	 * 删除
	 * 
	 * @param key
	 * @return
	 */
	public boolean delete(KEYTYPE key);

	/**
	 * 获取列表
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	public List<BEAN> getList(int start, int size);

	/**
	 * 获取列表
	 * 
	 * @param parentId
	 * @param start
	 * @param size
	 * @return
	 */
	public List<BEAN> getList(int parentId, int start, int size);

	/**
	 * 获取ViewObject列表
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	public List<VO_BEAN> getVOList(int start, int size);

	/**
	 * 取ViewObject列表
	 * 
	 * @param parentId
	 * @param start
	 * @param size
	 * @return
	 */
	public List<VO_BEAN> getVOList(int parentId, int start, int size);

	/**
	 * 获取分页列表
	 * 
	 * @param start
	 * @param size
	 * @return
	 */
	public Page<ADMIN_VO_BEAN> getPage(int start, int size);

	/**
	 * 获取分页列表
	 * 
	 * @param parentid
	 * @param start
	 * @param size
	 * @return
	 */
	public Page<ADMIN_VO_BEAN> getPage(int parentid, int start, int size);

	/**
	 * 获取分页列表
	 * 
	 * @param page
	 * @return
	 */
	public Page<ADMIN_VO_BEAN> getPage(Page<BEAN> page);

	/**
	 * 获取分页列表
	 * 
	 * @param query
	 * @param start
	 * @param size
	 * @return
	 */
	public Page<ADMIN_VO_BEAN> getPage(Query query, int start, int size);
}
