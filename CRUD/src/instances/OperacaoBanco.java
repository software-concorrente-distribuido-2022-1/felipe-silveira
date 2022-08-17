package instances;

public enum OperacaoBanco {
    INSERT(1),
    UPDATE(2),
    SELECT(3),
    DELETE(4);
    public final int valor;
    OperacaoBanco(int valor) {
        this.valor = valor;
    }

    public static OperacaoBanco obtemOperacaoId(int id) {
        if(id == 1) {
            return INSERT;
        } else if(id == 2) {
            return UPDATE;
        } else if(id == 3) {
            return SELECT;
        }else {
            return DELETE;
        }
    }
}
