package com.bageframework.coder.service;

import com.bageframework.coder.core.Config;
import com.bageframework.coder.metadata.ModelMetadata;

public interface MetadataService {

	public ModelMetadata createModelMetadata(Config config, String table);
}
