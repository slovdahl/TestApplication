package test.application;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.atmosphere.annotation.Suspend;
import org.atmosphere.cpr.AtmosphereResource;
import org.atmosphere.cpr.Broadcaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
public class JerseyResource {

    private static final Logger logger = LoggerFactory.getLogger(JerseyResource.class);

    @GET
    @Suspend(contentType = "application/json")
    public String handleSuspend(final @Context Broadcaster broadcaster) {
        logger.info("CLIENT SUSPENDED");
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }
                broadcaster.broadcast("test-message-1");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }
                broadcaster.broadcast("test-message-2");
            }
        });
        t.start();
        return "CONNECTED";
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    public void handleMessage(final @Context AtmosphereResource res, final String message) {
        logger.info("MESSAGE RECEIVED: " + message);
    }
}
