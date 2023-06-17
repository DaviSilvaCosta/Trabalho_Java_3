package ninjastech;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExibirProdutos {
    public static void exibirProdutos() {
        String driver = "jdbc:postgresql://127.0.0.1:5432/DadosJava";
        Connection conn = null;

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

            Scanner scanner = new Scanner(System.in);

            System.out.println("Digite o valor mínimo: ");
            double valorMinimo = scanner.nextDouble();
            System.out.println("Digite o valor máximo: ");
            double valorMaximo = scanner.nextDouble();

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                System.out.println("Tabela: " + tableName);

                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName + " WHERE preco BETWEEN " + valorMinimo + " AND " + valorMaximo);

                while (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    double preco = resultSet.getDouble("preco");
                    System.out.println("Nome: " + nome + ", Preço: " + preco);
                }

                resultSet.close();
                statement.close();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
