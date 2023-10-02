package io.angularpay.blog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class GetBlogPostListCommandRequest extends AccessControl {

    @NotNull
    @Valid
    private Paging paging;

    GetBlogPostListCommandRequest(AuthenticatedUser authenticatedUser) {
        super(authenticatedUser);
    }
}
