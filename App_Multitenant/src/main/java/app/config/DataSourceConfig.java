package app.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*Para cada fuente de datos MultiTenant se obtienen los principales datos para crear la conexion*/
@Configuration
public class DataSourceConfig {

	@Autowired
	private MultitenancyProperties multitenancyProperties;

	@Bean(name = { "dataSource", "dataSource1" })
	@ConfigurationProperties(prefix = "spring.multitenancy.datasource1")
	public DataSource dataSource1() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource1().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource1().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource1().getUsername())
				.password(this.multitenancyProperties.getDatasource1().getPassword())
				.url(this.multitenancyProperties.getDatasource1().getUrl());
		return factory.build();
	}

	@Bean(name = "dataSource2")
	@ConfigurationProperties(prefix = "spring.multitenancy.datasource2")
	public DataSource dataSource2() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource2().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource2().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource2().getUsername())
				.password(this.multitenancyProperties.getDatasource2().getPassword())
				.url(this.multitenancyProperties.getDatasource2().getUrl());
		return factory.build();
	}

	@Bean(name = "dataSource3")
	@ConfigurationProperties(prefix = "spring.multitenancy.datasource3")
	public DataSource dataSource3() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource3().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource3().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource3().getUsername())
				.password(this.multitenancyProperties.getDatasource3().getPassword())
				.url(this.multitenancyProperties.getDatasource3().getUrl());
		return factory.build();
	}
        
        @Bean(name = "dataSource4")
	@ConfigurationProperties(prefix = "spring.multitenancy.datasource4")
	public DataSource dataSource4() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource4().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource4().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource4().getUsername())
				.password(this.multitenancyProperties.getDatasource4().getPassword())
				.url(this.multitenancyProperties.getDatasource4().getUrl());
		return factory.build();
	}
        
        @Bean(name = "dataSource5")
	@ConfigurationProperties(prefix = "spring.multitenancy.datasource5")
	public DataSource dataSource5() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource5().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource5().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource5().getUsername())
				.password(this.multitenancyProperties.getDatasource5().getPassword())
				.url(this.multitenancyProperties.getDatasource5().getUrl());
		return factory.build();
	}
        
	@Bean(name = { "dataSource", "dataSource6" })
	@ConfigurationProperties(prefix = "spring.multitenancy.datasource6")
	public DataSource dataSource6() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource6().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource6().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource6().getUsername())
				.password(this.multitenancyProperties.getDatasource6().getPassword())
				.url(this.multitenancyProperties.getDatasource6().getUrl());
		return factory.build();
	}

	@Bean(name = "dataSource7")
	@ConfigurationProperties(prefix = "spring.multitenancy.datasource7")
	public DataSource dataSource7() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource7().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource7().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource7().getUsername())
				.password(this.multitenancyProperties.getDatasource7().getPassword())
				.url(this.multitenancyProperties.getDatasource7().getUrl());
		return factory.build();
	}

	@Bean(name = "dataSource8")
	@ConfigurationProperties(prefix = "spring.multitenancy.datasource8")
	public DataSource dataSource8() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource8().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource8().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource8().getUsername())
				.password(this.multitenancyProperties.getDatasource8().getPassword())
				.url(this.multitenancyProperties.getDatasource8().getUrl());
		return factory.build();
	}
        
        @Bean(name = "dataSource9")
	@ConfigurationProperties(prefix = "spring.multitenancy.datasource9")
	public DataSource dataSource9() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource9().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource9().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource9().getUsername())
				.password(this.multitenancyProperties.getDatasource9().getPassword())
				.url(this.multitenancyProperties.getDatasource9().getUrl());
		return factory.build();
	}
        
        @Bean(name = "dataSource10")
	@ConfigurationProperties(prefix = "spring.multitenancy.datasource10")
	public DataSource dataSource10() {
		DataSourceBuilder factory = DataSourceBuilder
				.create(this.multitenancyProperties.getDatasource10().getClassLoader())
				.driverClassName(this.multitenancyProperties.getDatasource10().getDriverClassName())
				.username(this.multitenancyProperties.getDatasource10().getUsername())
				.password(this.multitenancyProperties.getDatasource10().getPassword())
				.url(this.multitenancyProperties.getDatasource10().getUrl());
		return factory.build();
	}        
}
