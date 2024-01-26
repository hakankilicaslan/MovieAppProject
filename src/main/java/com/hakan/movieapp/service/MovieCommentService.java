package com.hakan.movieapp.service;

import com.hakan.movieapp.repository.MovieCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MovieCommentService {

    private final MovieCommentRepository movieCommentRepository;

}