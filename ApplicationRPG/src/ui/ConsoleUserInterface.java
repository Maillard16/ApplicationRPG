package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class ConsoleUserInterface implements UserInterface {
    
    private BufferedReader in;
    private PrintStream out;
    
    private ConsoleUserInterface() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = System.out;
    }
    
    private static class Instance { 
        private static final ConsoleUserInterface instance = new ConsoleUserInterface();
    }
    
    public static UserInterface getInstance() {
        return Instance.instance;
    }
    
    @Override
    public void print(String message) {
        out.println(message);
        out.flush();
    }
    
//    @Override
//    public void clear()
//    {
//        try
//        {
//            final String os = System.getProperty("os.name");
//
//            if (os.contains("Windows"))
//            {
//                Runtime.getRuntime().exec("cls");
//            }
//            else
//            {
//                Runtime.getRuntime().exec("clear");
//            }
//        }
//        catch (final Exception e)
//        {
//            //  Handle any exceptions.
//        }
//    }
    
    @Override
    public String getLine() {
        String message = "Console Input Error";
        try {
            message = in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return message;
    }    
}
