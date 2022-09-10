import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

interface OperadorServidorAbstract {
    void realizaOperacao(PrintWriter saida, BufferedReader entrada, Socket socketCliente) throws IOException;
}