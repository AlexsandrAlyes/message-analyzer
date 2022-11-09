package liga.medical.messageanalyzer.core.service;

import liga.medical.messageanalyzer.core.model.Exception;

public interface ExceptionService {

    Exception saveExceptionInDB(Exception exception);
}
