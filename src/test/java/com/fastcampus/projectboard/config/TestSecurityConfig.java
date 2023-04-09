package com.fastcampus.projectboard.config;

import com.fastcampus.projectboard.dto.UserAccountDto;
import com.fastcampus.projectboard.service.UserAccountService;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

// controller test 에서 사용
@Import(SecurityConfig.class)
public class TestSecurityConfig {

    @MockBean private UserAccountService userAccountService;

    @BeforeTestMethod // 각 test code 가 실행되기 전에 인증 정보를 넣어줌.
    public void securitySetUp() {
        given(userAccountService.searchUser(anyString()))
                .willReturn(Optional.of(createUserAccountDto()));
        given(userAccountService.saveUser(anyString(), anyString(), anyString(), anyString(), anyString()))
                .willReturn(createUserAccountDto());
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "unoTest",
                "pw",
                "uno-test@email.com",
                "uno-test",
                "test memo"
        );
    }
}
