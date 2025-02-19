package gr.netmechanics.jmix.gplace.cache;

import static gr.netmechanics.jmix.gplace.GPlaceConstants.KEY_CACHE_G_PLACE_INFO;
import static gr.netmechanics.jmix.gplace.GPlaceConstants.KEY_CACHE_G_PLACE_INFO_RAW;
import static gr.netmechanics.jmix.gplace.GPlaceConstants.KEY_CACHE_G_PLACE_RATING;

import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.cache.annotation.CacheEvict;

/**
 * @author Panos Bariamis (pbaris)
 */
@Slf4j
public class GPlaceCleaningCacheJob implements Job {

    @Override
    @CacheEvict(value = {KEY_CACHE_G_PLACE_RATING, KEY_CACHE_G_PLACE_INFO, KEY_CACHE_G_PLACE_INFO_RAW}, allEntries = true)
    public void execute(final JobExecutionContext jobExecutionContext) throws JobExecutionException {
        log.info("Cleaning Google Place cached data");
    }
}
