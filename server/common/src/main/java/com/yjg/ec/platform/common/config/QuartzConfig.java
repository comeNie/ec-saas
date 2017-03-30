package com.yjg.ec.platform.common.config;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.yjg.ec.platform.common.component.QuartzJobFactory;

@Configuration
public class QuartzConfig {
	@Autowired
	QuartzJobFactory quartzJobFactory;

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean() {
		SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
		schedulerFactoryBean.setOverwriteExistingJobs(true);
		schedulerFactoryBean.setJobFactory(quartzJobFactory);
		return schedulerFactoryBean;
	}

	@Bean
	public Scheduler scheduler() {
		return schedulerFactoryBean().getScheduler();
	}

}
