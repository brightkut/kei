package com.brightkut.kei.lib;

import com.brightkut.kei.config.AppConfig;
import com.brightkut.kei.config.AsyncConfig;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@Import({
        AppConfig.class,
        AsyncConfig.class
})
public class KeiConfig {
}
