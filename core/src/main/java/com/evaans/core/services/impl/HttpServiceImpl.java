package com.evaans.core.services.impl;

import com.evaans.core.config.HttpConfiguration;
import com.evaans.core.services.HttpService;
import com.evaans.core.utils.Network;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = HttpService.class, immediate = true)
@Designate(ocd = HttpConfiguration.class)

public class HttpServiceImpl implements HttpService {

    private static final Logger log = LoggerFactory.getLogger(HttpServiceImpl.class);

    private HttpConfiguration httpConfiguration;

    @Activate
    protected void activate(HttpConfiguration httpConfiguration) {
        this.httpConfiguration = httpConfiguration;

    }

        @Override
                public String makeHttpCall(){

            log.info("----------< Reading the config values >----------");
            try{
                boolean enable = httpConfiguration.enableConfig();
                String protocol = httpConfiguration.getProtocol();
                String server = httpConfiguration.getServer();
                String endpoint = httpConfiguration.getEndpoint();

                String url = protocol + "://" + server + "/" + endpoint;

                if (enable) {
                    /**
                     * Making the actual HTTP call
                     */
                    String response = Network.readJson(url);

                    /**
                     * Printing the response in the logs
                     */
                    log.info("----------< JSON response from the webservice is >----------");
                    log.info(response);

                    return response;

                } else {

                    log.info("----------< Configuration is not enabled >----------");

                    return "Configuration not enabled";
                }

            } catch (Exception e) {

                log.error(e.getMessage(), e);

                return "Error occurred" + e.getMessage();
            }
        }

}