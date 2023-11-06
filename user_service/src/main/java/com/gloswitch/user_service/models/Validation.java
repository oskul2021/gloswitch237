package com.gloswitch.user_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Random;

@Getter
@Setter
@AllArgsConstructor
@Entity
@Table
public class Validation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Instant created_at;
    private Instant expire_at;
    private String code;
    @OneToOne(cascade = CascadeType.ALL)
    private User user;

    public Validation(){
    }
}
