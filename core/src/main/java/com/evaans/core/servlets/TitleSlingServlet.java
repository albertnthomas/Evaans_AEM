package com.evaans.core.servlets;

import com.day.cq.wcm.api.Page;
import com.day.cq.wcm.api.PageManager;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;

//@Component(service = Servlet.class,
//            property ={
 //                       "sling.servlet.paths=/bin/evaans/titleservlet",
 //                       "sling.servlet.extensions=html"
 //           }
 //       )

@Component(service = Servlet.class,
        property ={
                "sling.servlet.resourceType=/apps/evaans/components/title",
                "sling.servlet.extensions=html"
        }
)

public class TitleSlingServlet extends SlingSafeMethodsServlet {
    private static final long serialVersionUID = 1L;
    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) throws ServletException, IOException {
        response.setHeader("Content-Type", "text/html");

        //Get the resourceResolver from the request and adapt it to the PageManager
        PageManager pm  = request.getResourceResolver().adaptTo(PageManager.class);
        //Use the PageManager to find the containing page of the resource (component)
        Page curPage = pm.getContainingPage(request.getResource());

        if(curPage != null) {
            response.getWriter().print("<h1>Sling Servlet injected this title on the " + curPage.getTitle() + " page.</h1>");
        }else {
            response.getWriter().print("<h1>Sling Servlet injected this title</h1>");
        }
        response.getWriter().close();
    }
}

