package exercicio1;

import utils.Processador;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CargoProcessador extends Processador {
    @Override
    public Object processaResult(Object result) {
        String resposta = result.toString();
        Pattern patternNome = Pattern.compile("(?<=Nome: )[^;]+");
        Pattern patternCargo = Pattern.compile("(?<=Cargo: )[^;]+");
        Pattern patternSalario = Pattern.compile("(?<=Salario: )[^;]+");
        Matcher matcherNome = patternNome.matcher(resposta);
        Matcher matcherCargo = patternCargo.matcher(resposta);
        Matcher matcherSalario = patternSalario.matcher(resposta);
        matcherNome.find();
        matcherCargo.find();
        matcherSalario.find();
        String cargo = matcherCargo.group(0);
        String nome = matcherNome.group(0);
        BigDecimal salario = new BigDecimal(matcherSalario.group(0));
        if(Objects.equals(cargo, "operador")) {
           salario = salario.multiply(BigDecimal.valueOf(1.2));
        } else {
            salario = salario.multiply(BigDecimal.valueOf(1.18));
        }
        return ("Salario de " + nome + " reajustado para " + salario);
    }
}
