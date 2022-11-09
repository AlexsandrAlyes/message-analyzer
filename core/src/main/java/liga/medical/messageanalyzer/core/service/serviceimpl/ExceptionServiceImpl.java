package liga.medical.messageanalyzer.core.service.serviceimpl;

import liga.medical.messageanalyzer.core.model.Exception;
import liga.medical.messageanalyzer.core.repository.ExceptionRepository;
import liga.medical.messageanalyzer.core.service.ExceptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExceptionServiceImpl implements ExceptionService {

    private final ExceptionRepository exceptionRepository;

    @Override
    public Exception saveExceptionInDB(Exception exception) {
        return exceptionRepository.save(exception);
    }
}
