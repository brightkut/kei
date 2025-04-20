package com.brightkut.kei.api;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

@ExtendWith(MockitoExtension.class)
public class ApiTest {

    @Nested
    class ApiResTest {

        @Test
        public void should_success() {
            var actual = ApiRes.success(HttpStatus.OK, "data success");
            Assertions.assertEquals(ApiStatusEnum.SUCCESS, actual.getStatus());
            Assertions.assertEquals(HttpStatus.OK.value(), actual.getCode());
            Assertions.assertEquals("data success", actual.getData());
            Assertions.assertNull(actual.getError());
        }

        @Test
        public void should_failed() {
            var actual = ApiRes.error(HttpStatus.INTERNAL_SERVER_ERROR, "server error");
            Assertions.assertEquals(ApiStatusEnum.FAILED, actual.getStatus());
            Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR.value(), actual.getCode());
            Assertions.assertNull(actual.getData());
            Assertions.assertEquals("server error", actual.getError());
        }
    }
}
