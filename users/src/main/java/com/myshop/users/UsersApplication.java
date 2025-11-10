package com.myshop.users;

import com.myshop.users.dtos.UserDto;
import com.myshop.users.services.servicesImpl.IUserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import static com.myshop.users.entities.Role.ADMIN;
import static com.myshop.users.entities.Role.MANAGER;

@SpringBootApplication
public class UsersApplication {
    public static void main(String[] args) {
        SpringApplication.run(UsersApplication.class , args);
    }

//    @Bean
//    public CommandLineRunner commandLineRunner(
//            IUserServiceImpl service
//    ) {
//        return args -> {
//            var admin = UserDto.builder()
//                    .username("Admin")
//                    .firstname("Admin")
//                    .lastname("Admin")
//                    .email("admin@mail.com")
//                    .password("password")
//                    .role(ADMIN)
//                    .build();
//
//            service.addUser(admin);
//            System.out.println(admin);
//
//            var manager = UserDto.builder()
//                    .username("manager")
//                    .firstname("manager")
//                    .lastname("manager")
//                    .email("manager@mail.com")
//                    .password("password")
//                    .role(MANAGER)
//                    .build();
//            service.addUser(manager);
//            System.out.println(manager);
//        };
//}
}

