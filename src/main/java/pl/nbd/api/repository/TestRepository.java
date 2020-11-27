package pl.nbd.api.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import pl.nbd.api.model.Test;

@Repository
public interface TestRepository extends CassandraRepository<Test, String> {
}
