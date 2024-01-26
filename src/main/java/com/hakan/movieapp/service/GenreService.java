package com.hakan.movieapp.service;

import com.hakan.movieapp.entity.Genre;
import com.hakan.movieapp.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GenreService {

    private final GenreRepository genreRepository;

    public List<Genre> saveGenreWithName(List<String> genres) {
        List<Genre> genreList = new ArrayList<>();
        for (String genreName : genres) {
            Optional<Genre> optionalGenre = genreRepository.findByName(genreName);
            if (optionalGenre.isPresent()) {
                genreList.add(optionalGenre.get());
            } else {
                Genre genre = Genre.builder()
                        .name(genreName)
                        .build();
                genreRepository.save(genre);
                genreList.add(genre);
            }
        }
        return genreList;
    }

}
