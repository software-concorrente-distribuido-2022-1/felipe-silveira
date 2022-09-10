package exercicio1;

import utils.Processador;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DepositoProcessador extends Processador {
    int quantidadeDeposito = 0;
    int quantidadeMaxima = 10;

    @Override
    public synchronized Object processaResult(Object result) {
        String resposta = result.toString();
        Pattern patternNome = Pattern.compile("(?<=Nome: )[^;]+");
        Pattern patternAcao = Pattern.compile("(?<=acao: )[^;]+");
        Matcher matcherNome = patternNome.matcher(resposta);
        Matcher matcherAcao = patternAcao.matcher(resposta);
        matcherNome.find();
        matcherAcao.find();
        String acao = matcherAcao.group(0);
        String nome = matcherNome.group(0);
        if (Objects.equals(acao, "retirar")) {
            if (quantidadeDeposito > 0) {
                quantidadeDeposito--;
                return ("O " + nome + " retirou uma caixa");
            }
            return ("Deposito vazio não foi possivel retirar");
        } else if (quantidadeDeposito < quantidadeMaxima) {
            quantidadeDeposito++;
            return ("O " + nome + " adicionou uma caixa");
        }
        return ("Deposito cheio não foi possivel adicionar");
    }
}
