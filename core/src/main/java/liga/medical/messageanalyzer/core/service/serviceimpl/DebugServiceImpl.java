package liga.medical.messageanalyzer.core.service.serviceimpl;

import liga.medical.messageanalyzer.core.model.Debug;
import liga.medical.messageanalyzer.core.repository.DebugRepository;
import liga.medical.messageanalyzer.core.service.DebugService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DebugServiceImpl implements DebugService {

    private final DebugRepository debugRepository;

    @Override
    public Debug saveDebugInDB(Debug debug) {
        return debugRepository.save(debug);
    }
}
