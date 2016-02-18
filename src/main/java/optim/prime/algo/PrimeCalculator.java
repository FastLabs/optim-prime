package optim.prime.algo;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Predicate;
@Deprecated
public class PrimeCalculator {

    public static boolean isPrime(final long n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }

        for (int i = 2; i <= Math.sqrt(n) + 1; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }


    public static boolean isPrime1(final long n) {
        if (n <= 1) {
            return false;
        }
        if (n == 2) {
            return true;
        }
        if (n % 2 == 0) {
            return false;
        }//TODO: don't forget about 5

        for (int i = 3; i <= Math.sqrt(n) + 1; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static final BiFunction<Long, Predicate<Long>, List<Long>> primesTo = (final Long from, final Predicate<Long> isPrime) -> primesFromTo(0, from, isPrime);

    public static List<Long> primesFromTo(final long from, final long to, Predicate<Long> isPrm) {
        final List<Long> set = new ArrayList<>();
        for (long i = from; i <= to; i++) {
            if (isPrm.test(i)) {
                set.add(i);
            }
        }
        return Collections.unmodifiableList(set);

    }
}
