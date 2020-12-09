package pl.nbd.api.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.config.CqlSessionFactoryBean;
import org.springframework.data.cassandra.config.SchemaAction;
import org.springframework.data.cassandra.core.cql.keyspace.CreateKeyspaceSpecification;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;
import org.springframework.lang.NonNull;

import java.util.List;

@Configuration
@EnableCassandraRepositories
@RequiredArgsConstructor
public class CassandraConfig extends AbstractCassandraConfiguration {

    @Value("${cassandra.local-datacenter:datacenter1}")
    private String localDatacenter;

    @Value("${cassandra.contact-points:localhost}")
    private String contactPoints;

    @Value("${cassandra.port:9042}")
    private int port;

    @Value("${cassandra.keyspace-name:wine_reviews}")
    private String keyspaceName;

    @Value("${cassandra.schema-action:CREATE_IF_NOT_EXISTS}")
    private String schemaAction;

    @NonNull
    @Override
    protected String getLocalDataCenter() {
        return localDatacenter;
    }

    @NonNull
    @Override
    protected String getContactPoints() {
        return contactPoints;
    }

    @Override
    protected int getPort() {
        return port;
    }

    @NonNull
    @Override
    protected String getKeyspaceName() {
        return keyspaceName;
    }

    @NonNull
    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(schemaAction);
    }

    @NonNull
    @Override
    protected List<CreateKeyspaceSpecification> getKeyspaceCreations() {
        final CreateKeyspaceSpecification specification =
                CreateKeyspaceSpecification.createKeyspace(getKeyspaceName())
                        .ifNotExists()
                        .withSimpleReplication();
        return List.of(specification);
    }

    @NonNull
    @Override
    public String[] getEntityBasePackages() {
        return new String[]{"pl.nbd.api.model"};
    }

    @NonNull
    @Override
    public CqlSessionFactoryBean cassandraSession() {
        CustomCqlSessionFactoryBean bean = new CustomCqlSessionFactoryBean();
        bean.setContactPoints(getContactPoints());
        bean.setKeyspaceCreations(getKeyspaceCreations());
        bean.setKeyspaceDrops(getKeyspaceDrops());
        bean.setKeyspaceName(getKeyspaceName());
        bean.setKeyspaceStartupScripts(getStartupScripts());
        bean.setKeyspaceShutdownScripts(getShutdownScripts());
        bean.setLocalDatacenter(getLocalDataCenter());
        bean.setPort(getPort());

        return bean;
    }
}
