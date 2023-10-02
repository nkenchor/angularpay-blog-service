package io.angularpay.blog.adapters.outbound;

import io.angularpay.blog.domain.BlogPost;
import io.angularpay.blog.ports.outbound.PersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MongoAdapter implements PersistencePort {

    private final BlogPostRepository blogPostRepository;

    @Override
    public BlogPost createBlogPost(BlogPost blogPost) {
        blogPost.setCreatedOn(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
        blogPost.setLastModified(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
        return blogPostRepository.save(blogPost);
    }

    @Override
    public BlogPost updateBlogPost(BlogPost blogPost) {
        blogPost.setLastModified(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
        return blogPostRepository.save(blogPost);
    }

    @Override
    public Optional<BlogPost> findBlogPostByReference(String reference) {
        return blogPostRepository.findByReference(reference);
    }

    @Override
    public Optional<BlogPost> findBlogPostByLink(String link) {
        return blogPostRepository.findByLink(link);
    }

    @Override
    public Page<BlogPost> listBlogPosts(Pageable pageable) {
        return this.blogPostRepository.findAll(pageable);
    }

}
