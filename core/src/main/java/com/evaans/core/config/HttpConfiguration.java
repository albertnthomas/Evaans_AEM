package com.evaans.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;
import org.osgi.service.metatype.annotations.AttributeType;
import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.Option;

@ObjectClassDefinition(name = "Http Configuration",
                       description = "OSGI config for HTTP")

public @interface HttpConfiguration {

    @AttributeDefinition(
            name = "Enable Config",
                         description = "This checkbox will enable or not",
                          type = AttributeType.BOOLEAN)
    public boolean enableConfig();

    @AttributeDefinition(
            name = "Protocol",
            description = "choose protocol",
            options = {
                    @Option(label = "HTTP", value = "http"),
                    @Option(label = "HTTPS", value = "https")
            }
    )
    public String getProtocol();

    @AttributeDefinition(name = "Server Name",
                         description = "Enter Server name"
                         )
    public String getServer();

    @AttributeDefinition(name = "Endpoint",
                         description = "Enter the endpoint")
    public String getEndpoint();

}
