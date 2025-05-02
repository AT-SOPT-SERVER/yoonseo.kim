package org.sopt.controller;

import org.sopt.dto.request.UserRequest;
import org.sopt.dto.response.UserResponse;
import org.sopt.global.common.base.BaseResponse;
import org.sopt.global.common.response.SuccessCode;
import org.sopt.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public BaseResponse<UserResponse> signup(
        @RequestBody UserRequest request
    ) {
        UserResponse response = userService.signup(request);
        return BaseResponse.success(SuccessCode.REGISTER_USER, response);
    }
}
