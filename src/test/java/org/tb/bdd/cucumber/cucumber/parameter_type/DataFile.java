package org.tb.bdd.cucumber.cucumber.parameter_type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.tb.bdd.cucumber.cucumber.util.TestHelper;

@RequiredArgsConstructor
public class DataFile {

    @Getter
    private final String path;
    private final Type type;

    public enum Type {
        JSON,
        CSV,
        SQL
    }

    public String getContent() {
        return TestHelper.readFile("/feature/data/" + path);
    }
}
