package com.bageframework.demo.web.dao.impl.mysql;

import com.bageframework.demo.web.dao.MetadataDao;
import com.bageframework.dao.base.mysql.BaseMysqlDao;
import com.bageframework.demo.web.model.Metadata;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Repository
public class MetadataDaoMysqlImpl extends BaseMysqlDao<Metadata> implements MetadataDao {

}
