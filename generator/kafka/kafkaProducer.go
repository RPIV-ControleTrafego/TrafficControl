package kafka

import (
	"fmt"
	"github.com/confluentinc/confluent-kafka-go/kafka"
)


func kafkaProducer(message string, producer *KafkaProducer) {
	topic := "traffic-topic"

	err := producer.SendMessage(topic, message)
	if err != nil {
		fmt.Printf("Error sending message: %v\n", err)
	}
}

type KafkaProducer struct {
	Producer *kafka.Producer
}

type KafkaConfiguration struct {
	BootstrapServers string
}

func NewKafkaProducer(config KafkaConfiguration) (*KafkaProducer, error) {
	p, err := kafka.NewProducer(&kafka.ConfigMap{
		"bootstrap.servers": config.BootstrapServers,
	})

	if err != nil {
		return nil, err
	}

	return &KafkaProducer{
		Producer: p,
	}, nil
}

func (kp *KafkaProducer) SendMessage(topic string, message string) error {
	deliveryChan := make(chan kafka.Event)

	err := kp.Producer.Produce(&kafka.Message{
		TopicPartition: kafka.TopicPartition{Topic: &topic, Partition: kafka.PartitionAny},
		Value:          []byte(message),
	}, deliveryChan)

	if err != nil {
		return err
	}

	e := <-deliveryChan
	msg := e.(*kafka.Message)

	if msg.TopicPartition.Error != nil {
		return msg.TopicPartition.Error
	}

	fmt.Printf("Message delivered to topic %s (partition %d) at offset %d\n", *msg.TopicPartition.Topic, msg.TopicPartition.Partition, msg.TopicPartition.Offset)
	return nil
}

func NewKafkaConfiguration() KafkaConfiguration {
	return KafkaConfiguration{
		BootstrapServers: "127.0.0.1:9092",
	}
}