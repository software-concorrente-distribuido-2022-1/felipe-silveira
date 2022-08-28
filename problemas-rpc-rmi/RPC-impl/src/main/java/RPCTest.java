import exercicio1.CargoProcessador;
import exercicio1.CargoServiceImpl;
import exercicio2.MaiorIdadeProcessador;
import exercicio2.MaiorIdadeServiceImpl;
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
        testExercicio2(8089);

    }

    private static void testExercicio1(int porta) {
        System.out.println("Iniciando exercicio 1");
        new Thread(new Runnable() {
            public void run() {
                try {
                    CargoProcessador processador = new CargoProcessador();
                    Server serviceServer = new ServiceCenter(processador, porta);
                    serviceServer.register(InterfaceService.class, CargoServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        InterfaceService service = RPCClient.getRemoteProxyObj(InterfaceService.class, new InetSocketAddress("localhost", porta));
        List<String> parametros1 = new ArrayList<>();
        List<String> parametros2 = new ArrayList<>();
        parametros1.add("Joao");
        parametros1.add("operador");
        parametros1.add("2000");
        parametros2.add("Jose");
        parametros2.add("programador");
        parametros2.add("2000");
        System.out.println(service.methodExecute(parametros1));
        System.out.println(service.methodExecute(parametros2));
        System.out.println("Fim exercicio 1");
    }

    private static void testExercicio2(int porta) {
        System.out.println("Iniciando exercicio 2");
        new Thread(new Runnable() {
            public void run() {
                try {
                    MaiorIdadeProcessador processador = new MaiorIdadeProcessador();
                    Server serviceServer = new ServiceCenter(processador, porta);
                    serviceServer.register(InterfaceService.class, MaiorIdadeServiceImpl.class);
                    serviceServer.start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        InterfaceService service = RPCClient.getRemoteProxyObj(InterfaceService.class, new InetSocketAddress("localhost", porta));
        List<String> parametros1 = new ArrayList<>();
        List<String> parametros2 = new ArrayList<>();
        List<String> parametros3 = new ArrayList<>();
        List<String> parametros4 = new ArrayList<>();
        parametros1.add("Joao");
        parametros1.add("masculino");
        parametros1.add("20");
        parametros2.add("Jose");
        parametros2.add("masculino");
        parametros2.add("16");
        parametros3.add("Maria");
        parametros3.add("feminino");
        parametros3.add("20");
        parametros4.add("Marta");
        parametros4.add("feminino");
        parametros4.add("22");
        System.out.println(service.methodExecute(parametros1));
        System.out.println(service.methodExecute(parametros2));
        System.out.println(service.methodExecute(parametros3));
        System.out.println(service.methodExecute(parametros4));
        System.out.println("Fim exercicio 2");
    }
}