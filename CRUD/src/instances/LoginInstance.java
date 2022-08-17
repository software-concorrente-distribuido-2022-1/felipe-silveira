package instances;

import java.sql.ResultSet;

public class LoginInstance extends AbstractConnection {

    private static final String QUERRY = "SELECT * FROM usuarios WHERE username = ':usuario' AND password = ':senha'";

    public LoginInstance(LogInstance logInstance) {
        super(logInstance);
    }

    public Long  realizaLogin(String usuario, String senha) {
        String processedQuerry = QUERRY.replaceAll(":usuario", usuario).replaceAll(":senha", senha);
        Long id = Long.valueOf(-1);
        synchronized(this) {
            try {
                ResultSet usuarios = statement.executeQuery(processedQuerry);
                if(usuarios.next()) {
                    id = usuarios.getLong("id");
                    logInstance.registraLogin(id);
                }
            } catch (Exception ignored) {}

        }
        return id;
    }
}
