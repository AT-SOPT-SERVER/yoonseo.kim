package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.Post;
import org.sopt.domain.User;
import org.sopt.dto.request.PostRequest;
import org.sopt.dto.response.PostDetailResponse;
import org.sopt.dto.response.PostListResponse;
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
    private final UserService userService;

    public PostService(PostRepository postRepository, UserService userService) {
        this.postRepository = postRepository;
        this.userService = userService;
    }

    @Transactional
    public void createPost(Long userId, PostRequest request) {
        String title = request.title().trim();
        String content = request.content().trim();

        Validator.validateTitle(title);
        Validator.validateContent(content);

        if (postRepository.existsByTitle(title)) {
            throw new CustomException(ErrorCode.DUPLICATE_POST_TITLE);
        }

        User writer = userService.findByIdOrThrow(userId);
        Post post = new Post(title, content, writer);
        postRepository.save(post);
    }

    public List<PostListResponse> getAllPosts() {
        return postRepository.findAllByOrderByIdDesc().stream()
                .map(post -> new PostListResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getUser().getName()
                ))
                .collect(Collectors.toList());
    }

    public PostDetailResponse getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));
        return new PostDetailResponse(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getName()
        );
    }

    @Transactional
    public void updatePost(Long id, PostRequest request) {
        String newTitle = request.title().trim();
        String newContent = request.content().trim();

        Validator.validateTitle(newTitle);
        Validator.validateContent(newContent);

        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        if (postRepository.existsByTitleAndIdNot(newTitle, id)) {
            throw new CustomException(ErrorCode.DUPLICATE_POST_TITLE);
        }
        post.setTitle(newTitle);
        post.setContent(newContent);
    }

    @Transactional
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_DELETE_NOT_FOUND));
        postRepository.delete(post);
    }

    public List<PostListResponse> searchPostsByKeyword(String keyword) {
        if (keyword == null || keyword.isBlank()) {
            throw new CustomException(ErrorCode.INVALID_INPUT_VALUE);
        }
        return postRepository.searchByKeyword(keyword.trim()).stream()
                .map(post -> new PostListResponse(
                        post.getId(),
                        post.getTitle(),
                        post.getUser().getName()
                ))
                .collect(Collectors.toList());
    }
}