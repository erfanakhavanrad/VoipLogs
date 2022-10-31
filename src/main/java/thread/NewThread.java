package thread;

import org.asteriskjava.manager.event.ManagerEvent;

public class NewThread extends Thread{

    ManagerEvent event;
    public NewThread(ManagerEvent event) {
    this.event = event;
    }

    public void run() {
        long startTime = System.currentTimeMillis();
        int i = 0;
        while (true) {
            System.out.println(this.getName() + ": New Thread is running..." + i++);
            try {
                System.out.println("thread event ==>> " + event.getCallerIdNum());
                //Wait for one sec so it doesn't print too fast
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
