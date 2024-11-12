/*
 * WebConfig.java
 * Copyright (c) UEG.
 */
package com.g2.Progweb_II_EducaDin_Backend.infra.security.configuration;

import br.ueg.progweb2.arquitetura.config.ApiWebConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

/**
 * Classe de configuração referente aos recursos Web MVC da aplicação.
 * 
 * @author UEG
 */
@Configuration
public class WebConfig extends ApiWebConfig {
    //Para exibir logs do que está senfo requisitado pelo frontend
    //precisa colocar o atributo abaixo no application.properties:
    //logging.level.org.springframework.web.filter.CommonsRequestLoggingFilter=DEBUG
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setMaxPayloadLength(64000);
        return loggingFilter;
    }
}
