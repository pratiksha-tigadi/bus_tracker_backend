package com.tracker.bus_tracker.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document("userauth")
public class AuthUser {
    @Id
    private String id;
    @Indexed
    private String username;
    private String password;
    private String phone_no;
    private String email;


    // Manually defined constructor to match usage
    public AuthUser(String username, String password, String phone_no ,String email) {
        this.username = username;
        this.password = password;
        this.phone_no= phone_no;
        this.email= email;
    }
}
