package core.demo.app.interfaces.veiculo;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.demo.app.domain.veiculo.VeiculoService;
import core.demo.app.domain.veiculo.model.VeiculoEntity;
import core.demo.app.infrastucture.messaging.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VeiculoRequestedConsumer {

    @Autowired
    private VeiculoService veiculoService;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MessageSender messageSender;

    @Value("${queue.name}")
    private String queueMessage;

    @RabbitListener(queues = {"${queue.name}"})
    public void receive(@Payload String veiculoMessage) {
        try {
            log.trace("action=veiculo-requested status=start message={}", veiculoMessage);
            final VeiculoEntity veiculoEntity = objectMapper.readValue(veiculoMessage, VeiculoEntity.class);
            veiculoService.processCreation(veiculoEntity);
            log.trace("action=veiculo-requested status=success");

        } catch (Exception e) {
            messageSender.send(queueMessage + ".dlq", veiculoMessage);
            log.error("action=veiculo-requested status=dlq error={}", e.getMessage(), e);
        }
    }

}