package org.will.delivery.web.cofig;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(org.willd.delivery.common.config.r2dbc.R2dbcConfig.class)
public class R2dbcConfig {

}
