package test.application;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;

import org.atmosphere.annotation.Suspend;
import org.atmosphere.cpr.Broadcaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Path("/")
public class JerseyResource {

    private static final Logger logger = LoggerFactory.getLogger(JerseyResource.class);
    private static Thread thread = null;
    private final int numberOfMessages = 5;

    @GET
    @Suspend(contentType = "application/json", listeners = EventListener.class)
    public String handleSuspend(final @Context Broadcaster broadcaster) {
        logger.info("CLIENT SUSPENDED");
        if (thread == null) {
            thread = new Thread(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                    }
                    for (int i = 0; i < numberOfMessages; ++i) {
                        broadcaster.broadcast("test-message-" + i);
                    }
                }
            });
            thread.start();
        }
        return "";
    }
}
