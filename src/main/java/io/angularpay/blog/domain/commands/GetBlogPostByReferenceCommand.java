package io.angularpay.blog.domain.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.angularpay.blog.adapters.outbound.MongoAdapter;
import io.angularpay.blog.domain.BlogPost;
import io.angularpay.blog.domain.Role;
import io.angularpay.blog.exceptions.ErrorObject;
import io.angularpay.blog.models.GenericBlogPostCommandRequest;
import io.angularpay.blog.validation.DefaultConstraintValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static io.angularpay.blog.helpers.CommandHelper.getBlogPostByReferenceOrThrow;

@Slf4j
@Service
public class GetBlogPostByReferenceCommand extends AbstractCommand<GenericBlogPostCommandRequest, BlogPost> {

    private final DefaultConstraintValidator validator;
    private final MongoAdapter mongoAdapter;

    public GetBlogPostByReferenceCommand(
            ObjectMapper mapper,
            DefaultConstraintValidator validator,
            MongoAdapter mongoAdapter) {
        super("GetBlogPostByReferenceCommand", mapper);
        this.validator = validator;
        this.mongoAdapter = mongoAdapter;
    }

    @Override
    protected BlogPost handle(GenericBlogPostCommandRequest request) {
        return getBlogPostByReferenceOrThrow(this.mongoAdapter, request.getReference());
    }

    @Override
    protected List<ErrorObject> validate(GenericBlogPostCommandRequest request) {
        return this.validator.validate(request);
    }

    @Override
    protected List<Role> permittedRoles() {
        return Arrays.asList(Role.ROLE_BLOG_ADMIN, Role.ROLE_PLATFORM_ADMIN);
    }

}
