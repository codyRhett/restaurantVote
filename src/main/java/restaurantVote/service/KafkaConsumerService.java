package restaurantVote.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import restaurantVote.dto.RestaurantDto;
import restaurantVote.dto.UserDto;

@Service
public class KafkaConsumerService {

    private final Logger logger
            = LoggerFactory.getLogger(KafkaConsumerService.class);

    @KafkaListener(topics = "${kafka.user.topic.name}",
            groupId = "${kafka.topic.group.id}",
            containerFactory = "userKafkaListenerContainerFactory")
    public void consume(UserDto userDto) {
        logger.info(String.format("Message recieved -> %s", userDto));
    }

    @KafkaListener(topics = "${kafka.restaurant.topic.name}",
            groupId = "${kafka.restaurant.topic.group.id}",
            containerFactory = "restaurantKafkaListenerContainerFactory")
    public void consume(RestaurantDto restaurantDto) {
        logger.info(String.format("Message recieved -> %s", restaurantDto));
    }

    @KafkaListener(topics = "${kafka.test.topic.name}",
            groupId = "${kafka.test.topic.group.id}")
    public void consume(String text) {
        logger.info(String.format("Message recieved -> %s", text));
    }
}
