package com.music.MusicCatalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.music.MusicCatalog.DTO.request.RoleRequest;
import com.music.MusicCatalog.Service.RoleService;

@SpringBootApplication
public class MokhlisBelhajS4B2MusicCatalogApplication {

	@Autowired
	private RoleService roleService;

	public static void main(String[] args) {
		SpringApplication.run(MokhlisBelhajS4B2MusicCatalogApplication.class, args);
	}

	 @Bean
    public CommandLineRunner initRoles() {
        return args -> {
            try {
                RoleRequest userRole = new RoleRequest();
                userRole.setName("USER");
                roleService.createRole(userRole);

                RoleRequest adminRole = new RoleRequest();
                adminRole.setName("ADMIN");
                roleService.createRole(adminRole);

                System.out.println("Roles initialized successfully");
            } catch (Exception e) {
                System.out.println("Roles already exist: " + e.getMessage());
            }
        };
    }

}
