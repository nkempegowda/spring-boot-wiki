package net.javaguides;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.javaguides.service.WikiMediaChangesProducer;

@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {


  @Autowired
  private WikiMediaChangesProducer wikiMediaChangesProducer;

  public static void main(String[] args) {
    SpringApplication.run(ProducerApplication.class);
  }

  @Override
  public void run(String... args) throws Exception {
    this.wikiMediaChangesProducer.sendMessage();
  }
}