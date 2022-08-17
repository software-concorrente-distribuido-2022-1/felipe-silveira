import instances.DBInstance;
import instances.LoginInstance;
import instances.OperacaoBanco;
import utils.Endereco;
import utils.Pessoa;
import utils.Trabalho;

import java.util.Objects;
import java.util.Random;

public class ThreadUsuario extends Thread {

    LoginInstance loginInstance;
    DBInstance dbInstance;
    String senha;
    String usuario;
    Long idUsuario;

    public ThreadUsuario(LoginInstance loginInstance, DBInstance dbInstance, String usuario, String senha) {
        this.loginInstance = loginInstance;
        this.dbInstance = dbInstance;
        this.usuario = usuario;
        this.senha = senha;
    }

    @Override
    public void run() {
        idUsuario = loginInstance.realizaLogin(usuario, senha);
        if (!Objects.equals(idUsuario, Long.valueOf(-1))) {
            for (int i = 0; i < 10; i++) {
                realizaOperacao();
            }
        }
    }

    private void realizaOperacao() {
        Object entidade = decideTabelaAcessar();
        decideParametrosOperacaoInsert(entidade);
        decideParametrosOperacaoSelect(entidade);
        decideParametrosOperacaoUpdate(entidade);
        decideParametrosOperacaoDelete(entidade);
    }

    private void decideParametrosOperacaoInsert(Object entidade) {
        if (entidade instanceof Pessoa) {
            //TODO
//            String colunas = "nome, cpf, idade, trabalho_id, endereco_id";
//            String dadosInseridos = "':nome', ':cpf', :idade, :trabalho_id, :endereco_id"
//                    .replaceAll(":nome", ((Pessoa) entidade).getNome().toString())
//                    .replaceAll(":cpf", ((Pessoa) entidade).getCPF())
//                    .replaceAll(":idade", ((Pessoa) entidade).getIdade().toString())
//                    .replaceAll(":trabalho_id", "")
//                    .replaceAll(":endereco_id", "");
//            dbInstance.realizaAcaoBanco(idUsuario, "endereco", OperacaoBanco.INSERT, colunas, dadosInseridos, null, null);
        } else if (entidade instanceof Trabalho) {
            String colunas = "cargo, salario";
            String dadosInseridos = "':cargo', :salario"
                    .replaceAll(":cargo", ((Trabalho) entidade).getCargo())
                    .replaceAll(":salario", ((Trabalho) entidade).getSalario() + "");
            dbInstance.realizaAcaoBanco(idUsuario, "trabalho", OperacaoBanco.INSERT, colunas, dadosInseridos, null, null);
        } else if (entidade instanceof Endereco) {
            String colunas = "cep, cidade, bairro, rua";
            String dadosInseridos = ":cep, ':cidade', ':bairro', ':rua'"
                    .replaceAll(":cep", ((Endereco) entidade).getCEP().toString())
                    .replaceAll(":cidade", ((Endereco) entidade).getCidade())
                    .replaceAll(":bairro", ((Endereco) entidade).getBairro())
                    .replaceAll(":rua", ((Endereco) entidade).getRua());
            dbInstance.realizaAcaoBanco(idUsuario, "endereco", OperacaoBanco.INSERT, colunas, dadosInseridos, null, null);
        }
    }

    private void decideParametrosOperacaoSelect(Object entidade) {
        if (entidade instanceof Pessoa) {
            //TODO
        } else if (entidade instanceof Trabalho) {
            String condicao = "cargo = ':cargo'"
                    .replaceAll(":cargo", ((Trabalho) entidade).getCargo());
            dbInstance.realizaAcaoBanco(idUsuario, "trabalho", OperacaoBanco.SELECT, null, null, condicao, null);
        } else if (entidade instanceof Endereco) {
            String condicao = "cidade = ':cidade'"
                    .replaceAll(":cidade", ((Endereco) entidade).getCidade());
            dbInstance.realizaAcaoBanco(idUsuario, "endereco", OperacaoBanco.SELECT, null, null, condicao, null);
        }
    }

    private void decideParametrosOperacaoDelete(Object entidade) {
        if (entidade instanceof Pessoa) {
            //TODO
        } else if (entidade instanceof Trabalho) {
            String condicao = "cargo = ':cargo'"
                    .replaceAll(":cargo", ((Trabalho) entidade).getCargo());
            dbInstance.realizaAcaoBanco(idUsuario, "trabalho", OperacaoBanco.DELETE, null, null, condicao, null);
        } else if (entidade instanceof Endereco) {
            String condicao = "cidade = ':cidade'"
                    .replaceAll(":cidade", ((Endereco) entidade).getCidade());
            dbInstance.realizaAcaoBanco(idUsuario, "endereco", OperacaoBanco.DELETE, null, null, condicao, null);
        }
    }

    private void decideParametrosOperacaoUpdate(Object entidade) {
        if (entidade instanceof Pessoa) {
            //TODO
        } else if (entidade instanceof Trabalho) {
            String condicao = "cargo = ':cargo'"
                    .replaceAll(":cargo", ((Trabalho) entidade).getCargo());
            dbInstance.realizaAcaoBanco(idUsuario, "trabalho", OperacaoBanco.UPDATE, "cargo", null, condicao, ((Trabalho) entidade).getCargo());
        } else if (entidade instanceof Endereco) {
            String condicao = "cidade = ':cidade'"
                    .replaceAll(":cidade", ((Endereco) entidade).getCidade());
            dbInstance.realizaAcaoBanco(idUsuario, "endereco", OperacaoBanco.UPDATE, "cidade", null, condicao, ((Endereco) entidade).getCidade());
        }
    }

    private Object decideTabelaAcessar() {
        Random rand = new Random();
        int identificadorTabela = rand.nextInt(3);
        Endereco endereco = new Endereco(usuario, usuario, usuario, 74000000L);
        Trabalho trabalho = new Trabalho(usuario, Double.parseDouble(usuario));
        if (identificadorTabela == 0) {
            return endereco;
            //return new Pessoa(usuario, idUsuario, usuario, endereco, trabalho);
        } else if (identificadorTabela == 1) {
            return endereco;
        } else {
            return trabalho;
        }
    }
}
