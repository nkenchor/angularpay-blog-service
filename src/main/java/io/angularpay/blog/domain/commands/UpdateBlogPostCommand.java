package io.angularpay.blog.domain.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.angularpay.blog.adapters.outbound.MongoAdapter;
import io.angularpay.blog.domain.BlogPost;
import io.angularpay.blog.domain.Role;
import io.angularpay.blog.exceptions.ErrorObject;
import io.angularpay.blog.models.UpdateBlogPostCommandRequest;
import io.angularpay.blog.validation.DefaultConstraintValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static io.angularpay.blog.helpers.CommandHelper.getBlogPostByReferenceOrThrow;

@Slf4j
@Service
public class UpdateBlogPostCommand extends AbstractCommand<UpdateBlogPostCommandRequest, Void> {

    private final DefaultConstraintValidator validator;
    private final MongoAdapter mongoAdapter;

    public UpdateBlogPostCommand(
            ObjectMapper mapper,
            DefaultConstraintValidator validator,
            MongoAdapter mongoAdapter) {
        super("UpdateBlogPostCommand", mapper);
        this.validator = validator;
        this.mongoAdapter = mongoAdapter;
    }

    @Override
    protected Void handle(UpdateBlogPostCommandRequest request) {
        BlogPost blogPost = getBlogPostByReferenceOrThrow(this.mongoAdapter, request.getReference()).toBuilder()
                .title(request.getGenericBlogPostApiModel().getTitle())
                .image(request.getGenericBlogPostApiModel().getImage())
                .summary(request.getGenericBlogPostApiModel().getSummary())
                .link(request.getGenericBlogPostApiModel().getLink())
                .author(request.getGenericBlogPostApiModel().getAuthor())
                .publishedOn(request.getGenericBlogPostApiModel().getPublishedOn())
                .lastUpdated(request.getGenericBlogPostApiModel().getLastUpdated())
                .build();

        this.mongoAdapter.updateBlogPost(blogPost);
        return null;
    }

    @Override
    protected List<ErrorObject> validate(UpdateBlogPostCommandRequest request) {
        return this.validator.validate(request);
    }

    @Override
    protected List<Role> permittedRoles() {
        return Arrays.asList(Role.ROLE_BLOG_ADMIN, Role.ROLE_PLATFORM_ADMIN);
    }

}
