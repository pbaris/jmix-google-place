package gr.netmechanics.jmix.gplace.cache;

import lombok.extern.slf4j.Slf4j;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Panos Bariamis (pbaris)
 */
@Slf4j
@Configuration(proxyBeanMethods = false)
@ConditionalOnClass(Job.class)
@ConditionalOnProperty(name = "jmix.gplace.use-default-cleaning-cache-quartz-configuration")
public class GPlaceCleaningCacheScheduleAutoConfiguration {

    @Value("${jmix.gplace.cleaning-cache-cron:0 0 1 1 1/1 ? *}")
    private String cron;

    @Bean("gplace_GPlaceCleaningCacheJob")
    JobDetail job() {
        return JobBuilder.newJob()
            .ofType(GPlaceCleaningCacheJob.class)
            .storeDurably()
            .withIdentity("gplaceCacheCleaning")
            .withDescription("Clean cached data from Google Place Service")
            .build();
    }

    @Bean("gplace_GPlaceCleaningCacheTrigger")
    Trigger trigger(@Qualifier("gplace_GPlaceCleaningCacheJob") final JobDetail job) {
        log.info("Schedule cleaning Google Place Service cache, using default configuration with CRON expression '{}'", cron);

        return TriggerBuilder.newTrigger()
            .withIdentity("gplaceCacheCleaningCronTrigger")
            .forJob(job)
            .startNow()
            .withSchedule(CronScheduleBuilder.cronSchedule(cron))
            .build();
    }
}
