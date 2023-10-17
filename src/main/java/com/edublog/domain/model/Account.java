package com.edublog.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String username;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String password;

    @Column(columnDefinition = "VARCHAR(45) NOT NULL")
    private String role;

    public Account(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }
}
