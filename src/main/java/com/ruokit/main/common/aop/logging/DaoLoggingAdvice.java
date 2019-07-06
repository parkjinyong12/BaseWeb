package com.ruokit.main.common.aop.logging;

import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DaoLoggingAdvice implements MethodInterceptor {

  private static Logger logger = null;

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {

    Method reflectMethod = invocation.getMethod();
    String className = reflectMethod.getDeclaringClass().getName();
    logger = LoggerFactory.getLogger(className);
    try {
      logger.info("method. " + reflectMethod.getName());
      Object ret = invocation.proceed();

      Class<?> returnTypeClass = reflectMethod.getReturnType();
      // logger.info("type : " + returnTypeClass.getTypeName());

      for (Class<?> clazz : returnTypeClass.getInterfaces()) {
        if ("RuokitModel".indexOf(clazz.getName()) != -1) {
          logger.info("result : " + ret.toString());
        } else {
          logger.info("result : " + ret.toString());
        }
      }
      return ret;
    } catch (RuntimeException e) {
      throw e;
    }
  }

}
