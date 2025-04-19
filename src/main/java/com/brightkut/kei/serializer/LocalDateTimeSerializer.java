package com.brightkut.kei.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import static com.brightkut.kei.constant.DateTimeConstant.DATE_TIME_WITH_TIMEZONE_PATTERN;
import static com.brightkut.kei.constant.DateTimeConstant.ZONE_BANGKOK;

public class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_TIME_WITH_TIMEZONE_PATTERN);
    private static final ZoneId ZONE_ID = ZoneId.of(ZONE_BANGKOK);

    @Override
    public void serialize(LocalDateTime localDateTime, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        OffsetDateTime offsetDateTime = localDateTime.atZone(ZONE_ID).toOffsetDateTime();
        String formattedDate = offsetDateTime.format(dateTimeFormatter);
        jsonGenerator.writeString(formattedDate);
    }
}
