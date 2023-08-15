package net.javaguides.service;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventHandler;
import com.launchdarkly.eventsource.EventSource;

@Service
public class WikiMediaChangesProducer {

  @Value("${spring.kafka.topic.name}")
  private String topicName;

  @Autowired
  private KafkaTemplate<String, String> kafkaTemplate;

  private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangesProducer.class);

  public void sendMessage() throws InterruptedException {
    //read real time stream data from wiki media
    EventHandler eventHandler = new WikiMediaChangesHandler(topicName, kafkaTemplate);
    String url = "https://stream.wikimedia.org/v2/stream/recentchange";
    EventSource.Builder builder = new EventSource.Builder(eventHandler, URI.create(url));
    EventSource eventSource = builder.build();
    eventSource.start();

    TimeUnit.MINUTES.sleep(10);
  }
}
