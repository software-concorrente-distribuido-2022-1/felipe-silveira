import exercicio1.DepositoProcessador;
import exercicio1.DepositoServiceImpl;
import utils.InterfaceService;
import utils.RPCClient;
import utils.Server;
import utils.ServiceCenter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.*;

public class RPCTest {

    public static void main(String[] args) {
        testExercicio1(8088);
    }

    private static void testExercicio1(int porta) {
        System.out.println("Iniciando exercicio 1");
        new Thread(new Runnable() {
            public void run() {
                try {
                    DepositoProcessador processador = new DepositoProcessador();
                    Server serviceServer = new ServiceCenter(processador, porta);
                    serviceServer.register(InterfaceService.class, DepositoServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        InterfaceService service = RPCClient.getRemoteProxyObj(InterfaceService.class, new InetSocketAddress("localhost", porta));
        List<String> parametros1 = new ArrayList<>();
        List<String> parametros2 = new ArrayList<>();
        parametros1.add("Consumidor");
        parametros1.add("retirar");
        parametros2.add("Produtor");
        parametros2.add("adicionar");
        System.out.println(service.methodExecute(parametros1));
        for(int i = 0; i < 11; i++) {
            System.out.println(service.methodExecute(parametros2));
        }
        System.out.println("Fim exercicio 1");
    }
}