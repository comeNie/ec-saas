package com.yjg.ec.platform.common.component;

import org.quartz.spi.TriggerFiredBundle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.scheduling.quartz.AdaptableJobFactory;
import org.springframework.stereotype.Component;

@Component
public class QuartzJobFactory extends AdaptableJobFactory {

	@Autowired
	AutowireCapableBeanFactory capableBeanFactory;

	@Override
	protected Object createJobInstance(TriggerFiredBundle bundle) throws Exception {
		// 调用父类的方�?
		Object jobInstance = super.createJobInstance(bundle);
		// 进行注入
		capableBeanFactory.autowireBean(jobInstance);
		return jobInstance;
	}
}
