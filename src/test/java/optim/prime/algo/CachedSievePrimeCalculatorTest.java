package optim.prime.algo;


import akka.agent.Agent;
import akka.dispatch.ExecutionContexts;
import akka.dispatch.Mapper;
import org.junit.Test;
import scala.concurrent.ExecutionContext;
import scala.concurrent.Future;

import java.util.BitSet;

public class CachedSievePrimeCalculatorTest {

    private CachedSievePrimeCalculator primeCalculator = new CachedSievePrimeCalculator();

    @Test
    public void testIt() {
        primeCalculator.getPrimes(0L, 100L);
        primeCalculator.getPrimes(0L, 100L);
        primeCalculator.getPrimes(0L, 1500L);
        primeCalculator.getPrimes(0L, 15000L);
        primeCalculator.getPrimes(0L, 150000L);
    }

    @Test
    public void testAgent()  throws Exception{
        ExecutionContext ec = ExecutionContexts.global();
        BitSet x = new BitSet(10);
        x.set(0, 10, true);
        Agent<BitSet> ag = Agent.create(x, ec);

        Future<BitSet> f = ag.alter(new Mapper<BitSet, BitSet>() {
            @Override
            public BitSet apply(BitSet parameter) {
                System.out.println("change agent");
                parameter.set(3, false);
                return parameter;
            }
        });
        Thread.currentThread().sleep(1000);
        //System.out.println( ag.apply());

        System.out.println(f.value());

    }
    @Test
    public void testAgent1 () {
        ExecutionContext ex = ExecutionContexts.global();
        Agent<Integer> agent = Agent.create(10, ex);
        agent.send(11);
        agent.apply();
        System.out.println(agent.get());
    }
}
