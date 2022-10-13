package liga.medical.messageanalyzer.core.model;

import lombok.Data;

@Data
public class MessageDTO {

    private int id;

    private MessageStatus status;

    private String content;
}
