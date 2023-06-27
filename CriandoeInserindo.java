package ninjastech;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class CriandoeInserindo {
    public static void adicionarDados(Scanner scanner) {
        String driver = "jdbc:postgresql://127.0.0.1:5432/DadosJava";
        Statement st = null;

        try (Connection conn = DriverManager.getConnection(driver, "postgres", "27150118")) {
            if (conn != null) {
                System.out.println("Conectado ao banco de dados!\n");
            } else {
                System.out.println("Falha na conexão");
                return;
            }

            while (true) {
                System.out.print("Digite o nome da tabela (ou 'sair'): ");
                String nomeTabela = scanner.nextLine();

                if (nomeTabela.equalsIgnoreCase("sair")) {
                    System.out.println("Tabela Salva com Sucesso!");
                    break;
                }

                System.out.print("Digite o número de Itens que você deseja: ");
                int numeroRegistros = scanner.nextInt();
                scanner.nextLine(); 
                String SQLcriarTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + " (nome VARCHAR(60), preco DECIMAL(10,2))";

                try {
                    System.out.println("Criando tabela, aguarde...");
                    st = conn.createStatement();
                    st.executeUpdate(SQLcriarTabela);
                    System.out.println("Tabela criada com sucesso!");

                    for (int i = 1; i <= numeroRegistros; i++) {
                        System.out.print("Digite o nome do Item " + i + ": ");
                        String nome = scanner.nextLine();

                        System.out.print("Digite o preço " + i + ": ");
                        double preco = scanner.nextDouble();
                        scanner.nextLine(); 

                        String SQLinserirDados = "INSERT INTO " + nomeTabela + " (nome, preco) VALUES ('" + nome + "', " + preco + ")";

                        st.executeUpdate(SQLinserirDados);
                        System.out.println("Registro inserido na tabela " + nomeTabela);
                    }
                } catch (SQLException e) {
                    System.err.format("Erro ao criar a tabela ou inserir dados: %s\n%s", e.getSQLState(), e.getMessage());
                }
            }
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        } finally {
            if (st != null) {
                try {
                    st.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar o Statement: " + e.getMessage());
                }
            }
        }
    }
}
