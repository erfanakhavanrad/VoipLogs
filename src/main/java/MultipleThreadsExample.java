public class MultipleThreadsExample {
    public static void main(String[] args) {

        NewThread newThread1 = new NewThread();
        newThread1.setName("My First Thread");

        NewThread newThread2 = new NewThread();
        newThread2.setName("My Second Thread");

        newThread1.start();
        newThread2.start();

    }
}
