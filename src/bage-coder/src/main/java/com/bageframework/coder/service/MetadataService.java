package com.bageframework.coder.service;

import com.bageframework.coder.core.Config;
import com.bageframework.coder.metadata.AdminControllerMetadata;
import com.bageframework.coder.metadata.AdminVOMetadata;
import com.bageframework.coder.metadata.DaoMetadata;
import com.bageframework.coder.metadata.DaoMysqlImplMetadata;
import com.bageframework.coder.metadata.ModelMetadata;
import com.bageframework.coder.metadata.ServiceImplMetadata;
import com.bageframework.coder.metadata.ServiceMetadata;
import com.bageframework.coder.metadata.VOMetadata;
import com.bageframework.coder.metadata.ViewMetadata;
import com.bageframework.coder.metadata.WebServiceImplMetadata;
import com.bageframework.coder.metadata.WebServiceMetadata;

public interface MetadataService {

	public ModelMetadata createModelMetadata(Config config, String table);

	public VOMetadata createVOMetadata(Config config, String table);

	public AdminVOMetadata createAdminVOMetadata(Config config, String table);

	public DaoMetadata createDaoMetadata(Config config, String table);

	public DaoMysqlImplMetadata createDaoMysqlImplMetadata(Config config, String table);

	public ServiceMetadata createServiceMetadata(Config config, String table);

	public ServiceImplMetadata createServiceImplMetadata(Config config, String table);

	public WebServiceMetadata createWebServiceMetadata(Config config, String table);

	public WebServiceImplMetadata createWebServiceImplMetadata(Config config, String table);

	public AdminControllerMetadata createAdminControllerMetadata(Config config, String table);

	public ViewMetadata createViewMetadata(Config config, String table);

}
