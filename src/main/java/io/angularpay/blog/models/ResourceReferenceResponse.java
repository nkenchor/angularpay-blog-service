
package io.angularpay.blog.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ResourceReferenceResponse extends GenericReferenceResponse {

    private final String reference;
}
