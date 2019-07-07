package com.ruokit.main.common.aop.transaction;

import java.lang.reflect.Method;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

public class TransactionAdvice implements MethodInterceptor {

  private static Logger logger = null;
  private DataSourceTransactionManager transactionManager;

  public void setTransactionManager(DataSourceTransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  @Override
  public Object invoke(MethodInvocation invocation) throws Throwable {

    Method reflectMethod = invocation.getMethod();
    String className = reflectMethod.getDeclaringClass().getName();
    logger = LoggerFactory.getLogger(className);

    TransactionStatus status =
        this.transactionManager.getTransaction(new DefaultTransactionDefinition());

    // if (status.isNewTransaction())
    // logger.info("transaction start");

    try {
      Object ret = invocation.proceed();
      this.transactionManager.commit(status);
      return ret;
    } catch (RuntimeException e) {
      this.transactionManager.rollback(status);
      logger.info("transaction rollback");
      throw e;
    } finally {
      // if (status.isCompleted())
      // logger.info("transaction end");
    }
  }

}
