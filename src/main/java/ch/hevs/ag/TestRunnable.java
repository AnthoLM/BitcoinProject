package ch.hevs.ag;

public class TestRunnable implements Runnable{

    String name ;
    public TestRunnable(String name) {
        this.name = name ;
    }

    public void run(){
        for(int i = 0; i<10; i++) {
            System.out.println(name);
        }
    }
}
