import instances.DBInstance;
import instances.LogInstance;
import instances.LoginInstance;

public class Application {
    public static void main(String[] args) {
        LogInstance logInstance = new LogInstance();
        LoginInstance loginInstance = new LoginInstance(logInstance);
        DBInstance dbInstance = new DBInstance(logInstance);
        for(int thread = 1; thread < 100; thread++) {
            ThreadUsuario threadUsuario = new ThreadUsuario(loginInstance, dbInstance, Integer.toString(thread), Integer.toString(thread));
            threadUsuario.start();
        }
    }
}
