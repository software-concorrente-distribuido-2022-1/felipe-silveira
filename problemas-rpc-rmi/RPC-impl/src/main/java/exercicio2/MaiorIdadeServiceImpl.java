package exercicio2;

import utils.InterfaceService;

import java.util.List;

public class MaiorIdadeServiceImpl implements InterfaceService {

    @Override
    public String methodExecute(List<String> parametros) {
        return "Nome: " + parametros.get(0) + "; Sexo: " + parametros.get(1) + "; Idade: " + parametros.get(2);
    }
}