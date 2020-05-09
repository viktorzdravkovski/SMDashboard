package com.viktor.ttt;

import com.viktor.ttt.repository.RepositoryMarkerInterface;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = RepositoryMarkerInterface.class)
public class Application {

  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
// TODO vzdravko 2020-05-09: 2. Login
// TODO vzdravko 2020-05-09: 3. Comments table