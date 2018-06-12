package com.example.demo.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 数据库连接池配置
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(DruidProperties.class)
public class DruidConfiguration {

  @Autowired
  private DruidProperties druidProperties;

  @Bean
  public ServletRegistrationBean druidServlet() {
    log.info("init Druid Servlet Configuration ");
    ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
    servletRegistrationBean.setServlet(new StatViewServlet());
    servletRegistrationBean.addUrlMappings("/druid/*");
    Map<String, String> initParameters = new HashMap<String, String>();
    initParameters.put("loginUsername", "kaka");// 用户名
    initParameters.put("loginPassword", "Kaka1234");// 密码
    initParameters.put("resetEnable", "false");// 禁用HTML页面上的“Reset All”功能
    initParameters.put("allow", ""); // IP白名单 (没有配置或者为空，则允许所有访问)
    //initParameters.put("deny", "192.168.20.38");// IP黑名单 (存在共同时，deny优先于allow)
    servletRegistrationBean.setInitParameters(initParameters);
    return servletRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean filterRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new WebStatFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*,*.html");
    return filterRegistrationBean;
  }

  @Bean(name = "dataSource")
  @Primary
  public DataSource dataSource() {
    DruidDataSource datasource = new DruidDataSource();

    datasource.setUrl(druidProperties.getUrl());
    datasource.setUsername(druidProperties.getUsername());
    datasource.setPassword(druidProperties.getPassword());
    datasource.setDriverClassName(druidProperties.getDriverClassName());

    //configuration
    datasource.setInitialSize(druidProperties.getInitialSize());
    datasource.setMinIdle(druidProperties.getMinIdle());
    datasource.setMaxActive(druidProperties.getMaxActive());
    datasource.setMaxWait(druidProperties.getMaxWait());
    datasource.setTimeBetweenEvictionRunsMillis(druidProperties.getTimeBetweenEvictionRunsMillis());
    datasource.setMinEvictableIdleTimeMillis(druidProperties.getMinEvictableIdleTimeMillis());
    datasource.setValidationQuery(druidProperties.getValidationQuery());
    datasource.setTestWhileIdle(druidProperties.isTestWhileIdle());
    datasource.setTestOnBorrow(druidProperties.isTestOnBorrow());
    datasource.setTestOnReturn(druidProperties.isTestOnReturn());
    datasource.setPoolPreparedStatements(druidProperties.isPoolPreparedStatements());
    datasource.setMaxPoolPreparedStatementPerConnectionSize(druidProperties.getMaxPoolPreparedStatementPerConnectionSize());
    datasource.setConnectionProperties(druidProperties.getConnectionProperties());

    try {
      datasource.setFilters(druidProperties.getFilters());
    } catch (SQLException e) {
      log.error("druid configuration initialization filter", e);
    }

    return datasource;
  }

}