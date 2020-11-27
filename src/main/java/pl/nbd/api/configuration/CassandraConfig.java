package pl.nbd.api.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.cassandra.CassandraProperties;
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

    private final CassandraProperties cassandraProperties;

    @NonNull
    @Override
    protected String getLocalDataCenter() {
        return cassandraProperties.getLocalDatacenter();
    }

    @NonNull
    @Override
    protected String getContactPoints() {
        return cassandraProperties.getContactPoints().get(0);
    }

    @NonNull
    @Override
    protected String getKeyspaceName() {
        return cassandraProperties.getKeyspaceName();
    }

    @NonNull
    @Override
    public SchemaAction getSchemaAction() {
        return SchemaAction.valueOf(cassandraProperties.getSchemaAction());
    }

    @NonNull
    @Override
    public CqlSessionFactoryBean cassandraSession() {
        RetryingCqlSessionFactoryBean bean = new RetryingCqlSessionFactoryBean();

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
}
