package com.idsmanager.demo.jwt.config;

import com.idsmanager.demo.jwt.commons.web.filter.CharacterEncodingIPFilter;
import com.idsmanager.demo.jwt.infrastructure.JzytConstants;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.servlet.Filter;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * 2018/3/20
 * <p>
 * 项目自身的配置
 *
 * @author Shengzhao Li
 */
@Configuration
public class ProjectConfiguration {


    //    @Value("${application.ftp.host}")
//    private String ftpHost;
//    @Value("${application.ftp.port}")
//    private int ftpPort;
//
//    @Value("${application.ftp.username}")
//    private String ftpUsername;
//
//    @Value("${application.ftp.password}")
//    private String ftpPassword;
//
//    @Value("${application.ftp.home.directory}")
//    private String ftpDirectory;

    @Value("${hibernate.show-sql}")
    private String hibernateShowSql;


    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        factoryBean.setDataSource(dataSource);
        //请注意修改
        factoryBean.setPackagesToScan("com.idsmanager.demo.jwt");

        Properties properties = new Properties();
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.setProperty("hibernate.show_sql", hibernateShowSql);
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        properties.setProperty("jdbc.use_scrollable_resultset", "false");
        properties.setProperty("hibernate.query.substitutions", "true=1,false=0");
        properties.setProperty("hibernate.current_session_context_class", "org.springframework.orm.hibernate5.SpringSessionContext");
        factoryBean.setHibernateProperties(properties);

        return factoryBean;
    }


    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

//    /**
//     * FileWarehouse bean
//     *
//     * @return bean
//     */
//    @Bean
//    public FileWarehouse fileWarehouse() {
//        FTPFileWarehouse ftpFileWarehouse = new FTPFileWarehouse();
//        ftpFileWarehouse.setFtpHomeDirectory(this.ftpDirectory);
//        ftpFileWarehouse.setFtpHost(this.ftpHost);
//        ftpFileWarehouse.setFtpPassword(this.ftpPassword);
//
//        ftpFileWarehouse.setFtpUsername(this.ftpUsername);
//        ftpFileWarehouse.setFtpPort(this.ftpPort);
//
//        return ftpFileWarehouse;
//    }


    /**
     * 字符编码 Filter,
     * 包括获取IP
     */
    @Bean
    public FilterRegistrationBean characterFilter() {
        final CharacterEncodingIPFilter characterEncodingIPFilter = new CharacterEncodingIPFilter();
        characterEncodingIPFilter.setForceEncoding(true);
        characterEncodingIPFilter.setEncoding(JzytConstants.ENCODING);

        FilterRegistrationBean<Filter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(characterEncodingIPFilter);
        registrationBean.addUrlPatterns("/*");
        registrationBean.setName("characterEncodingIPFilter");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
