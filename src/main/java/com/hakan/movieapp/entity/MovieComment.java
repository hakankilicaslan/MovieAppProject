package com.hakan.movieapp.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class MovieComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 500)
    private String content;
    @Builder.Default
    private LocalDate date = LocalDate.now();
    @ManyToOne
    private User user;
    @ManyToOne
    private Movie movie;
}
