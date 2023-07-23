import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueWithCounter<T> {
    Queue<DataWrapper> cd;

    public QueueWithCounter() {
        cd = new ConcurrentLinkedQueue();
    }

    public void add(T data, int counter) {
        if (counter > 0) {
            cd.add(new DataWrapper(data, counter));
        } else {
            throw new IllegalArgumentException ("Counter have to be more than 0");
        }
    }

    public T get() {
        if (cd.isEmpty()) {
            throw new IllegalStateException("Queue is empty");
        }

        DataWrapper dw = cd.peek();

        if (dw.counter < 2) {
            return cd.poll().data;
        } else {
            dw.counter = --dw.counter;
        }
        return dw.data;
    }

    class DataWrapper {
        T data;
        int counter;
        DataWrapper(T data, int counter) {
            this.data = data;
            this.counter = counter;
        }
    }
}
