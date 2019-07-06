package com.ruokit.main.common.aop.transaction;

import java.lang.reflect.Proxy;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

public class TransactionProxyFactory implements FactoryBean<Object> {

  private String pattern;
  private DataSourceTransactionManager transactionManager;
  private Object target;
  Class<?> serviceInterface;

  public void setPattern(String pattern) {
    this.pattern = pattern;
  }

  public void setTransactionManager(DataSourceTransactionManager transactionManager) {
    this.transactionManager = transactionManager;
  }

  public void setTarget(Object target) {
    this.target = target;
  }

  public void setServiceInterface(Class<?> serviceInterface) {
    this.serviceInterface = serviceInterface;
  }

  @Override
  public Object getObject() throws Exception {
    TransactionHandler handler = new TransactionHandler();
    handler.setTarget(target);
    handler.setTransactionManager(transactionManager);
    handler.setPattern(pattern);
    return Proxy.newProxyInstance(getClass().getClassLoader(), new Class[] {serviceInterface},
        handler);
  }

  @Override
  public Class<?> getObjectType() {
    return serviceInterface;
  }

  public boolean isSingleton() {
    return false;
  }
}
