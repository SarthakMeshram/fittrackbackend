package com.sarthak.fittrackbackend.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    // @Column(nullable = false)
    // private String password;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    
    @OneToMany(mappedBy = "user")
    @JsonIgnore
    private List<Workout> workouts;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}
