package io.angularpay.blog.adapters.outbound;

import io.angularpay.blog.domain.BlogPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BlogPostRepository extends MongoRepository<BlogPost, String> {

    Optional<BlogPost> findByReference(String reference);
    Optional<BlogPost> findByLink(String link);
}
