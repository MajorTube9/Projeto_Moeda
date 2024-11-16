public class Usuario {
    private String nome;
    private String sobrenome;
    private String cpf;
    private String senha;
    private double saldoReais;
    private double saldoBitcoin;
    private double saldoEthereum;
    private double saldoRipple;

    public Usuario(String nome, String sobrenome, String cpf, String senha, double saldoReais, double saldoBitcoin, double saldoEthereum, double saldoRipple) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
        this.senha = senha;
        this.saldoReais = saldoReais;
        this.saldoBitcoin = saldoBitcoin;
        this.saldoEthereum = saldoEthereum;
        this.saldoRipple = saldoRipple;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getSenha() {
        return senha;
    }

    public double getSaldoReais() {
        return saldoReais;
    }

    public double getSaldoBitcoin() {
        return saldoBitcoin;
    }

    public double getSaldoEthereum() {
        return saldoEthereum;
    }

    public double getSaldoRipple() {
        return saldoRipple;
    }
}
