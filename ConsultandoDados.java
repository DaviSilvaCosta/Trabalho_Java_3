package ninjastech;


import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ConsultandoDados {
    public static void consultarDados() {
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
            String tabelaConsulta;

            while (true) {
                System.out.print("Digite o nome da tabela que deseja consultar (ou 'sair'): ");
                tabelaConsulta = scanner.nextLine();

                if (tabelaConsulta.equalsIgnoreCase("sair")) {
                    break;
                }

                boolean tabelaEncontrada = false;

                while (tables.next()) {
                    String tableName = tables.getString("TABLE_NAME");

                    if (!tableName.equalsIgnoreCase(tabelaConsulta)) {
                        continue;
                    }

                    tabelaEncontrada = true;

                    String SQLconsultarDados = "SELECT * FROM " + tableName;
                    System.out.println("--------------------------");
                    System.out.println("Tabela " + tableName + ":");

                    Statement st = conn.createStatement();
                    ResultSet result = st.executeQuery(SQLconsultarDados);

                    while (result.next()) {
                        System.out.println("Item: " + result.getString("nome"));
                        System.out.println("Preço: " + result.getDouble("preco"));
                        System.out.println("--------------------------");
                    }

                    result.close();
                    st.close();
                }

                if (!tabelaEncontrada) {
                    System.out.println("Tabela não encontrada.");
                }

                tables.beforeFirst();
            }

            tables.close();
            conn.close();
            System.out.println("Consulta finalizada!");
        } catch (SQLException e) {
            System.err.format("SQL State: %s\n%s", e.getSQLState(), e.getMessage());
        }
    }
}
