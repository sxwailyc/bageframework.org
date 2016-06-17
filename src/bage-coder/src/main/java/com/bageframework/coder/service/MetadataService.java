package com.bageframework.coder.service;

import com.bageframework.coder.core.Config;
import com.bageframework.coder.metadata.AdminVOMetadata;
import com.bageframework.coder.metadata.ModelMetadata;
import com.bageframework.coder.metadata.ServiceMetadata;
import com.bageframework.coder.metadata.VOMetadata;

public interface MetadataService {

	public ModelMetadata createModelMetadata(Config config, String table);

	public VOMetadata createVOMetadata(Config config, String table);

	public AdminVOMetadata createAdminVOMetadata(Config config, String table);

	public ServiceMetadata createServiceMetadata(Config config, String table);
}
