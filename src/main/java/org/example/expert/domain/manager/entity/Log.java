package org.example.expert.domain.manager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    private String message;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    public Log(String message, LocalDateTime now) {
        this.message = message;
        this.createdAt = now;
    }
}
