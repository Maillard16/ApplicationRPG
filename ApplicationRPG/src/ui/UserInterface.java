package ui;

public abstract class UserInterface {

    private static UserInterface instance;

    public static UserInterface getInstance() {
        if (instance == null) {
            throw new RuntimeException("No registered singleton");
        }
        return instance;
    }

    public synchronized static void register(String className) throws Exception {
        if (UserInterface.instance != null)
            throw new RuntimeException("Singleton alredy registered");
        Class c = Class.forName(className);
        if (!UserInterface.class.isAssignableFrom(c))
            throw new RuntimeException("Invalid Singleton subclass");
        UserInterface.instance = (UserInterface) c.newInstance();
    }

    public abstract void print(String message);
    public abstract void println(String message);
    public abstract String getLine();

}
