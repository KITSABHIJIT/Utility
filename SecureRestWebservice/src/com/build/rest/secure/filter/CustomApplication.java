package com.build.rest.secure.filter;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.server.ResourceConfig;
 
public class CustomApplication extends ResourceConfig 
{
    public CustomApplication() 
    {
        packages("com.build.rest.secure.webservice");
        register(LoggingFilter.class);
        register(GsonMessageBodyHandler.class);
        //Register Auth Filter here
        register(AuthenticationFilter.class);
    }
}