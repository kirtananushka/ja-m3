package com.tananushka.javaadvance.jmp.cloud.service.impl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

    @Entity
    @Table(name = "users")
    @Data
    @NoArgsConstructor
    @RequiredArgsConstructor
    public class User {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        
        @NonNull
        @Column(name = "name", nullable = false)
        private String name;
    
        @NonNull
        @Column(name = "surname", nullable = false)
        private String surname;
    
        @NonNull
        @Column(name = "birthday", nullable = false)
        private LocalDate birthday;
        
        @JsonIgnore
        @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
        private Set<Subscription> subscriptions;
    
    }
