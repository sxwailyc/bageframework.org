package com.bageframework.coder.service;

import com.bageframework.coder.core.Config;
import com.bageframework.coder.metadata.Metadata;

public interface MetadataService {

	public Metadata createMetadata(Config config, String table);
}
