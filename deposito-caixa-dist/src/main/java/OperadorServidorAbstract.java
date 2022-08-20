import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

interface OperadorServidorAbstract {
    void realizaOperacao(PrintWriter saida, BufferedReader entrada) throws IOException;
}