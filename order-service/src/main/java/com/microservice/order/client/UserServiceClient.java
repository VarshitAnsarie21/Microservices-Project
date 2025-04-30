package com.microservice.order.client;

import com.microservice.order.dto.UserDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class UserServiceClient {
    private final RestTemplate restTemplate = new RestTemplate();

    public UserDTO getUserById(Long userId) {
        String url = "http://localhost:8081/api/user/" + userId;
        return restTemplate.getForObject(url, UserDTO.class);
    }
}
