package optim.prime.service;


import optim.prime.domain.PrimeRange;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In memory prime storage.
 */

public class PrimeRepository {

    private static final Log logger = LogFactory.getLog(PrimeRepository.class);

    private Map<PrimeRange, List<Long>> content = new ConcurrentHashMap<>();


    public List<Long> getPrimes(long value) {
        return content.get(PrimeRange.from(0).to(value).build());
    }

    public List<Long> getPrimes(PrimeRange range) {
        return content.get(range);
    }

    public List<Long> getPrimes(PrimeRange.Builder builder) {
        return getPrimes(builder.build());
    }

    public void addPrimes(PrimeRange value, List<Long> values) {
        if (logger.isDebugEnabled()) {
            logger.debug(String.format("Prime range %s stored in cache", value));
        }
        content.put(value, values);

    }

    public void addPrimes(PrimeRange.Builder builder, List<Long> values) {
        addPrimes(builder.build(), values);
    }

}
