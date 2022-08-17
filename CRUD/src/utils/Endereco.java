package utils;

public class Endereco {
    private String rua;
    private String bairro;
    private String cidade;
    private Long CEP;

    public Endereco(String rua, String bairro, String cidade, Long CEP) {
        this.rua = rua;
        this.bairro = bairro;
        this.cidade = cidade;
        this.CEP = CEP;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public Long getCEP() {
        return CEP;
    }

    public void setCEP(Long CEP) {
        this.CEP = CEP;
    }
}
