
package io.angularpay.blog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Document("blog_posts")
public class BlogPost {

    @Id
    private String id;
    @Version
    private int version;
    private String reference;
    @JsonProperty("created_on")
    private String createdOn;
    @JsonProperty("last_modified")
    private String lastModified;
    private String title;
    private String image;
    private String summary;
    private String link;
    private Author author;
    @JsonProperty("blog_post_published_on")
    private String publishedOn;
    @JsonProperty("blog_post_last_updated")
    private String lastUpdated;
}
