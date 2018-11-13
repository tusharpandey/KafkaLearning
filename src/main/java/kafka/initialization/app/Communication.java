package kafka.initialization.app;

import kafka.domain.SendMessageUsecase;

public class Communication {
    public static void main(String[] args) throws Exception {
        SendMessageUsecase send = new SendMessageUsecase();
        send.sendMessage();

//        ReceiveMessageUsecase receive = new ReceiveMessageUsecase();
//        receive.receiveMessage();
    }
}
