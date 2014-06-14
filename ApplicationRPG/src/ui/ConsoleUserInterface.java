package ui;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;


public class ConsoleUserInterface extends UserInterface {
    
    private BufferedReader in;
    private PrintStream out;
    
    protected ConsoleUserInterface() {
        in = new BufferedReader(new InputStreamReader(System.in));
        out = System.out;
    }
    
    @Override
    public void println(String message) {
        out.println(message);
        out.flush();
    }
    
    @Override
    public void print(String message) {
        out.print(message);
        out.flush();
    }
    
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
