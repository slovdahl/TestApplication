package test.application;

import org.atmosphere.cpr.AtmosphereResourceEvent;
import org.atmosphere.cpr.AtmosphereResourceEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author slovdahl
 */
public class EventListener implements AtmosphereResourceEventListener {

    private static final Logger logger = LoggerFactory.getLogger(EventListener.class);

    public void onPreSuspend(AtmosphereResourceEvent event) {
    }

    public void onSuspend(AtmosphereResourceEvent event) {
    }

    public void onResume(AtmosphereResourceEvent event) {
        logger.info("onResume(), getMessage(): {}", event.getMessage());
    }

    public void onDisconnect(AtmosphereResourceEvent event) {
    }

    public void onBroadcast(AtmosphereResourceEvent event) {
        logger.info("onBroadcast, getMessage(): {}", event.getMessage());
    }

    public void onThrowable(AtmosphereResourceEvent event) {
    }
}
