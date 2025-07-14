package com.vt.example.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VirtualThreadExecutorConfig
{
	@Bean(name = "virtualThreadExecutor")
	Executor virtualThreadExecutor()
	{
		return Executors.newVirtualThreadPerTaskExecutor();
	}
}
