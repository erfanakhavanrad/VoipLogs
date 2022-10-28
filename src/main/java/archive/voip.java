package archive;

import org.asteriskjava.manager.*;
import org.asteriskjava.manager.action.StatusAction;
import org.asteriskjava.manager.event.ManagerEvent;
import java.io.IOException;
//**
// * @author Aref Azizi
// * @date 10/24/22
// */
public class voip implements ManagerEventListener
{
    private ManagerConnection managerConnection;

    public voip() throws IOException
    {
        //  اینجا باید اطلاعاتی که برای کاربر در استریسک زده شده نوشته بشه
        // یوزر پسوورد من و نو یکیه فقط ایپیو عوض کن
        ManagerConnectionFactory factory = new ManagerConnectionFactory("192.168.119.128", "admin", "F@np2ss23");

        this.managerConnection = factory.createManagerConnection();

    }

    public void run() throws IOException, AuthenticationFailedException,
            TimeoutException, InterruptedException
    {


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

    public static void main(String[] args) throws Exception
    {
        voip helloManager;
        helloManager = new voip();
        helloManager.run();
    }

    public void onManagerEvent(ManagerEvent event)
    {

//        System.out.println(" MAnager E "+event.getCallerIdName());
//        System.out.println(" MAnager E "+event);
//        System.out.println(" MAnager E "+event.getCallerIdNum());
//        System.out.println(" MAnager E "+event.getConnectedLineName());
//        System.out.println(" MAnager E "+event.getConnectedLineNum());
//        System.out.println(" MAnager E "+event.getChannelState());
//        System.out.println(" MAnager E "+event.getContext());
        // اینجا event شامل تمام اطلاعاتیه که مورد نیاز ماست
        System.out.println("User  " + event.getCallerIdName() + "  with number  " + event.getCallerIdNum() + "  calling and connected to operator  " + event.getConnectedLineNum() + "  with name " + event.getConnectedLineName());
    }



}