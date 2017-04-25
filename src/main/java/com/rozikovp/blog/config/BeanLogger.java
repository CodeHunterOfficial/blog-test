package com.rozikovp.blog.config;

import com.rozikovp.blog.services.service.CommentService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerExceptionResolverComposite;


/**
 * Created by ParvizR on 12/01/2017.
 * @author ParvizR
 */
@Component
public class BeanLogger implements BeanPostProcessor {

    protected Log logger;
    protected boolean enabled = true;

    public BeanLogger() {
        logger = LogFactory.getLog(BeanLogger.class);
    }

    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName)
            throws BeansException {
        // Nothing to do. Must return the bean or we lose it!
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName)
            throws BeansException {
        if (!enabled)
            return bean; // Must return the bean or we lose it!

        if (bean instanceof CommentService) {
            final String order = String.valueOf(((CommentService) bean).getClass());
            logger.debug(bean.getClass().getName() + " - Name : " + order);
        } else {
            logger.debug("BEAN " + bean.getClass().getName());
        }

        if (bean instanceof HandlerExceptionResolverComposite) {
            logger.debug(" resolvers: "
                    + ((HandlerExceptionResolverComposite) bean)
                    .getExceptionResolvers());
        }
        // Must return the bean or we lose it!
        return bean;
    }
}