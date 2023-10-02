package io.angularpay.blog.adapters.inbound;

import io.angularpay.blog.configurations.AngularPayConfiguration;
import io.angularpay.blog.domain.BlogPost;
import io.angularpay.blog.domain.commands.*;
import io.angularpay.blog.models.*;
import io.angularpay.blog.ports.inbound.RestApiPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import static io.angularpay.blog.helpers.Helper.fromHeaders;

@RestController
@RequestMapping("/blog/posts")
@RequiredArgsConstructor
public class RestApiAdapter implements RestApiPort {

    private final CreateBlogPostCommand createBlogPostCommand;
    private final UpdateBlogPostCommand updateBlogPostCommand;
    private final GetBlogPostByReferenceCommand getBlogPostByReferenceCommand;
    private final GetBlogPostListCommand getBlogPostListCommand;

    private final AngularPayConfiguration configuration;

    @PostMapping
    @ResponseBody
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public GenericReferenceResponse createBlogPost(
            @RequestBody GenericBlogPostApiModel request,
            @RequestHeader Map<String, String> headers) {
        AuthenticatedUser authenticatedUser = fromHeaders(headers);
        CreateBlogPostCommandRequest createBlogPostCommandRequest = CreateBlogPostCommandRequest.builder()
                .genericBlogPostApiModel(request)
                .authenticatedUser(authenticatedUser)
                .build();
        return this.createBlogPostCommand.execute(createBlogPostCommandRequest);
    }

    @PutMapping("/{blogPostReference}")
    @Override
    public void updateBlogPost(
            @PathVariable String blogPostReference,
            @RequestBody GenericBlogPostApiModel request,
            @RequestHeader Map<String, String> headers) {
        AuthenticatedUser authenticatedUser = fromHeaders(headers);
        UpdateBlogPostCommandRequest updateBlogPostCommandRequest = UpdateBlogPostCommandRequest.builder()
                .reference(blogPostReference)
                .genericBlogPostApiModel(request)
                .authenticatedUser(authenticatedUser)
                .build();
        this.updateBlogPostCommand.execute(updateBlogPostCommandRequest);
    }

    @GetMapping("/{blogPostReference}")
    @ResponseBody
    @Override
    public BlogPost getBlogPostByReference(
            @PathVariable String blogPostReference,
            @RequestHeader Map<String, String> headers) {
        AuthenticatedUser authenticatedUser = fromHeaders(headers);
        GenericBlogPostCommandRequest genericBlogPostCommandRequest = GenericBlogPostCommandRequest.builder()
                .reference(blogPostReference)
                .authenticatedUser(authenticatedUser)
                .build();
        return this.getBlogPostByReferenceCommand.execute(genericBlogPostCommandRequest);
    }

    @GetMapping("/list/page/{page}")
    @ResponseBody
    @Override
    public List<BlogPost> getBlogPostList(
            @PathVariable int page,
            @RequestHeader Map<String, String> headers) {
        AuthenticatedUser authenticatedUser = fromHeaders(headers);
        GetBlogPostListCommandRequest getBlogPostListCommandRequest = GetBlogPostListCommandRequest.builder()
                .authenticatedUser(authenticatedUser)
                .paging(Paging.builder().size(this.configuration.getPageSize()).index(page).build())
                .build();
        return this.getBlogPostListCommand.execute(getBlogPostListCommandRequest);
    }

}
