package instances;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public abstract class AbstractConnection {
    Connection connection;
    Statement statement;
    LogInstance logInstance;
    public AbstractConnection(LogInstance logInstance) {
        this.logInstance = logInstance;
        createConnection();
    }

    public AbstractConnection() {
        createConnection();
    }

    private void createConnection() {
        String driver = "org.postgresql.Driver";
        String user = "postgres";
        String senha = "Schloegl@20";
        String url = "jdbc:postgresql://localhost:5432/scd-2022-01";
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, senha);
            statement = connection.createStatement();
        } catch (Exception ex) {
            System.err.print(ex.getMessage());
        }
    }
}