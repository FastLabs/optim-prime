package optim.prime.domain;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PrimeRangeTest {

    @Test
    public void testBuild() {
        PrimeRange x = PrimeRange.from(10).to(100).build();
        assertEquals(10, x.from);
        assertEquals(100, x.to);
    }
}
