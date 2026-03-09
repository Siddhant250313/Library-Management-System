package server;

public class CheckJDK {
    public static void main(String[] args) {
        System.out.println("java.home  = " + System.getProperty("java.home"));
        System.out.println("java.vendor = " + System.getProperty("java.vendor"));
        System.out.println("java.version = " + System.getProperty("java.version"));
    }
}
