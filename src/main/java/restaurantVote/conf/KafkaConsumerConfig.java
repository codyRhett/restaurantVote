package restaurantVote.conf;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import restaurantVote.dto.RestaurantDto;
import restaurantVote.dto.UserDto;

import java.util.HashMap;
import java.util.Map;

// We are creating a consumer who will be listening one topic we created

@Configuration
@ConditionalOnProperty(value = "kafka.enable", havingValue = "true", matchIfMissing = true)
public class KafkaConsumerConfig {
    @Value(value = "${kafka.bootstrapAddress}")
    private String bootstrapAddress;

    @Value(value = "${kafka.topic.group.id}")
    private String groupId;

    @Value(value = "${kafka.test.topic.group.id}")
    private String testGroupId;

    @Value(value = "${kafka.restaurant.topic.group.id}")
    private String restaurantGroupId;

    @Bean
    public ConsumerFactory<String, String> testConsumerFactory() {
        // is used to create new Consumer instances where all consumer share
        // common configuration properties mentioned in this bean.
        return new DefaultKafkaConsumerFactory<>(testConsumerConfigs());
    }

    // Consume UserDto data from Kafka
    @Bean
    public ConsumerFactory<String, UserDto> userConsumerFactory() {
        // is used to create new Consumer instances where all consumer share
        // common configuration properties mentioned in this bean.
        return new DefaultKafkaConsumerFactory<>(userConsumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(UserDto.class));
    }

    // Consume RestaurantDto data from Kafka
    @Bean
    public ConsumerFactory<String, RestaurantDto> restaurantConsumerFactory() {
        // is used to create new Consumer instances where all consumer share
        // common configuration properties mentioned in this bean.
        return new DefaultKafkaConsumerFactory<>(userConsumerConfigs(),
                new StringDeserializer(),
                new JsonDeserializer<>(RestaurantDto.class));
    }

    @Bean
    public Map<String, Object> testConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, testGroupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return props;
    }

    @Bean
    public Map<String, Object> userConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return props;
    }

    @Bean
    public Map<String, Object> restaurantConsumerConfigs() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapAddress);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, restaurantGroupId);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, true);
        return props;
    }

    // is used to build ConcurrentMessageListenerContainer. This factory is primarily for building containers for @KafkaListener annotated methods.
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, UserDto> userKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, UserDto> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(userConsumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> testKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, String> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(testConsumerFactory());
        return factory;
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, RestaurantDto> restaurantKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, RestaurantDto> factory
                = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(restaurantConsumerFactory());
        return factory;
    }
}
