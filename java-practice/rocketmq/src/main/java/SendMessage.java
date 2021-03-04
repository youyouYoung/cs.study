import com.alibaba.rocketmq.client.producer.DefaultMQProducer;
import com.alibaba.rocketmq.common.message.Message;

/**
 * 功能描述: 向服务器发送mq
 *
 * @author youyou
 * @date 12/28/19 5:01 PM
 */
public class SendMessage {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup("platform-web-prod");
        producer.setNamesrvAddr("10.40.34.223:9876;10.40.34.222:9876");
        producer.start();

        Message message = new Message("primeloanweb_channel_order_init", "*", "{\"orderNo\":\"HKG2019123139262148501\",\"customerId\":\"731444119827B83CA10780AD02139B29\",\"channelOrderNo\":\"19684998\"}".getBytes("UTF-8"));
        System.out.println(producer.send(message));
    }
}
