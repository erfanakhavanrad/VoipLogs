public class SingleThread {
    public static void main(String[] args) {
        NewThread newThread = new NewThread();
//        newThread.run();
        newThread.start();
    }
}
