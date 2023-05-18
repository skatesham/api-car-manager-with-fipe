package core.demo.app.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessagingSenderConfig {

    public static final String DLQ_SUFFIX = ".dlq";

    @Value("${queue.name}")
    private String queueMessage;

    @Bean
    Queue messagesQueue() {
        return QueueBuilder.durable(queueMessage)
//                .withArgument("x-dead-letter-exchange", getDLQ())
//                .withArgument("x-dead-letter-routing-key", getDLQ())
                .build();
    }

    @Bean
    DirectExchange messagesExchange() {
        return new DirectExchange(queueMessage);
    }

    @Bean
    Binding bindingMessages() {
        return BindingBuilder.bind(messagesQueue()).to(messagesExchange()).with(queueMessage);
    }

//    @Bean
//    FanoutExchange deadLetterExchange() {
//        return new FanoutExchange(getDLQ());
//    }

    @Bean
    DirectExchange deadLetterExchange() {
        return new DirectExchange(getDLQ());
    }

    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(getDLQ()).build();
    }

    @Bean
    Binding deadLetterBinding() {
        return BindingBuilder.bind(deadLetterQueue()).to(deadLetterExchange()).with(getDLQ());
    }

    private String getDLQ() {
        return queueMessage + DLQ_SUFFIX;
    }


}