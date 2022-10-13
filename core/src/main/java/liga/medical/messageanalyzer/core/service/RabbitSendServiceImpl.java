package liga.medical.messageanalyzer.core.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import liga.medical.messageanalyzer.core.model.MessageDTO;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RabbitSendServiceImpl implements RabbitSendService {

    private final AmqpTemplate template;

    private final ObjectMapper mapper;

    @Override
    public void sendMessage(MessageDTO message, String queue) throws JsonProcessingException {
        String messageToString = mapper.writeValueAsString(message);
        template.convertAndSend(queue, messageToString);
    }
}
