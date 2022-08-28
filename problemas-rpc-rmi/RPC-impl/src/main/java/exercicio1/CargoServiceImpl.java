package exercicio1;

import utils.InterfaceService;

import java.util.List;

public class CargoServiceImpl implements InterfaceService {

    @Override
    public String methodExecute(List<String> parametros) {
        return "Nome: " + parametros.get(0) + "; Cargo: " + parametros.get(1) + "; Salario: " + parametros.get(2);
    }
}