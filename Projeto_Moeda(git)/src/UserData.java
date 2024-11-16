public class UserData {
    private static String cpf;
    private static double saldoReais;
    private static double saldoBitcoin;
    private static double saldoEthereum;
    private static double saldoRipple;

    // Valores das criptomoedas
    private static double cotacaoBitcoin = 529557.80;
    private static double cotacaoEthereum = 17896.51;
    private static double cotacaoRipple = 5.00;

    public static String getCpf() {
        return cpf;
    }

    public static void setCpf(String cpf) {
        UserData.cpf = cpf;
    }

    public static double getSaldoReais() {
        return saldoReais;
    }

    public static void setSaldoReais(double saldoReais) {
        UserData.saldoReais = saldoReais;
    }

    public static double getSaldoBitcoin() {
        return saldoBitcoin;
    }

    public static void setSaldoBitcoin(double saldoBitcoin) {
        UserData.saldoBitcoin = saldoBitcoin;
    }

    public static double getSaldoEthereum() {
        return saldoEthereum;
    }

    public static void setSaldoEthereum(double saldoEthereum) {
        UserData.saldoEthereum = saldoEthereum;
    }

    public static double getSaldoRipple() {
        return saldoRipple;
    }

    public static void setSaldoRipple(double saldoRipple) {
        UserData.saldoRipple = saldoRipple;
    }

    // Cotações das criptomoedas
    public static double getCotacaoBitcoin() {
        return cotacaoBitcoin;
    }

    public static void setCotacaoBitcoin(double cotacaoBitcoin) {
        UserData.cotacaoBitcoin = cotacaoBitcoin;
    }

    public static double getCotacaoEthereum() {
        return cotacaoEthereum;
    }

    public static void setCotacaoEthereum(double cotacaoEthereum) {
        UserData.cotacaoEthereum = cotacaoEthereum;
    }

    public static double getCotacaoRipple() {
        return cotacaoRipple;
    }

    public static void setCotacaoRipple(double cotacaoRipple) {
        UserData.cotacaoRipple = cotacaoRipple;
    }

    // Método para atualizar as cotações de forma aleatória
    public static void atualizarCotacao() {
        cotacaoBitcoin *= (1 + (Math.random() * 0.10 - 0.05));  // variação de -5% a +5%
        cotacaoEthereum *= (1 + (Math.random() * 0.10 - 0.05));
        cotacaoRipple *= (1 + (Math.random() * 0.10 - 0.05));
    }
}
