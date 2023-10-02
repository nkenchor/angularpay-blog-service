package io.angularpay.blog.ports.inbound;

import io.angularpay.blog.models.platform.PlatformConfigurationIdentifier;

public interface InboundMessagingPort {
    void onMessage(String message, PlatformConfigurationIdentifier identifier);
}
