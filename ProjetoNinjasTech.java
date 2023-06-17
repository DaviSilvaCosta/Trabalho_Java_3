package ninjastech;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


class Item {
    private int cod;
    private String nome;
    private double preco;

    public Item(int cod, String nome, double preco) {
        this.cod = cod;
        this.nome = nome;
        this.preco = preco;
    }

    public int getCod() {
        return cod;
    }

    public String getNome() {
        return nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "COD: " + cod + ", Nome: " + nome + ", Preço: " + preco;
    }
}


public class ProjetoNinjasTech {
    private static List<Item> itens = new ArrayList<>();
    private static int proximoCod = 1;

    public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    
    CriandoeInserindo criandoeInserindo = new CriandoeInserindo();
    AtualizandoDados atualizandoDados = new AtualizandoDados();
    ConsultandoDados consultandoDados = new ConsultandoDados();
    ExcluindoDados excluindoDados = new ExcluindoDados();
    ExibirProdutos exibirProdutos = new ExibirProdutos();
    
    
    boolean executando = true;
    while (executando) {
        System.out.println("\nEscolha uma opção:\n");
        System.out.println("Você pode verificar o código da mercadoria em Produtos em Estoque\n");
        System.out.println("1 - Adicionar Mercadoria");
        System.out.println("2 - Atualizar Estoque");
        System.out.println("3 - Produtos em Estoque");
        System.out.println("4 - Excluir Mercadoria");
        System.out.println("5 - Exibir Valores entre X e Y ");
        System.out.println("0 - Sair");

        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1:
                criandoeInserindo.adicionarDados(scanner);
                break;
            case 2:
                atualizandoDados.atualizarDados(scanner);
                break;
            case 3:
                consultandoDados.consultarDados();
                break;
            case 4:
                excluindoDados.excluirDados(scanner);
                break;
            case 5:
                exibirProdutos.exibirProdutos();
                break;
            case 0:
                executando = false;
                break;
            default:
                System.out.println("Opção inválida. Tente novamente.");
                break;
        }
    }

    System.out.println("Encerrando NinjasTech...");
}
}
