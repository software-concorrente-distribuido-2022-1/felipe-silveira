package exercicio2;

import utils.Processador;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MaiorIdadeProcessador extends Processador {
    @Override
    public Object processaResult(Object result) {
        String resposta = result.toString();
        Pattern patternNome = Pattern.compile("(?<=Nome: )[^;]+");
        Pattern patternSexo = Pattern.compile("(?<=Sexo: )[^;]+");
        Pattern patternIdade = Pattern.compile("(?<=Idade: )[^;]+");
        Matcher matcherNome = patternNome.matcher(resposta);
        Matcher matcherSexo = patternSexo.matcher(resposta);
        Matcher matcherIdade = patternIdade.matcher(resposta);
        matcherNome.find();
        matcherSexo.find();
        matcherIdade.find();
        String sexo = matcherSexo.group(0);
        String nome = matcherNome.group(0);
        BigDecimal idade = new BigDecimal(matcherIdade.group(0));
        if(Objects.equals(sexo, "masculino")) {
           if(idade.compareTo(new BigDecimal(18)) >= 0) {
                return "O " + nome+ " é maior de idade";
           }
            return "O " + nome+ " é menor de idade";
        } else {
            if(idade.compareTo(new BigDecimal(21)) >= 0) {
                return "A " + nome+ " é maior de idade";
            }
            return "A " + nome+ " é menor de idade";
        }
    }
}
