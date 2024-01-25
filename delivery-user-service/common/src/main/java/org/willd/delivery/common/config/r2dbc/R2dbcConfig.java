package org.willd.delivery.common.config.r2dbc;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories(basePackages = "org.willd.delivery.db")
@EnableR2dbcAuditing
public class R2dbcConfig {

}
