package bitcamp.myapp;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

@SpringBootApplication // Gradle에서 bootRun 태스크를 수행할 때 실행시킬 메인 클래스를 지정하는 애노테이션
@PropertySource("file:${user.home}/config/bitcamp-study.properties")
@MapperScan(basePackages = "bitcamp.myapp.dao")
public class App {

  @Bean // Spring IoC 컨테이너는 이 메서드를 호출한 후 리턴된 객체를 메서드 이름으로 보관한다.
  public DataSource dataSource(
          @Value("${jdbc.driver}") String driverClassName,
          @Value("${jdbc.url}") String url,
          @Value("${jdbc.username}") String username,
          @Value("${jdbc.password}") String password
  ) {
    DriverManagerDataSource dataSource = new DriverManagerDataSource();
    dataSource.setDriverClassName(driverClassName);
    dataSource.setUrl(url);
    dataSource.setUsername(username);
    dataSource.setPassword(password);
    return dataSource;
  }

  @Bean
  public PlatformTransactionManager transactionManager(DataSource dataSource) {
    return new DataSourceTransactionManager(dataSource);
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext iocContainer) throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource);
    factoryBean.setTypeAliasesPackage("bitcamp.myapp.vo");

    factoryBean.setMapperLocations(iocContainer.getResources("classpath:bitcamp/myapp/mapper/*Dao.xml"));
    return factoryBean.getObject();
  }

  @Bean
  public MapperScannerConfigurer mapperScannerConfigurer() {
    MapperScannerConfigurer configurer = new MapperScannerConfigurer();
    configurer.setBasePackage("bitcamp.myapp.dao");

    return configurer;
  }

  public static void main(String[] args) {
    System.out.println("App 실행!");
    SpringApplication.run(App.class, args);
  }
}
