package app;

//Bibliotecas utilizadas
import javax.swing.JOptionPane;
import javax.swing.UIManager;

public class Supermecado {
    public static void main(String[] args) {
        int opcao = 1;
    GereciamentoProdutos gerenciamento = new GereciamentoProdutos();
    
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) { 
            System.err.println("Não foi possível carregar o Look and Feel Nimbus."); 
        }

        //Produtos para utilizar de exemplos
        gerenciamento.adicionarProduto("Arroz", "Alimentos", 4.99, "2022-06-30", 50);
        gerenciamento.adicionarProduto("Feijão", "Alimentos", 4.99, "2022-06-30", 50);
        gerenciamento.adicionarProduto("Macarrao", "Alimentos", 4.99, "2022-06-30", 50);
        gerenciamento.adicionarProduto("Leite", "Alimentos", 4.99, "2022-06-30", 50);
        gerenciamento.adicionarProduto("Cafe", "Alimentos", 4.99, "2022-06-30", 50);
        
        String menu = "SELECIONE UMA OPÇÂO PARA PROSSEGUIR:" 
                    + "\n01 - Adicionar produto." 
                    + "\n02 - Buscar produto." 
                    + "\n03 - Listar produtos." 
                    + "\n04 - Modificar produto." 
                    + "\n05 - Remover produto."
                    + "\n06 - Adicionar ao carrinho."
                    + "\n07 - Mostrar carrinho.";
                    
        //Variaveis de produto
        String nome, categoria, validade;
        double preco;
        int quantidade;
    
        //Variaveis auxiliares
        Produto produto;
        String mensagem;
        int busca = 0;
        boolean flag;
        int status;

        while (opcao != 0){
            try {
                opcao = Integer.parseInt(JOptionPane.showInputDialog(null, menu, "MENU", JOptionPane.DEFAULT_OPTION));
            } catch (NumberFormatException e) {
                opcao = 0;
            }

            switch (opcao) {
                case 01: //Adicionar produto
                    nome = JOptionPane.showInputDialog(null, "Digite o nome do produto:", "ADICIONAR NOVO PRODUTO", JOptionPane.QUESTION_MESSAGE);
                    categoria = JOptionPane.showInputDialog(null, "Digite a categoria do produto", "ADICIONAR NOVO PRODUTO", JOptionPane.QUESTION_MESSAGE);
                    preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Quanto custa a unidade do produto?", "ADICIONAR NOVO PRODUTO", JOptionPane.QUESTION_MESSAGE));
                    validade = JOptionPane.showInputDialog(null, "Qual a validade do produto?", "ADICIONAR NOVO PRODUTO", JOptionPane.QUESTION_MESSAGE);
                    quantidade = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantos produtos há em estoque?", "ADICIONAR NOVO PRODUTO", JOptionPane.QUESTION_MESSAGE));

                    try {
                        gerenciamento.adicionarProduto(nome, categoria, preco, validade, quantidade);
                    } catch (RuntimeException e) {
                        mensagem = "<html>Houve um problema ao adicionar seu produto: <font color='red'>" 
                        + e.getMessage() 
                        + "</font></html>";
                        JOptionPane.showMessageDialog(null, mensagem, "ADICIONAR NOVO PRODUTO", JOptionPane.ERROR_MESSAGE);
                        break;
                    }
                    JOptionPane.showMessageDialog(null, "Produto adicionado com sucesso!", "ADICIONAR NOVO PRODUTO", JOptionPane.INFORMATION_MESSAGE);
                    break;

                case 02: //Buscar produto
                    flag = true;
                    while (flag) { 
                        try {
                            busca = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do produto:", "BUSCAR PRODUTO", JOptionPane.QUESTION_MESSAGE));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "ID inválido", "BUSCAR PRODUTO", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }
                        flag = false;
                    }
                    produto = gerenciamento.buscarProduto(busca);
                    if(produto != null){
                        mensagem = produto.toString();
                        JOptionPane.showMessageDialog(null, mensagem);
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado", "BUSCAR PRODUTO", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;

                case 03: //Listar produtos
                try {
                    JOptionPane.showMessageDialog(null, gerenciamento.listarProdutos(), "LISTAR PRODUTOS", JOptionPane.INFORMATION_MESSAGE);
                } catch (RuntimeException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "LISTAR PRODUTOS", JOptionPane.ERROR_MESSAGE);
                    break;
                }
                    break;
                
                case 04: //Modificar produto
                    flag = true;
                    while (flag) { 
                        try {
                            busca = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o ID do produto:", "MODIFICAR PRODUTO", JOptionPane.QUESTION_MESSAGE));
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "ID inválido", "MODIFICAR PRODUTO", JOptionPane.ERROR_MESSAGE);
                            continue;
                        }
                        flag = false;
                    }
                    produto = gerenciamento.buscarProduto(busca);
                    if(produto != null){
                        mensagem = produto.toString();
                        JOptionPane.showMessageDialog(null, mensagem);
                    } else {
                        JOptionPane.showMessageDialog(null, "Produto não encontrado", "BUSCAR PRODUTO", JOptionPane.INFORMATION_MESSAGE);
                    }

                    produto = gerenciamento.buscarProduto(busca);
                    if(produto == null){
                        break;
                    }
                        nome = JOptionPane.showInputDialog(null, "Digite o nome do produto", "MODIFICAR PRODUTO", JOptionPane.QUESTION_MESSAGE);
                        categoria = JOptionPane.showInputDialog(null, "Digite a categoria do produto", "MODIFICAR PRODUTO", JOptionPane.QUESTION_MESSAGE);
                        preco = Double.parseDouble(JOptionPane.showInputDialog(null, "Quanto custa a unidade do produto?", "MODIFICAR PRODUTO", JOptionPane.QUESTION_MESSAGE));
                        validade = JOptionPane.showInputDialog(null, "Qual a validade do produto?", "MODIFICAR PRODUTO", JOptionPane.QUESTION_MESSAGE);
                        quantidade = Integer.parseInt(JOptionPane.showInputDialog(null, "Quantos produtos há em estoque?", "MODIFICAR PRODUTO", JOptionPane.QUESTION_MESSAGE));

                        produto = gerenciamento.atualizarProduto(produto, nome, categoria, preco, validade, quantidade);
                        mensagem = "Produto modificado com sucesso\n\n" + produto.toString();
                        JOptionPane.showMessageDialog(null, mensagem, "MODIFICAR PRODUTO", JOptionPane.INFORMATION_MESSAGE);
                    break;

                    case 05:
                        busca = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o código do produto que deseja remover:"));

                        produto = gerenciamento.buscarProduto(busca);
                        if(produto != null){
                            gerenciamento.removerProduto(produto.getId());

                        } else {
                            JOptionPane.showMessageDialog(null,"Produto não encontrado");
                            break;
                        }
                    case 06:
                        busca = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite o código do produto que deseja adicionar ao carrinho:"));
                        quantidade = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite quantos produtos deseja adicionar ao carrinho:"));

                        produto = gerenciamento.buscarProduto(busca);
                        status = gerenciamento.adicionarCarrinho(busca, quantidade);

                        if(status == 1){
                            JOptionPane.showMessageDialog(null, "Produto adicionado ao carrinho com sucesso");
                        } else {
                            if(status == 2){
                                JOptionPane.showMessageDialog(null, "Produto não encontrado");
                            } else {
                                JOptionPane.showMessageDialog(null, "A quantidade é superior ao valor no estoque");
                            }
                        }
                    break;
                    case 07:
                        mensagem = gerenciamento.mostrarCarrinho();
                        if(mensagem == null){
                            JOptionPane.showMessageDialog(null, "O carrinho está vazio!");
                            break;
                        }
                        JOptionPane.showMessageDialog(null, mensagem);
                        break;

                default:
                    break;
            }
        }
    }
}
