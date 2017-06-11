package pl.com.musicstore.api.configuration;

import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DebugSpringConfig implements BeanPostProcessor {

    org.slf4j.Logger logger = LoggerFactory.getLogger(DebugSpringConfig.class);
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        logger.info("postProcessBeforeInitialization: bean("+beanName+")");
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // logger.info("postProcessAfterInitialization: bean("+beanName+")");
        return bean;
    }
}
