package org.gocs.letmeknow.application;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by dynamicheart on 6/27/2017.
 */

@Configuration
@ComponentScan({"org.gocs.letmeknow"})
@EnableAutoConfiguration
public class ApplicationConfiguration {
}
