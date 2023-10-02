package io.angularpay.blog.ports.outbound;

import io.angularpay.blog.domain.BlogPost;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PersistencePort {
    BlogPost createBlogPost(BlogPost blogPost);
    BlogPost updateBlogPost(BlogPost blogPost);
    Optional<BlogPost> findBlogPostByReference(String reference);
    Optional<BlogPost> findBlogPostByLink(String link);
    Page<BlogPost> listBlogPosts(Pageable pageable);
}
