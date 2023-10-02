package io.angularpay.blog.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class UpdateBlogPostCommandRequest extends AccessControl {

    @NotEmpty
    private String reference;

    @NotNull
    @Valid
    private GenericBlogPostApiModel genericBlogPostApiModel;

    UpdateBlogPostCommandRequest(AuthenticatedUser authenticatedUser) {
        super(authenticatedUser);
    }
}
