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
    public DataSource dataSource() {
        HikariDataSource ds = new HikariDataSource();
        ds.setUsername("root");
        ds.setPassword("senha");
        ds.setJdbcUrl("jdbc:h2:mem:teste");
        ds.setDriverClassName("org.h2.Driver");

        return ds;
    }

    @Bean
    public EntityManagerFactory entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory =
                new LocalContainerEntityManagerFactoryBean();

        HibernateJpaVendorAdapter vendorAdapter =
                new HibernateJpaVendorAdapter();

        vendorAdapter.setGenerateDdl(true);
        vendorAdapter.setShowSql(true);

        factory.setDataSource(dataSource());
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