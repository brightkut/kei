package com.brightkut.kei.util;

import com.github.f4b6a3.uuid.UuidCreator;
import lombok.experimental.UtilityClass;

import java.util.UUID;

@UtilityClass
public class UuidUtil {
    public static UUID generateUUIDv7(){
        return UuidCreator.getTimeOrderedEpoch();
    }
}
