package io.angularpay.blog.adapters.inbound;

import io.angularpay.blog.domain.commands.PlatformConfigurationsConverterCommand;
import io.angularpay.blog.models.platform.PlatformConfigurationIdentifier;
import io.angularpay.blog.ports.inbound.InboundMessagingPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static io.angularpay.blog.models.platform.PlatformConfigurationSource.TOPIC;

@Service
@RequiredArgsConstructor
public class RedisMessageAdapter implements InboundMessagingPort {

    private final PlatformConfigurationsConverterCommand converterCommand;

    @Override
    public void onMessage(String message, PlatformConfigurationIdentifier identifier) {
        this.converterCommand.execute(message, identifier, TOPIC);
    }
}
