import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UserData {
    private static String nome;
    private static String sobrenome;
    private static String cpf;
    private static String senha;
    private static double saldoReais = 0.00; // Saldo inicial em Reais
    private static double saldoBitcoin = 0.0; 
    private static double saldoEthereum = 0.0; 
    private static double saldoRipple = 0.0; 

    private static double cotacaoBitcoin = 529557.80;
    private static double cotacaoEthereum = 17896.51;
    private static double cotacaoRipple = 5.00;

    public static String getNome() { return nome; }
    public static void setNome(String nome) { UserData.nome = nome; }
    public static String getSobrenome() { return sobrenome; }
    public static void setSobrenome(String sobrenome) { UserData.sobrenome = sobrenome; }
    public static String getCpf() { return cpf; }
    public static void setCpf(String cpf) { UserData.cpf = cpf; }
    public static String getSenha() { return senha; }
    public static void setSenha(String senha) { UserData.senha = senha; }

    public static double getSaldoReais() { return saldoReais; }
    public static void setSaldoReais(double saldo) { UserData.saldoReais = saldo; }
    public static double getSaldoBitcoin() { return saldoBitcoin; }
    public static void setSaldoBitcoin(double saldo) { UserData.saldoBitcoin = saldo; }
    public static double getSaldoEthereum() { return saldoEthereum; }
    public static void setSaldoEthereum(double saldo) { UserData.saldoEthereum = saldo; }
    public static double getSaldoRipple() { return saldoRipple; }
    public static void setSaldoRipple(double saldo) { UserData.saldoRipple = saldo; }

    public static double getCotacaoBitcoin() { return cotacaoBitcoin; }
    public static void setCotacaoBitcoin(double cotacao) { UserData.cotacaoBitcoin = cotacao; }

    public static double getCotacaoEthereum() { return cotacaoEthereum; }
    public static void setCotacaoEthereum(double cotacao) { UserData.cotacaoEthereum = cotacao; }

    public static double getCotacaoRipple() { return cotacaoRipple; }
    public static void setCotacaoRipple(double cotacao) { UserData.cotacaoRipple = cotacao; }

    public static void atualizarCotacoes() {
        cotacaoBitcoin *= 1 + (Math.random() * 0.1 - 0.05);
        cotacaoEthereum *= 1 + (Math.random() * 0.1 - 0.05);
        cotacaoRipple *= 1 + (Math.random() * 0.1 - 0.05);
    }
}