package org.sopt.controller;

import org.sopt.base.BaseResponse;
import org.sopt.dto.request.PostRequest;
import org.sopt.dto.response.PostResponse;
import org.sopt.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<BaseResponse<Void>> createPost(@RequestBody final PostRequest request) {
        postService.createPost(request.title());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(BaseResponse.success(201, null));
    }

    @GetMapping
    public ResponseEntity<BaseResponse<List<PostResponse>>> getAllPosts() {
        return ResponseEntity
                .ok(BaseResponse.success(postService.getAllPosts()));
    }

    @GetMapping("/{postId}")
    public ResponseEntity<BaseResponse<PostResponse>> getPostById(@PathVariable final Long postId) {
        return ResponseEntity
                .ok(BaseResponse.success(postService.getPostById(postId)));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<BaseResponse<Void>> updatePostTitle(
        @PathVariable final Long postId,
        @RequestBody final PostRequest request
    ) {
        postService.updatePost(postId, request.title());
        return ResponseEntity
                .ok(BaseResponse.success(null));
    }

    @GetMapping("/search")
    public ResponseEntity<BaseResponse<List<PostResponse>>> searchPostsByKeyword(@RequestParam final String keyword) {
        return ResponseEntity
                .ok(BaseResponse.success(postService.searchPostsByKeyword(keyword)));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<BaseResponse<Void>> deletePostById(@PathVariable final Long postId) {
        postService.deletePost(postId);
        return ResponseEntity
                .ok(BaseResponse.success(null));
    }
}
