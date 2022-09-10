public class ExecutaConsumidorProdutor {
    public static void main(String[] args) {
        String nomeHost = "192.168.0.105";
        int porta = 8080;
        Cliente cliente = new Cliente(nomeHost, porta);
        Consumidor consumidor = new Consumidor("Consumidor1", cliente);
        Produtor deposito = new Produtor("Produtor1", cliente);
        Thread tc = new Thread(consumidor);
        Thread td = new Thread(deposito);
        tc.start();
        td.start();
    }
}
