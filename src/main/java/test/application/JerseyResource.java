package test.application;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.atmosphere.annotation.Suspend;
import org.atmosphere.cpr.AtmosphereResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
public class JerseyResource {

    private static final Logger logger = LoggerFactory.getLogger(JerseyResource.class);

    @GET
    @Suspend(contentType = "application/json")
    public String handleSuspend() {
        logger.info("CLIENT SUSPENDED");
        return "";
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void handleMessage(final @Context AtmosphereResource res, final String message) {
        logger.info("MESSAGE RECEIVED: " + message);
    }
}
