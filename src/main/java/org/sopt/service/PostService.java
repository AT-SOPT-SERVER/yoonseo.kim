package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.Post;
import org.sopt.dto.response.PostResponse;
import org.sopt.global.common.exception.CustomException;
import org.sopt.global.common.exception.ErrorCode;
import org.sopt.global.util.Validator;
import org.sopt.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostService {
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Transactional
    public void createPost(String title) {
        Validator.validateTitle(title);
        if (postRepository.existsByTitle(title)) {
            throw new CustomException(ErrorCode.DUPLICATE_POST_TITLE);
        }
        postRepository.save(new Post(title));
    }

    public List<PostResponse> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> new PostResponse(post.getId(), post.getTitle()))
                .collect(Collectors.toList());
    }

    public PostResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        return new PostResponse(post.getId(), post.getTitle());
    }

    @Transactional
    public void updatePost(Long id, String newTitle) {
        Validator.validateTitle(newTitle);
        if (postRepository.existsByTitle(newTitle)) {
            throw new CustomException(ErrorCode.DUPLICATE_POST_TITLE);
        }
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        post.setTitle(newTitle);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_DELETE_NOT_FOUND));
        postRepository.delete(post);
    }

    public List<PostResponse> searchPostsByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new CustomException(ErrorCode.EMPTY_POST_TITLE);
        }
        return postRepository.searchByKeyword(keyword.trim()).stream()
                .map(post -> new PostResponse(post.getId(), post.getTitle()))
                .collect(Collectors.toList());
    }
}