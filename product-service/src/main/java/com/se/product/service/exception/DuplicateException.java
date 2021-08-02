package com.se.product.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;
import java.util.stream.Collectors;

@ResponseStatus(HttpStatus.CONFLICT)
public class DuplicateException extends RuntimeException {

    public DuplicateException(String resourceName, Map<String, String> map) {
        super(String.format("%s %s already exists", resourceName, convertWithStream(map)));
    }

    public static String convertWithStream(Map<String, ?> map) {
        String mapAsString = map.keySet().stream()
                .map(key -> key + "=" + map.get(key))
                .collect(Collectors.joining(", ", "{", "}"));
        return mapAsString;
    }
}
