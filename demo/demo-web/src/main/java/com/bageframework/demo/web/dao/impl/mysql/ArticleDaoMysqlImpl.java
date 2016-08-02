package com.bageframework.demo.web.dao.impl.mysql;

import com.bageframework.dao.base.mysql.BaseMysqlDao;
import com.bageframework.demo.web.dao.ArticleDao;
import com.bageframework.demo.web.model.Article;
import org.springframework.stereotype.Repository;

/**
 * 
 * 
 * @author shixiangwen03@gmail.com
 * 
 */
@Repository
public class ArticleDaoMysqlImpl extends BaseMysqlDao<Article> implements ArticleDao {

}
