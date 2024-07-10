package br.dev.hygino.colecao.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;

@Configuration
@EnableJpaRepositories("br.dev.hygino.colecao.repository")//pacote onde estão os repositórios
@EnableTransactionManagement
public class SpringDataConfig {

    @Bean
    public DataSource dataSourceH2() {
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername("root");
        ds.setPassword("senha");
        ds.setJdbcUrl("jdbc:h2:mem:teste");
        ds.setDriverClassName("org.h2.Driver");

        return ds;
    }

    @Bean
    public DataSource dataSourceMariaDB() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.mariadb.jdbc.Driver");
        dataSource.setJdbcUrl("jdbc:mariadb://localhost:3306/utfpr_bd_colecao");
        dataSource.setUsername("root");
        dataSource.setPassword("89631139");

        return dataSource;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter vendorAdapter =
                new HibernateJpaVendorAdapter();

        vendorAdapter.setGenerateDdl(false);
        vendorAdapter.setShowSql(true);

        factory.setDataSource(dataSourceMariaDB());
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("br.dev.hygino.colecao.entity");//pacote onde estão as entidades
        factory.afterPropertiesSet();

        return factory.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory());
        manager.setJpaDialect(new HibernateJpaDialect());

        return manager;
    }
}