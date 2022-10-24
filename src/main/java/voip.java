import org.asteriskjava.manager.AuthenticationFailedException;
import org.asteriskjava.manager.ManagerConnection;
import org.asteriskjava.manager.ManagerConnectionFactory;
import org.asteriskjava.manager.TimeoutException;
import org.asteriskjava.manager.action.CommandAction;
import org.asteriskjava.manager.action.OriginateAction;
import org.asteriskjava.manager.event.ManagerEvent;
import org.asteriskjava.manager.response.CommandResponse;
import org.asteriskjava.manager.response.ManagerResponse;
// the source of the page : http://www.scytheofwise.com/asterisk-java-ami/
import java.io.IOException;

/**
 * @author Aref Azizi
 * @date 10/24/22
 */
public class voip
{
    private ManagerConnection managerConnection;

    public voip() throws IOException
    {
        // this information most be unique
        ManagerConnectionFactory factory = new ManagerConnectionFactory("localHost", "root", "F@np2ss23");
        this.managerConnection = factory.createManagerConnection();
    }

    @SuppressWarnings("deprecation")
    public void run() throws IOException, AuthenticationFailedException, TimeoutException
    {
        OriginateAction originateAction;
        ManagerResponse originateResponse;
        ManagerEvent originateEvent;

        originateAction = new OriginateAction();
        originateAction.setChannel("SIP/1001");
        originateAction.setContext("default");
        originateAction.setExten("1000");
        originateAction.setPriority(new Integer(1));
        originateAction.setTimeout(new Integer(10000));
        System.out.println(originateAction.getCallerId());
        System.out.println(originateAction.getCallingPres());

        // connect to Asterisk and log in
        managerConnection.login();

        // send the action we defined and wait 10 seconds for a reply
        originateResponse = managerConnection.sendAction(originateAction, 10000);

        // print the response
        System.out.println("Response : "+originateResponse.getResponse());
        CommandAction commandAction = new CommandAction("asterisk -rvvvvv");
        CommandResponse response = (CommandResponse) managerConnection.sendAction(commandAction);
        for (String line : response.getResult())
        {
            System.out.println( "response of -rvvvvv =============>>>>>" +line);
        }
        // disconnect and logoff from asterisk
        managerConnection.logoff();
    }

    public static void main(String[] args) throws Exception
    {
        voip helloworld;
        helloworld = new voip();
        helloworld.run();
    }
}