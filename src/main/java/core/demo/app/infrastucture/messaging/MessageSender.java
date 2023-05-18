package core.demo.app.infrastucture.messaging;

public interface MessageSender {

    void send(String queue, String message);

}
