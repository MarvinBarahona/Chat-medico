package app.util;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/*Principal clase que permite resolver la arquitectura multitenant, esta implementa una clase de Hibernate 
que gestiona la fuente de datos a enviar por cada peticion*/
@Component
public class DataSourceBasedMultiTenantConnectionProviderImpl extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

    private static final long serialVersionUID = 8168907057647334460L;
    private static final String DEFAULT_TENANT_ID = "prueba";

    @Autowired
    private DataSource dataSourcePrueba1;

    @Autowired
    private DataSource dataSourcePrueba2;

    @Autowired
    private DataSource dataSourcePrueba3;

    private Map<String, DataSource> map;

    @PostConstruct
    public void load() {
        map = new HashMap<>();
        map.put("prueba", dataSourcePrueba1);
        map.put("prueba2", dataSourcePrueba2);
        map.put("prueba3", dataSourcePrueba3);
    }

    @Override
    protected DataSource selectAnyDataSource() {
        return map.get(DEFAULT_TENANT_ID);
    }

    @Override
    protected DataSource selectDataSource(String tenantIdentifier) {
        return map.get(tenantIdentifier);
    }
}
