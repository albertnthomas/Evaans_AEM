package com.evaans.core.listeners;

import com.day.cq.replication.ReplicationAction;
import com.day.cq.replication.ReplicationActionType;
import org.apache.sling.event.jobs.JobManager;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.osgi.service.event.EventHandler;
import java.util.HashMap;

@Component(immediate = true,
          service = EventHandler.class,
          property = {Constants.SERVICE_DESCRIPTION + "=Replication Listener kicks off a job",
                  EventConstants.EVENT_TOPIC + "=" + ReplicationAction.EVENT_TOPIC})

public class ReplicationListener implements EventHandler {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String TOPIC = "com/evaans/core/replicationjob";

    @Reference
    private JobManager jobManager;

    @Override
    public void handleEvent(final Event event) {
        ReplicationAction action = ReplicationAction.fromEvent(event);
        logger.info("PATH: " + action.getPath().toString());
        if (action.getType().equals(ReplicationActionType.ACTIVATE)) {

            if (action.getPath() != null)
            {
                try {
                    // Create a properties map that contains things we want to pass through the job
                    HashMap<String, Object> jobprops = new HashMap<>();
                    jobprops.put("PAGE_PATH", action.getPath());
                    // Add the job
                    jobManager.addJob(TOPIC, jobprops);
                    logger.info("=============Topic: '"+TOPIC+"' with payload: '"+action.getPath()+"' was added to the Job Manager");

                } catch (Exception e) {
                    logger.error("============= ERROR CREATING JOB : NO PAYLOAD WAS DEFINED");
                    e.printStackTrace();
                }
            }
        }
    }


}
