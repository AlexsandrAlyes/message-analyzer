package liga.medical.messageanalyzer.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.messageanalyzer.core.model.MessageDTO;
import liga.medical.messageanalyzer.core.service.serviceimpl.RabbitSendServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@AllArgsConstructor
@RequestMapping("/message-analyzer")
public class MessageAnalyzerRestController {

    private final RabbitSendServiceImpl service;

    @PostMapping("/message")
    private ResponseEntity<String> saveMessage(@RequestBody MessageDTO message) {

        System.out.println(message.toString());
        System.out.println(message.getType());
        if (message.getType() != null) {
            try {
                service.sendMessage(message, "common_monitoring");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok("Сообщение принято");
        }
        return ResponseEntity.badRequest().body("Сообщение не принято");
    }

}
