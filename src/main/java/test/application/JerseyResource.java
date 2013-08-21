package test.application;

import java.io.IOException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.atmosphere.annotation.Suspend;
import org.atmosphere.cpr.AtmosphereResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
public class JerseyResource {

    private static final Logger logger = LoggerFactory.getLogger(JerseyResource.class);

    @GET
    @Suspend(contentType = "application/json", listeners = EventListener.class)
    public String handleSuspend(final @Context AtmosphereResource resource) {
        logger.info("CLIENT SUSPENDED");
        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException ex) {
                }
                try {
                    resource.close();
                } catch (IOException ex) {
                }
            }
        });
        t.start();
        return "";
    }
}
