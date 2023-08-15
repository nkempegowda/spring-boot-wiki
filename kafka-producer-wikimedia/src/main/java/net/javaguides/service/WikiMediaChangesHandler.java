package net.javaguides.service;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.MessageEvent;


public class WikiMediaChangesHandler implements com.launchdarkly.eventsource.EventHandler{

  private static final Logger LOGGER = LoggerFactory.getLogger(WikiMediaChangesHandler.class);


  private KafkaTemplate<String, String> kafkaTemplate;


  private String topic;

  public WikiMediaChangesHandler(String topic, KafkaTemplate<String, String> kafkaTemplate) {

    this.kafkaTemplate = kafkaTemplate;
    this.topic = topic;
  }

  @Override
  public void onMessage(String s, MessageEvent messageEvent) throws Exception {
      LOGGER.info(String.format("event data -> %s ", messageEvent.getData()));
      kafkaTemplate.send(topic, messageEvent.getData());
  }

  @Override
  public void onOpen() throws Exception {

  }

  @Override
  public void onClosed() throws Exception {

  }



  @Override
  public void onComment(String s) throws Exception {

  }

  @Override
  public void onError(Throwable throwable) {

  }
}
