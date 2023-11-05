package com.example.hakaton.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "comments")
public class CommentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column(nullable = false)
    String text;
    @Column(name = "created_at", updatable = false, nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    LocalDateTime createdAt;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "tenders_id", nullable = false)
    TenderEntity tenderEntity;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id", nullable = false)
    UserEntity author;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

}
