package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.util.IdGenerator;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

public class PostService {
    private PostRepository postRepository = new PostRepository();

    public void createPost(String title) {
        List<Post> postList = postRepository.findAll();
        if (!postList.isEmpty()) {
            Post lastPost = postList.get(postList.size() - 1);
            LocalDateTime lastCreatedAt = lastPost.getCreatedAt();
            LocalDateTime now = LocalDateTime.now();

            long secondsBetween = Duration.between(lastCreatedAt, now).getSeconds();
            if (secondsBetween < 180) {
                throw new IllegalArgumentException("새로운 게시글은 마지막 게시글 작성 이후 3분 뒤에 작성할 수 있습니다.");
            }
        }
        validateTitle(title);
        checkDuplicateTitle(title);
        Post post = new Post(IdGenerator.generatePostId(), title, LocalDateTime.now());
        postRepository.save(post);
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public Post getPostById(int id) {
        return postRepository.findPostById(id);
    }

    public void editPostById(int id, String title) {
        Post post = getPostById(id);
        if (post == null) {
            throw new IllegalArgumentException("해당 ID의 게시글이 존재하지 않습니다.");
        }
        if (!validateTitle(title) || !checkDuplicateTitle(title)) return;
        getPostById(id).setTitle(title);
    }

    public boolean deletePostById(int id) {
        return postRepository.deletePostById(id);
    }

    private boolean validateTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("제목이 비어 있습니다.");
        }
        if (title.length() > 30) {
            throw new IllegalArgumentException("제목은 최대 30자까지 가능합니다.");
        }
        return true;
    }

    private boolean checkDuplicateTitle(String title) {
        for (Post post : postRepository.findAll()) {
            if (post.getTitle().equals(title)) {
                throw new IllegalArgumentException("이미 존재하는 게시글 제목입니다.");
            }
        }
        return true;
    }
}
