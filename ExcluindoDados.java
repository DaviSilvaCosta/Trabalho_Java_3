package ninjastech;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExcluindoDados {
    public static void excluirDados(Scanner scanner) {
        String driver = "jdbc:postgresql://127.0.0.1:5432/DadosJava";
        Statement st = null;

        try (Connection conn = DriverManager.getConnection(driver, "postgres", "27150118")) {
            if (conn != null) {
                System.out.println("Conectado ao banco de dados!");
            } else {
                System.out.println("Falha ao conectar ao banco de dados");
            }

           
            while (true) {
                System.out.print("Digite o nome da tabela que deseja excluir (ou 'sair'): ");
                String nomeTabela = scanner.nextLine();

                if (nomeTabela.equalsIgnoreCase("sair")) {
                    break;
                }

                System.out.print("Tem certeza que deseja excluir a tabela '" + nomeTabela + "'? (S/N): ");
                String confirmacao = scanner.nextLine();

                if (confirmacao.equalsIgnoreCase("S")) {
                    String SQLexcluirTabela = "DROP TABLE " + nomeTabela;

                    System.out.println("Excluindo tabela...");
                    st = conn.createStatement();
                    st.executeUpdate(SQLexcluirTabela);
                    System.out.println("Tabela excluída!");
                } else {
                    System.out.println("Operação cancelada.");
                }
            }

            if (st != null) {
                st.close();
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}
