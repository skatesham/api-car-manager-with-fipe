package core.demo.app.domain.veiculo.model;

import core.demo.app.domain.common.JsonUtils;
import core.demo.app.infrastucture.messaging.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VeiculoRequestedSender {

    @Autowired
    private MessageSender messageSender;

    @Value("${queue.name}")
    private String queueMessage;

    public void send(VeiculoEntity message) {
        messageSender.send(queueMessage, JsonUtils.convertToString(message));
    }

}