package com.oauth2.authorization.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author fangxing.peng
 */
@EnableTransactionManagement
@Configuration
@MapperScan({"cn.com.jrj.vtmatch.firstcapitalmanage.dao"})
@ConditionalOnProperty(value = "mybatis-plus.mapper-locations")
@Slf4j
public class MybatisConfig {


}
