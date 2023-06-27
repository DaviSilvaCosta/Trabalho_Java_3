package ninjastech;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class AtualizandoDados {
    public static void atualizarDados(Scanner scanner) {
        String driver = "jdbc:postgresql://127.0.0.1:5432/DadosJava";
        Connection conn = null;
        Statement st = null;

        try {
            conn = DriverManager.getConnection(driver, "postgres", "27150118");
            if (conn != null) {
                System.out.println("Conectado ao banco de dados!");
            } else {
                System.out.println("Falha na conexão");
                return;
            }

            DatabaseMetaData metaData = conn.getMetaData();
            ResultSet tables = metaData.getTables(null, null, null, new String[]{"TABLE"});

            System.out.println("Tabelas existentes:");
            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println(tableName);
            }

            while (true) {
                System.out.print("Digite o nome da tabela que deseja atualizar (ou 'sair'): ");
                String nomeTabela = scanner.nextLine();

                if (nomeTabela.equalsIgnoreCase("sair")) {
                    break;
                }

                System.out.print("Digite o nome do produto que deseja atualizar: ");
                String nomeProduto = scanner.nextLine();

                System.out.print("Digite o novo nome: ");
                String novoNome = scanner.nextLine();

                System.out.print("Digite o novo preço: ");
                double novoPreco = scanner.nextDouble();
                scanner.nextLine(); 

                String SQLatualizarDados = "UPDATE " + nomeTabela + " SET nome = '" + novoNome + "', preco = " + novoPreco +
                        " WHERE nome = '" + nomeProduto + "'";

                System.out.println("Atualizando dados na tabela " + nomeTabela + "...");
                st = conn.createStatement();
                int linhasAfetadas = st.executeUpdate(SQLatualizarDados);
                System.out.println("Dados atualizados. " );
            }

            System.out.println("Tabela Salva com Sucesso!");
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
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Erro ao fechar a conexão: " + e.getMessage());
                }
            }
        }
    }
}
