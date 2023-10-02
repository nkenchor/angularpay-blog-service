package io.angularpay.blog.domain.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.angularpay.blog.adapters.outbound.MongoAdapter;
import io.angularpay.blog.domain.BlogPost;
import io.angularpay.blog.domain.Role;
import io.angularpay.blog.exceptions.ErrorObject;
import io.angularpay.blog.models.CreateBlogPostCommandRequest;
import io.angularpay.blog.models.ResourceReferenceResponse;
import io.angularpay.blog.validation.DefaultConstraintValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static io.angularpay.blog.helpers.CommandHelper.validateNotExistOrThrow;

@Slf4j
@Service
public class CreateBlogPostCommand extends AbstractCommand<CreateBlogPostCommandRequest, ResourceReferenceResponse> {

    private final DefaultConstraintValidator validator;
    private final MongoAdapter mongoAdapter;

    public CreateBlogPostCommand(
            ObjectMapper mapper,
            DefaultConstraintValidator validator,
            MongoAdapter mongoAdapter) {
        super("CreateBlogPostCommand", mapper);
        this.validator = validator;
        this.mongoAdapter = mongoAdapter;
    }

    @Override
    protected ResourceReferenceResponse handle(CreateBlogPostCommandRequest request) {
        validateNotExistOrThrow(this.mongoAdapter, request.getGenericBlogPostApiModel().getLink());
        BlogPost blogPost = BlogPost.builder()
                .reference(UUID.randomUUID().toString())
                .title(request.getGenericBlogPostApiModel().getTitle())
                .image(request.getGenericBlogPostApiModel().getImage())
                .summary(request.getGenericBlogPostApiModel().getSummary())
                .link(request.getGenericBlogPostApiModel().getLink())
                .author(request.getGenericBlogPostApiModel().getAuthor())
                .publishedOn(request.getGenericBlogPostApiModel().getPublishedOn())
                .lastUpdated(request.getGenericBlogPostApiModel().getLastUpdated())
                .build();

        BlogPost response = this.mongoAdapter.createBlogPost(blogPost);
        return new ResourceReferenceResponse(response.getReference());
    }

    @Override
    protected List<ErrorObject> validate(CreateBlogPostCommandRequest request) {
        return this.validator.validate(request);
    }

    @Override
    protected List<Role> permittedRoles() {
        return Arrays.asList(Role.ROLE_BLOG_ADMIN, Role.ROLE_PLATFORM_ADMIN);
    }

}
