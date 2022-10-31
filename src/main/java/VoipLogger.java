import org.asteriskjava.manager.*;
import org.asteriskjava.manager.action.StatusAction;
import org.asteriskjava.manager.event.ManagerEvent;
import thread.NewThread;

import java.io.IOException;

public class VoipLogger implements ManagerEventListener {
    private ManagerConnection managerConnection;

    public VoipLogger() throws IOException {
        //  اینجا باید اطلاعاتی که برای کاربر در استریسک زده شده نوشته بشه
        // یوزر پسوورد من و نو یکیه فقط ایپیو عوض کن
  //      ManagerConnectionFactory factory = new ManagerConnectionFactory("192.168.88.130", "admin", "F@np2ss23");
      ManagerConnectionFactory factory = new ManagerConnectionFactory("192.168.119.128", "admin", "F@np2ss23");

        this.managerConnection = factory.createManagerConnection();

    }

    public void run() throws IOException, AuthenticationFailedException,
            TimeoutException, InterruptedException {


        // register for events
        managerConnection.addEventListener(this);

        // connect to Asterisk and log in
        managerConnection.login();

        // request channel state
        managerConnection.sendAction(new StatusAction());

        // wait 10 seconds for events to come in
        Thread.sleep(1000000000);

        // and finally log off and disconnect
        managerConnection.logoff();
    }

    public static void main(String[] args) throws Exception {
        VoipLogger voipLogger;
        voipLogger = new VoipLogger();
        voipLogger.run();
    }

    public void onManagerEvent(ManagerEvent event) {

        NewThread newThread1 = new NewThread(event);
        newThread1.setName("My First Thread");
//
//        thread.NewThread newThread2 = new thread.NewThread();
//        newThread2.setName("My Second Thread");

        newThread1.start();
//        newThread2.start();


        System.out.println(
                "User " + event.getCallerIdName() +
                " with number " + event.getCallerIdNum() +
                " calling and connected to operator " + event.getConnectedLineNum() +
                " with name " + event.getConnectedLineName());
    }

}
