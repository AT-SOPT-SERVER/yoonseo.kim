package org.sopt.service;

import org.sopt.domain.Post;
import org.sopt.repository.PostRepository;
import org.sopt.util.IdGenerator;

import java.io.IOException;
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

        if (!checkDuplicateTitle(title)) return;
        getPostById(id).setTitle(title);
    }

    public boolean deletePostById(int id) {
        return postRepository.deletePostById(id);
    }

    public List<Post> searchPostsByKeyword(String keyword) {
        return postRepository.searchPostsByKeyword(keyword);
    }

    public void savePosts(String path) throws IOException {
        postRepository.savePostsToFile(path);
    }

    public List<String> loadPosts(String path) throws IOException {
        return postRepository.loadPostsFromFile(path);
    }

    private boolean checkDuplicateTitle(String title) {
        for (Post post : getAllPosts()) {
            if (post.getTitle().equals(title)) {
                throw new IllegalArgumentException("이미 존재하는 게시글 제목입니다.");
            }
        }
        return true;
    }
}
