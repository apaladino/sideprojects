package com.citrix.regsvc.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by apaladino on 9/29/14.
 */
@Configuration
@ImportResource("classpath:/com/citrix/regsvc/regsvc-context.xml")
@Import(DataConfig.class)
@ComponentScan
public class ContextConfig {


}
