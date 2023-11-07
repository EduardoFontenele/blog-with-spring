package com.edublog.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    LocalDateTime createdAt;

    @Column(length = 2000, nullable = false)
    private String comment;

    private String author;

    @ManyToOne
    private Account account;

    @ManyToOne
    private Article article;

    public Comment(String comment, String author, Account account, Article article) {
        this.comment = comment;
        this.author = author;
        this.account = account;
        this.article = article;
    }
}
