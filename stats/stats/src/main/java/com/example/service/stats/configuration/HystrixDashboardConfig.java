package com.example.service.stats.configuration;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HystrixDashboardConfig {
        @Bean
        public ServletRegistrationBean hystrixStreamServlet() {
            return new ServletRegistrationBean(new HystrixMetricsStreamServlet(), "/hystrix.stream");
        }
}
