package liga.medical.messageanalyzer.core.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import liga.medical.messageanalyzer.core.model.MessageDTO;
import liga.medical.messageanalyzer.core.service.RabbitSendServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@AllArgsConstructor
@RequestMapping("/message-analyzer")
public class MessageAnalyzerRestController {

    private final RabbitSendServiceImpl service;

    @GetMapping("/message")
    private ResponseEntity<String> getAllMessages() {
        return ResponseEntity.status(501).body("Не реализованно");
    }

    @GetMapping("/message/{id}")
    private ResponseEntity<String> getByIdMessage(@PathVariable("id") int id) {
        return ResponseEntity.status(501).body("Не реализованно");
    }

    @PostMapping("/message")
    private ResponseEntity<String> saveMessage(@RequestBody MessageDTO message) {
        if (message.getStatus() != null) {
            try {
                service.sendMessage(message, "common_monitoring");
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok("Сообщение принято");
        }
        return ResponseEntity.badRequest().body("Сообщение не принято");
    }

    @PutMapping("/message")
    private ResponseEntity<String> updateMessageById(@RequestBody MessageDTO message) {
        return ResponseEntity.status(501).body("Не реализованно");
    }

    @DeleteMapping("/message/{id}")
    private ResponseEntity<String> deleteMessageById(@PathVariable("id") int id) {
        return ResponseEntity.status(501).body("Не реализованно");
    }
}
