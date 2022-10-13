package liga.medical.messageanalyzer.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.messageanalyzer.core.model.MessageDTO;

public interface RabbitSendService {

    void sendMessage(MessageDTO message, String queue) throws JsonProcessingException;
}
