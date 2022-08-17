package instances;

import java.sql.ResultSet;
import java.util.Date;

public class LogInstance extends AbstractConnection {

    private static final String QUERRY_LOGIN = "INSERT INTO logacesso (user_id, data) VALUES (:id, ':data')";
    private static final String QUERRY_ACAO = "INSERT INTO logoperacao (user_id, data, operacao, tabela, dados_inserido, dados_anteriores) VALUES (:id, ':data', ':operacao', ':tabela', :dados_inserido, :dados_anteriores)";
    public LogInstance() {
        super();
    }

    public void registraLogin(Long id) {
        String processedQuerry = QUERRY_LOGIN.replaceAll(":id", id.toString()).replaceAll(":data", new Date().toString());
        synchronized(this) {
            try {
                statement.executeUpdate(processedQuerry);
            } catch (Exception ignored) {}
        }
    }

    public void registraAcao(Long id, String operacao, String tabela, String dados_inserido, String dados_anteriores) {
        if(dados_inserido == null) {
            dados_inserido = "NULL";
        } else {
            dados_inserido = "'" + dados_inserido + "'";
        }
        if(dados_anteriores == null) {
            dados_anteriores = "NULL";
        } else {
            dados_anteriores = "'" + dados_anteriores + "'";
        }
        String processedQuerry = QUERRY_ACAO
                .replaceAll(":id", id.toString())
                .replaceAll(":data", new Date().toString())
                .replaceAll(":operacao", operacao)
                .replaceAll(":tabela", tabela)
                .replaceAll(":dados_inserido", dados_inserido)
                .replaceAll(":dados_anteriores", dados_anteriores);
        synchronized(this) {
            try {
                statement.executeUpdate(processedQuerry);
            } catch (Exception ignored) {}
        }
    }
}
