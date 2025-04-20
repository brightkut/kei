package com.brightkut.kei.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.concurrent.Executor;

import static org.junit.jupiter.api.Assertions.assertFalse;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {AppConfig.class, AsyncConfig.class})
public class ConfigTest {
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Executor executor;

    @Test
    public void should_initialize_object_mapper_success() {
        Assertions.assertNotNull(objectMapper);
        assertFalse(objectMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
    }

    @Test
    public void should_initialize_executor_success() {
        Assertions.assertNotNull(executor);
    }
}
