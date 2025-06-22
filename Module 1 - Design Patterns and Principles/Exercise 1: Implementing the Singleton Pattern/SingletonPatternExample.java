public class SingletonPatternExample {
    
    //Public static method to get the instance of the Logger class
    public static void main(String[] args) {
        
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 refer to the same Logger instance.");
        } else {
            System.out.println("logger1 and logger2 refer to different instances (should not happen).");
        }

        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        System.out.println("logger1 hash code: " + System.identityHashCode(logger1));
        System.out.println("logger2 hash code: " + System.identityHashCode(logger2));
    }
}

// Class named Logger that has a private static instance of itself
class Logger {
    private static final Logger instance = new Logger();

    // Constructor of Logger is private
    private Logger() {
        System.out.println("Logger instance created.");
    }

    public static Logger getInstance() {
        return instance;
    }

    public void log(String message) {
        System.out.println("[LOG] " + message);
    }
}
