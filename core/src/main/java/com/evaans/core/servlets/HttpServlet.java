package com.evaans.core.servlets;

import com.evaans.core.services.HttpService;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=HTTP servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/evaans/httpcall", "sling.servlet.extensions=html" })


public class HttpServlet extends SlingSafeMethodsServlet {
   // private static final long serialVersionUID = -2014397651676211439L;

    private static final Logger log = LoggerFactory.getLogger(HttpServlet.class);

    @Reference
    private HttpService httpService;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

        try {

            String jsonResponse = httpService.makeHttpCall();

            /**
             * Printing the json response on the browser
             */
            response.getWriter().println(jsonResponse);
          //  response.getWriter().print("Sling Servlet injected this title");

        } catch (Exception e) {

            log.error(e.getMessage(), e);
        }
    }

}
