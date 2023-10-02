package io.angularpay.blog.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.angularpay.blog.domain.Author;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class GenericBlogPostApiModel {

    @NotEmpty
    private String title;

    @NotEmpty
    private String image;

    @NotEmpty
    private String summary;

    @NotEmpty
    private String link;

    @NotNull
    @Valid
    private Author author;

    @NotEmpty
    @JsonProperty("blog_post_published_on")
    private String publishedOn;

    @NotEmpty
    @JsonProperty("blog_post_last_updated")
    private String lastUpdated;
}
