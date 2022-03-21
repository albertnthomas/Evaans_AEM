package com.evaans.core.schedulers;

import com.evaans.core.config.SchedulerConfiguration;
import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(immediate = true, service = Runnable.class)
@Designate(ocd = SchedulerConfiguration.class)

public class EvaansScheduler implements Runnable {
    private static final Logger LOG = LoggerFactory.getLogger(EvaansScheduler.class);

    private int schedulerId;

    @Reference
    private Scheduler scheduler;

    @Activate
    protected void activate(SchedulerConfiguration config){
        schedulerId = config.schedulerName().hashCode();
        addScheduler(config);
    }

    @Deactivate
    protected void deactivate(SchedulerConfiguration config){
        removeScheduler();
    }

    private void removeScheduler(){
        scheduler.unschedule(String.valueOf(schedulerId));
    }

    private void addScheduler(SchedulerConfiguration config){
        ScheduleOptions scheduleOptions = scheduler.EXPR(config.cronExpression());
        scheduleOptions.name(String.valueOf(schedulerId));
        scheduleOptions.canRunConcurrently(true);
        scheduler.schedule(this, scheduleOptions);
        LOG.info("\n............Evaans Scheduler Added.........");

    }
    @Override
    public void run() {
        // LOG.info("\n ====> RUN METHOD  ");
    }


}
