package app.config;

import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

//Para cada fuente de datos se agregan sus respectivos getter y setter
@ConfigurationProperties("spring.multitenancy")
public class MultitenancyProperties {

	@NestedConfigurationProperty
	private DataSourceProperties datasource1;

	@NestedConfigurationProperty
	private DataSourceProperties datasource2;

	@NestedConfigurationProperty
	private DataSourceProperties datasource3;
        
	@NestedConfigurationProperty
	private DataSourceProperties datasource4;
        
        @NestedConfigurationProperty
	private DataSourceProperties datasource5;
        
        @NestedConfigurationProperty
	private DataSourceProperties datasource6;

	@NestedConfigurationProperty
	private DataSourceProperties datasource7;

	@NestedConfigurationProperty
	private DataSourceProperties datasource8;
        
	@NestedConfigurationProperty
	private DataSourceProperties datasource9;
        
        @NestedConfigurationProperty
	private DataSourceProperties datasource10;
        
	public DataSourceProperties getDatasource1() {
		return datasource1;
	}

	public void setDatasource1(DataSourceProperties datasource1) {
		this.datasource1 = datasource1;
	}

	public DataSourceProperties getDatasource2() {
		return datasource2;
	}

	public void setDatasource2(DataSourceProperties datasource2) {
		this.datasource2 = datasource2;
	}

	public DataSourceProperties getDatasource3() {
		return datasource3;
	}

	public void setDatasource3(DataSourceProperties datasource3) {
		this.datasource3 = datasource3;
	}

        public DataSourceProperties getDatasource4() {
		return datasource4;
	}

	public void setDatasource4(DataSourceProperties datasource4) {
		this.datasource4 = datasource4;
	}
        
        public DataSourceProperties getDatasource5() {
		return datasource5;
	}

	public void setDatasource5(DataSourceProperties datasource5) {
		this.datasource5 = datasource5;
	}
        
	public DataSourceProperties getDatasource6() {
		return datasource6;
	}

	public void setDatasource6(DataSourceProperties datasource6) {
		this.datasource6 = datasource6;
	}

	public DataSourceProperties getDatasource7() {
		return datasource7;
	}

	public void setDatasource7(DataSourceProperties datasource7) {
		this.datasource7 = datasource7;
	}

	public DataSourceProperties getDatasource8() {
		return datasource8;
	}

	public void setDatasource8(DataSourceProperties datasource8) {
		this.datasource8 = datasource8;
	}

        public DataSourceProperties getDatasource9() {
		return datasource9;
	}

	public void setDatasource9(DataSourceProperties datasource9) {
		this.datasource9 = datasource9;
	}
        
        public DataSourceProperties getDatasource10() {
		return datasource10;
	}

	public void setDatasource10(DataSourceProperties datasource10) {
		this.datasource10 = datasource10;
	}
}