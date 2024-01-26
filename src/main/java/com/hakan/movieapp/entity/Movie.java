package com.hakan.movieapp.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String language;
    private String image;
    private String name;
    private String country;
    private double rating;
    @Column(length = 2000)
    private String summary;
    private LocalDate premiered;
    private String url;
    @ManyToMany
    private List<Genre> genres;
    @OneToMany(mappedBy = "movie")
    @JsonIgnore //Movie ile MovieComment arasındaki ilişkiden dolayı tekrar tekrar aynı verileri yüklemesinin önüne geçmek için bu anotasyonu kullandık.
    private List<MovieComment> comments;
}
