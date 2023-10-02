package io.angularpay.blog.helpers;

import io.angularpay.blog.adapters.outbound.MongoAdapter;
import io.angularpay.blog.domain.BlogPost;
import io.angularpay.blog.exceptions.CommandException;
import io.angularpay.blog.exceptions.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static io.angularpay.blog.exceptions.ErrorCode.DUPLICATE_REQUEST_ERROR;
import static io.angularpay.blog.exceptions.ErrorCode.REQUEST_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class CommandHelper {

    public static BlogPost getBlogPostByReferenceOrThrow(MongoAdapter mongoAdapter, String reference) {
        return mongoAdapter.findBlogPostByReference(reference).orElseThrow(
                () -> commandException(HttpStatus.NOT_FOUND, REQUEST_NOT_FOUND)
        );
    }

    public static void validateNotExistOrThrow(MongoAdapter mongoAdapter, String link) {
        mongoAdapter.findBlogPostByLink(link).ifPresent(
                (x) -> {
                    throw commandException(HttpStatus.CONFLICT, DUPLICATE_REQUEST_ERROR);
                }
        );
    }

    private static CommandException commandException(HttpStatus status, ErrorCode errorCode) {
        return CommandException.builder()
                .status(status)
                .errorCode(errorCode)
                .message(errorCode.getDefaultMessage())
                .build();
    }

}
