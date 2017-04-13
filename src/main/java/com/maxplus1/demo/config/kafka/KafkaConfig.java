package com.maxplus1.demo.config.kafka;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.ProducerFactory;

import java.util.Map;
import java.util.Objects;

/**
 * http://docs.spring.io/spring-boot/docs/1.5.3.BUILD-SNAPSHOT/reference/htmlsingle/#boot-features-kafka
 */
@Configuration
@EnableKafka
public class KafkaConfig {

    /**
     * Customized ProducerFactory bean.
     * @param properties the kafka properties.
     * @return the bean.
     */
    @Bean("kafkaProducerFactory")
    public ProducerFactory<?, ?> kafkaProducerFactory(KafkaProperties properties) {
        Map<String, Object> producerProperties = properties.buildProducerProperties();
        return new DefaultKafkaProducerFactorySerializable<>(producerProperties);
    }

    /**
     * 注意：目前Spring Boot自动配置只支持单个分组group-id创建consumer，
     * 如需要多个应该创建多个不同的DefaultKafkaConsumerFactory properties.getConsumer().setGroupId(groupId);
     * Customized ConsumerFactory bean.
     * @param properties the kafka properties.
     * @return the bean.
     */
    @Bean("kafkaConsumerFactory")
    public ConsumerFactory<?, ?> kafkaConsumerFactory(KafkaProperties properties) {
        Map<String, Object> consumerProperties = properties.buildConsumerProperties();
        return new DefaultKafkaConsumerFactorySerializable<>(consumerProperties);
    }
}
