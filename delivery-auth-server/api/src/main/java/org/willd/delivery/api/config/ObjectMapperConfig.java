package org.willd.delivery.api.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(org.willd.delivery.common.config.objectmapper.ObjectMapperConfig.class)
public class ObjectMapperConfig {
}
