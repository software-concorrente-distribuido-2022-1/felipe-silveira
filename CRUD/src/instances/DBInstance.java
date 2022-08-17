package instances;

import org.postgresql.jdbc.PgResultSet;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

public class DBInstance extends AbstractConnection {

    private static final String QUERRY_INSERT = "INSERT INTO :tabela (:colunas) VALUES (:dados_inseridos)";
    private static final String QUERRY_DELETE = "DELETE FROM :tabela WHERE :condicao";
    private static final String QUERRY_SELECT = "SELECT * FROM :tabela WHERE :condicao";
    private static final String QUERRY_UPDATE = "UPDATE :tabela SET :coluna = :valor WHERE :condicao";

    public DBInstance(LogInstance logInstance) {
        super(logInstance);
    }

    public String realizaAcaoBanco(Long idUsuario, String tabela, OperacaoBanco operacao, String colunas, String dados_inseridos, String condicao, String valor) {
        synchronized (this) {
            if (operacao == OperacaoBanco.INSERT) {
                return realizaInsert(idUsuario, tabela, colunas, dados_inseridos);
            } else if (operacao == OperacaoBanco.DELETE) {
                return realizaDelete(idUsuario, tabela, condicao);
            } else if (operacao == OperacaoBanco.SELECT) {
                return realizaSelect(idUsuario, tabela, condicao);
            } else if (operacao == OperacaoBanco.UPDATE) {
                return realizaUpdate(idUsuario, tabela, condicao, colunas, valor);
            }
            return "";
        }
    }

    private String realizaInsert(Long idUsuario, String tabela, String colunas, String dados_inseridos) {
        String processedQuerry = QUERRY_INSERT
                .replaceAll(":tabela", tabela)
                .replaceAll(":colunas", colunas)
                .replaceAll(":dados_inseridos", dados_inseridos);
        int sucesso = -1;
        try {
            sucesso = statement.executeUpdate(processedQuerry);
            if (sucesso != -1) {
                logInstance.registraAcao(idUsuario, "INSERT", tabela, "[" +dados_inseridos.replaceAll("'", " ")+"]", null);
            }
        } catch (Exception ignored) {
        }
        return (sucesso != -1) ? "Sucesso" : "Falha";
    }

    private String realizaDelete(Long idUsuario, String tabela, String condicao) {
        String processedQuerryDelete = QUERRY_DELETE
                .replaceAll(":tabela", tabela)
                .replaceAll(":condicao", condicao);
        String processedQuerrySelect = QUERRY_SELECT
                .replaceAll(":tabela", tabela)
                .replaceAll(":condicao", condicao);
        boolean sucesso = false;
        try {
            ResultSet dados = statement.executeQuery(processedQuerrySelect);
            String dados_anteriores = convertResultSetString(dados);
            sucesso = (statement.executeUpdate(processedQuerryDelete) >= 1);
            if (sucesso) {
                logInstance.registraAcao(idUsuario, "DELETE", tabela, null, dados_anteriores);
            }
        } catch (Exception ignored) {
        }
        return sucesso ? "Sucesso" : "Falha";
    }

    private String realizaSelect(Long idUsuario, String tabela, String condicao) {
        String processedQuerrySelect = QUERRY_SELECT
                .replaceAll(":tabela", tabela)
                .replaceAll(":condicao", condicao);
        String dadosString = "";
        try {
            ResultSet dados = statement.executeQuery(processedQuerrySelect);
            dadosString = convertResultSetString(dados);
            if (!dadosString.equals("")) {
                logInstance.registraAcao(idUsuario, "SELECT", tabela, null, dadosString);
            }
        } catch (Exception ignored) {
        }

        return dadosString;
    }

    private String realizaUpdate(Long idUsuario, String tabela, String condicao, String coluna, String valor) {
        String processedQuerry = QUERRY_UPDATE
                .replaceAll(":tabela", tabela)
                .replaceAll(":condicao", condicao)
                .replaceAll(":coluna", coluna)
                .replaceAll(":valor", valor);
        String processedQuerrySelect = QUERRY_SELECT
                .replaceAll(":tabela", tabela)
                .replaceAll(":condicao", condicao)
                .replaceAll("\\*", coluna);
        boolean sucesso = false;
        try {
            ResultSet dados = statement.executeQuery(processedQuerrySelect);
            String dados_anteriores = convertResultSetString(dados);
            sucesso = (statement.executeUpdate(processedQuerry) >= 1);
            if (sucesso) {
                logInstance.registraAcao(idUsuario, "UPDATE", tabela, coluna + "=" + valor, dados_anteriores);
            }
        } catch (Exception ignored) {
        }
        return sucesso ? "Sucesso" : "Falha";
    }

    private String convertResultSetString(ResultSet dados) {
        try {
            ResultSetMetaData rsmd = dados.getMetaData();
            int numeroColunas = rsmd.getColumnCount();
            String resultadoString = "";
            while (dados.next()) {
                resultadoString = resultadoString + "[";
                for (int i = 1; i <= numeroColunas; i++) {
                    String coluna = rsmd.getColumnName(i);
                    String valor = dados.getString(coluna);
                    resultadoString = resultadoString + coluna + "=" + valor;
                }
                resultadoString = resultadoString + "]";
            }
            return resultadoString;
        } catch (Exception e) {
            return "";
        }
    }
}
