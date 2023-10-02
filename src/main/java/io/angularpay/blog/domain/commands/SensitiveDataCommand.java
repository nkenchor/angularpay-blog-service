package io.angularpay.blog.domain.commands;

public interface SensitiveDataCommand<T> {
    T mask(T raw);
}
