package liga.medical.messageanalyzer.core.repository;

import liga.medical.messageanalyzer.core.model.Exception;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExceptionRepository extends CrudRepository<Exception, Long> {
}
