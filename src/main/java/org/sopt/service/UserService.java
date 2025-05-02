package org.sopt.service;

import jakarta.transaction.Transactional;
import org.sopt.domain.User;
import org.sopt.dto.request.UserRequest;
import org.sopt.dto.response.UserResponse;
import org.sopt.global.common.exception.CustomException;
import org.sopt.global.common.exception.ErrorCode;
import org.sopt.global.util.Validator;
import org.sopt.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public UserResponse signup(UserRequest request) {
        Validator.validateUserName(request.name());
        User saved = userRepository.save(new User(request.name()));
        return new UserResponse(saved.getId(), saved.getName());
    }

    public User findByIdOrThrow(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.RESOURCE_NOT_FOUND));
    }
}
