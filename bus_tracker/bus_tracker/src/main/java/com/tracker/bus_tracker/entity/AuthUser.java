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

    // Manually defined constructor to match usage
    public AuthUser(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
