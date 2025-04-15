
package com.example.restapicontactmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.restapicontactmanagement.business.services.FilesStorageService;
import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class RestapicontactmanagementApplication implements CommandLineRunner {
 
 @Autowired
  FilesStorageService filesStorageService;
    public static void main(String[] args) {
        SpringApplication.run(RestapicontactmanagementApplication.class, args);
    }
    
    //Storage initialisation
    @Override
    public void run(String... arg) throws Exception {
      log.info("Storage initialisation");
     filesStorageService.init();
    }
}
