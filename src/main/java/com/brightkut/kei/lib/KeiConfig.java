package com.brightkut.kei.lib;

import com.brightkut.kei.config.AppConfig;
import com.brightkut.kei.config.AsyncConfig;
import com.brightkut.kei.config.JpaConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({
        AppConfig.class,
        AsyncConfig.class,
        JpaConfig.class,
})
public class KeiConfig {
}
