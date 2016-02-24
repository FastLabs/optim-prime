package optim.prime.concurent;

import java.util.concurrent.RecursiveTask;


public abstract class AbstractHalfingTask<T> extends RecursiveTask<T> {

    protected final long to;
    protected final long from;

    public AbstractHalfingTask(long from, long to) {
        this.to = to;
        this.from = from;
    }

    protected abstract T calculateDirectly(long from, long to);

    protected abstract T merge(T left, T right);

    protected abstract AbstractHalfingTask<T> getInstance(Long from, Long to);

    @Override
    protected T compute() {
        System.out.println(Thread.currentThread().getName());
        if (to - from <= 10) {
            return calculateDirectly(from, to);
        }
        long start1 = from,
                to1 = from + (to - from) / 2;

        final AbstractHalfingTask<T> j1 = getInstance(start1, to1);

        final AbstractHalfingTask<T> j2 = getInstance(to1 , to);
        j2.fork();
        final T r1 = j1.compute();
        final T r2 = j2.join();


        return merge(r1, r2);
    }
}
