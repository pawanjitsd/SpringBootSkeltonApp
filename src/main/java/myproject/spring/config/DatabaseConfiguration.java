package myproject.spring.config;


/*
@Configuration
@EnableJpaRepositories(value = "cdm.spring.repositories")
@EntityScan("cdm.spring.entities")
public class DatabaseConfiguration {

    @Bean
    public DataSource dataSource() {
        final XADataSource dataSource = new XADataSource();
        dataSource.setUsername("XXXXX");
        dataSource.setPassword("XXXX");
        dataSource.setUrl("jdbc:oracle:thin:@XXXX:1521:XXXXX");
        dataSource.setMaxActive(10);
        dataSource.setValidationQuery("SELECT 1 FROM DUAL");
        dataSource.setValidationInterval(30000);
        dataSource.setTestOnBorrow(true);
        dataSource.setTestOnReturn(false);
        dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        return dataSource;
    }

    @Bean(name = "sessionFactory")
    public LocalSessionFactoryBean sessionFactory() throws Exception {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("cdm.spring.entities");
        sessionFactory.setHibernateProperties(hibernateProperties());
        List<Class> annotatedClasses = new ArrayList<>();
        sessionFactory.setAnnotatedClasses( annotatedClasses.toArray(new Class[annotatedClasses.size()]));
        return sessionFactory;
    }


    @Bean
    public Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.Oracle10gDialect");
        properties.setProperty("hibernate.cache.use_second_level_cache", "true");
        properties.setProperty("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.EhCacheRegionFactory");
        return properties;
    }
}
*/

