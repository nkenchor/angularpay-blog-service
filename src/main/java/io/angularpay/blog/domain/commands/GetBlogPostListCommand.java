package io.angularpay.blog.domain.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.angularpay.blog.adapters.outbound.MongoAdapter;
import io.angularpay.blog.domain.BlogPost;
import io.angularpay.blog.domain.Role;
import io.angularpay.blog.exceptions.ErrorObject;
import io.angularpay.blog.models.GetBlogPostListCommandRequest;
import io.angularpay.blog.validation.DefaultConstraintValidator;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class GetBlogPostListCommand extends AbstractCommand<GetBlogPostListCommandRequest, List<BlogPost>> {

    private final MongoAdapter mongoAdapter;
    private final DefaultConstraintValidator validator;

    public GetBlogPostListCommand(ObjectMapper mapper, MongoAdapter mongoAdapter, DefaultConstraintValidator validator) {
        super("GetBlogPostListCommand", mapper);
        this.mongoAdapter = mongoAdapter;
        this.validator = validator;
    }

    @Override
    protected List<BlogPost> handle(GetBlogPostListCommandRequest request) {
        Pageable pageable = PageRequest.of(request.getPaging().getIndex(), request.getPaging().getSize());
        return this.mongoAdapter.listBlogPosts(pageable).getContent();
    }

    @Override
    protected List<ErrorObject> validate(GetBlogPostListCommandRequest request) {
        return this.validator.validate(request);
    }

    @Override
    protected List<Role> permittedRoles() {
        return Arrays.asList(Role.ROLE_BLOG_ADMIN, Role.ROLE_PLATFORM_ADMIN);
    }
}
