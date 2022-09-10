package exercicio1;

import utils.InterfaceService;

import java.util.List;

public class DepositoServiceImpl implements InterfaceService {

    @Override
    public String methodExecute(List<String> parametros) {
        return "Nome: " + parametros.get(0) + "; acao: " + parametros.get(1);
    }
}