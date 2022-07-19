package com.myfinance.myfinance.utils;

import java.util.Optional;

public class AssertUtil {

    public static boolean isNotNullAndNotEmpty(String param) {
        return param != null && !param.trim().isEmpty();
    }

    public static <T> T found(Optional<T> object, String message) {
        if (object.isPresent()) {
            return object.get();
        }
        throw new NotFoundException(message);
    }
}
