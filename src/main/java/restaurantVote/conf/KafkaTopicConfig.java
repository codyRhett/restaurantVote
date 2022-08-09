package restaurantVote.conf;

import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;

import java.util.HashMap;
import java.util.Map;


//@Configuration
public class KafkaTopicConfig {

    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.user.topic.name}")
    private String userTopicName;

    @Value(value = "${kafka.test.topic.name}")
    private String testTopicName;

    @Value(value = "${kafka.restaurant.topic.name}")
    private String restaurantTopicName;

    //KafkaAdmin Spring bean will automatically add topics for all beans of type NewTopic
    @Bean
    public KafkaAdmin kafkaAdmin() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        return new KafkaAdmin(configs);
    }

    @Bean
    public NewTopic userTopic() {
        return TopicBuilder.name(userTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic testTopic() {
        return TopicBuilder.name(testTopicName)
                .partitions(1)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic restaurantTopic() {
        return TopicBuilder.name(restaurantTopicName)
                .partitions(3)
                .replicas(1)
                .build();
    }
}
