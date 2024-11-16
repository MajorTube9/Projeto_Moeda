import java.sql.*;

public class Database {

    private static final String URL = "jdbc:postgresql://localhost:5432/banco";
    private static final String USER = "postgres";
    private static final String PASSWORD = "123";

    public static void criarUsuario(String nome, String sobrenome, String cpf, String senha) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "INSERT INTO usuarios (nome, sobrenome, cpf, senha) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, nome);
                stmt.setString(2, sobrenome);
                stmt.setString(3, cpf);
                stmt.setString(4, senha);
                stmt.executeUpdate();
            }
        }
    }

    public static boolean autenticarUsuario(String cpf, String senha) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM usuarios WHERE cpf = ? AND senha = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cpf);
                stmt.setString(2, senha);
                try (ResultSet rs = stmt.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }

    public static Usuario obterUsuario(String cpf) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "SELECT * FROM usuarios WHERE cpf = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, cpf);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Usuario(
                                rs.getString("nome"),
                                rs.getString("sobrenome"),
                                rs.getString("cpf"),
                                rs.getString("senha"),
                                rs.getDouble("saldo_reais"),
                                rs.getDouble("saldo_bitcoin"),
                                rs.getDouble("saldo_ethereum"),
                                rs.getDouble("saldo_ripple")
                        );
                    }
                }
            }
        }
        return null;
    }

    public static void atualizarSaldo(String cpf, double saldoReais, double saldoBitcoin, double saldoEthereum, double saldoRipple) throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE usuarios SET saldo_reais = ?, saldo_bitcoin = ?, saldo_ethereum = ?, saldo_ripple = ? WHERE cpf = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDouble(1, saldoReais);
                stmt.setDouble(2, saldoBitcoin);
                stmt.setDouble(3, saldoEthereum);
                stmt.setDouble(4, saldoRipple);
                stmt.setString(5, cpf);
                stmt.executeUpdate();
            }
        }
    }

    public static void atualizarCotacao() throws SQLException {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            String sql = "UPDATE cotações SET bitcoin = ?, ethereum = ?, ripple = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setDouble(1, UserData.getCotacaoBitcoin());
                stmt.setDouble(2, UserData.getCotacaoEthereum());
                stmt.setDouble(3, UserData.getCotacaoRipple());
                stmt.executeUpdate();
            }
        }
    }
}
