package liga.medical.messageanalyzer.core.repository;

import liga.medical.messageanalyzer.core.model.Debug;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebugRepository extends CrudRepository<Debug, Long> {
}
