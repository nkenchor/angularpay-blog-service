package io.angularpay.blog.domain.commands;

public interface ResourceReferenceCommand<T, R> {

    R map(T referenceResponse);
}
