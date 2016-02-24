package optim.prime.confg;

import optim.prime.config.AppConfig;
import optim.prime.service.PrimeCalcService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(AppConfig.class)
public class AppConfigTest {

    @Autowired
    PrimeCalcService forkJoinCalcService;

    @Test
    public void fokJoinServiceInjection() {
        assertNotNull(forkJoinCalcService);
    }
}
