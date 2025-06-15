package com.example.employeemanagement.config;

import com.example.employeemanagement.interceptor.LoggingInterceptor;
import com.example.employeemanagement.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoggingInterceptor loggingInterceptor;

    @Autowired
    private EmployeeService employeeService;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggingInterceptor);
    }

    @Bean
    public CommandLineRunner allEmployeeOnStartup(EmployeeService employeeService) {
        return args -> {
            employeeService.getAllEmployees().forEach(System.out::println);
        };
    }
}
