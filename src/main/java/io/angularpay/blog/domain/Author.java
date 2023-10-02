package io.angularpay.blog.domain;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
public class Author {

    @NotEmpty
    private String name;

    @NotEmpty
    private String bio;

    @NotEmpty
    private String avatar;
}
