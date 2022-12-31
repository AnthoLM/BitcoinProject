package ch.hevs.ag;

public class Test {

    public static void main(String [] arg)
    {
        Thread t = new Thread(new TestRunnable("A"));
        Thread t2 = new Thread(new TestRunnable("B"));

        TestThread t3 = new TestThread("  C") ;
        t.start();
        t2.start();
        t3.start();
    }
}
