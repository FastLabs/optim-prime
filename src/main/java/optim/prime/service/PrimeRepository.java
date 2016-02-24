package optim.prime.service;


import optim.prime.app.Utils;

import java.util.List;

public class PrimeRepository {
    public List<Long> getPrimes(long value) {
        return Utils.stubPrimes(value);
    }

    public void addResult(long value, List<Long> values) {

    }

}
