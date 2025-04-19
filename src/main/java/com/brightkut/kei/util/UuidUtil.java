package com.brightkut.kei.util;

import com.github.f4b6a3.uuid.UuidCreator;

import java.util.UUID;

public class UuidUtil {

    private UuidUtil() {
    }

    public static UUID generateUUIDv7(){
        return UuidCreator.getTimeOrderedEpoch();
    }
}
