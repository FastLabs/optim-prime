package optim.prime.utils;


import optim.prime.domain.PrimeRange;
import optim.prime.service.PrimeRepository;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;

public class StubPrimeRepository extends PrimeRepository {

    public CountDownLatch addLatch;
    public CountDownLatch getLatch;

    @Override
    public void addPrimes(PrimeRange.Builder builder, List<Long> values) {
        super.addPrimes(builder, values);
        if(addLatch != null) {
            addLatch.countDown();
        }
    }

    @Override
    public List<Long> getPrimes(PrimeRange.Builder builder)  {
        if(getLatch != null) {
            getLatch.countDown();
        }
        return super.getPrimes(builder);
    }

    public void setAddLatch(int val) {
        this.addLatch = new CountDownLatch(val);
    }

    public void setGetLatch(int val) {
        this.getLatch = new CountDownLatch(val);
    }


}
