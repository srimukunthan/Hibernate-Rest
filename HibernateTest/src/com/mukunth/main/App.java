package com.mukunth.main;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

 
public class App {
    public static void main(String[] args) throws Exception {
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/v1/api/");
 
        Server jettyServer = new Server(8080);
        jettyServer.setHandler(context);
 
        ServletHolder jerseyServlet = context.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/*");
        jerseyServlet.setInitOrder(0);
 
        // Tells the Jersey Servlet which REST service/class to load.
        jerseyServlet.setInitParameter(
           "jersey.config.server.provider.packages",
           "com.mukunth.exceptions,com.mukunth.model,com.mukunth.service");
 
        try {
            jettyServer.start();
            jettyServer.join();
        } finally {
            jettyServer.destroy();
        }
    }
}