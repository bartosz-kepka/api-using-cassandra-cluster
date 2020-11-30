package pl.nbd.api.repository;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;
import pl.nbd.api.model.Review;

@Repository
public interface ReviewRepository extends CassandraRepository<Review, String> {
}
