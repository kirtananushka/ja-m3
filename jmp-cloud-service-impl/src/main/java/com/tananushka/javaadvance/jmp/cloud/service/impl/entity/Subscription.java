package com.tananushka.javaadvance.jmp.cloud.service.impl.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name = "subscriptions")
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Subscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @NonNull
    private User user;

    @Column(name = "start_date", nullable = false)
    private LocalDateTime startDate;

    @PrePersist
    protected void onPersist() {
        if (this.startDate == null) {
            this.startDate = LocalDateTime.now();
        }
    }
}
