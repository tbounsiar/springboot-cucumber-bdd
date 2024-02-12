package org.tb.bdd.cucumber.api.bom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResponseEntity<T> {
    private final T content;
}
