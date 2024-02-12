package org.tb.bdd.cucumber.api.bom;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ClientBom {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
}
