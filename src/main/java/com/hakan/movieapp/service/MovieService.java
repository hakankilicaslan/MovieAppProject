package com.hakan.movieapp.service;

import com.hakan.movieapp.entity.Movie;
import com.hakan.movieapp.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;

    public void save(Movie movie) {
        movieRepository.save(movie);
    }

    public Movie findById(Long id){
        Optional<Movie> optionalMovie = movieRepository.findById(id);
        if(optionalMovie.isPresent()){
            return optionalMovie.get();
        } else{
            throw new RuntimeException(("Aradığınız film bulunamadı..."));
        }
    }

    public List<Movie> findAllByIds(List<Long> ids){
        return ids.stream().map(this::findById).collect(Collectors.toList());
    }

    public List<Movie> findAll() {
        return movieRepository.findAll();
    }
}
