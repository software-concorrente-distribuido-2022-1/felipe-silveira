package utils;

public class Pessoa {
    private String nome;
    private Long idade;
    private String CPF;
    private Endereco endereco;
    private Trabalho trabalho;

    public Pessoa(String nome, Long idade, String CPF, Endereco endereco, Trabalho trabalho) {
        this.nome = nome;
        this.idade = idade;
        this.CPF = CPF;
        this.endereco = endereco;
        this.trabalho = trabalho;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Long getIdade() {
        return idade;
    }

    public void setIdade(Long idade) {
        this.idade = idade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public Trabalho getTrabalho() {
        return trabalho;
    }

    public void setTrabalho(Trabalho trabalho) {
        this.trabalho = trabalho;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }
}
