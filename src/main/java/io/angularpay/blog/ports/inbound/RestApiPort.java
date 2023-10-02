package io.angularpay.blog.ports.inbound;

import io.angularpay.blog.domain.BlogPost;
import io.angularpay.blog.models.GenericBlogPostApiModel;
import io.angularpay.blog.models.GenericReferenceResponse;

import java.util.List;
import java.util.Map;

public interface RestApiPort {
    GenericReferenceResponse createBlogPost(GenericBlogPostApiModel request, Map<String, String> headers);
    void updateBlogPost(String blogPostReference, GenericBlogPostApiModel request, Map<String, String> headers);
    BlogPost getBlogPostByReference(String blogPostReference, Map<String, String> headers);
    List<BlogPost> getBlogPostList(int page, Map<String, String> headers);
}
