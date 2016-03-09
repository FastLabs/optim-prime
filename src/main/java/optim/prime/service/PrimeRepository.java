package optim.prime.service;


import optim.prime.domain.PrimeRange;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PrimeRepository {

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
        content.put(value, values);
    }

    public void addPrimes(PrimeRange.Builder builder, List<Long> values) {
        addPrimes(builder.build(), values);
    }

}
