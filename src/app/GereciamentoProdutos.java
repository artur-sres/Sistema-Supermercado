package app;

//Bibliotecas utilizadas
import javax.swing.JOptionPane;

public class GereciamentoProdutos {
    private Produto produtos[];
    private int id;
    static public int ID = 1000;
    private String nota = "";
    private double total;

    //Construtor
    public GereciamentoProdutos() {
        this.produtos = new Produto[5];
        this.id = 0;
    }

    public void adicionarProduto(String nome, String categoria, double preco, String validade, int quantidade){
        if(this.id < 5){
            Produto p = new Produto(nome, categoria, preco, validade, quantidade);
            this.produtos[this.id] = p;
            ++this.id;
        } else {
            throw new RuntimeException("O limite de produtos foi atingido.");
        }
    }    

    public String listarProdutos(){
        if(this.id == 0){
            throw new RuntimeException("Nenhum produto cadastrado.");
        }
        int i;
        String mensagem = ""; 
        for(i = 0; i < this.id; ++i){
            mensagem = mensagem + (this.produtos[i].toString() + "\n");
        }
        return mensagem;

    }

    public Produto buscarProduto(int busca){
        int i; 
        if(busca == 0 || busca > ID || this.id == 0){
            return null;
        }
        for(i = 0; i != ID; i++){
            if(busca == this.produtos[i].getId()){
                return this.produtos[i];
            }
        }
        return null;
    }

    public Produto atualizarProduto(Produto produto, String nome, String categoria, Double preco, String validade, int quantidade){
        if(produto == null) {
            JOptionPane.showMessageDialog(null, "Houve um problema :(");
            return null;
        } else {
            produto.setNome(nome);
            produto.setCategoria(categoria);
            produto.setPreco(preco);
            produto.setValidade(validade);
            produto.setQuantidade(quantidade);
            return produto;
        }
    }

public void removerProduto(int busca){
        int indexEncontrado = -1;
        for(int i = 0; i < this.id; i++){
            if(busca == this.produtos[i].getId()){
                indexEncontrado = i;
                break; 
            }
        }
        if (indexEncontrado != -1) {
            for(int i = indexEncontrado; i < (this.id - 1); i++) {
                this.produtos[i] = this.produtos[i + 1];
            }
            this.produtos[this.id - 1] = null;
            this.id--;
        }
    }

    public int adicionarCarrinho(int busca, int quantidade){
        Produto produto = buscarProduto(busca);
        if (produto != null){
            if(produto.getQuantidade() >= quantidade){
                produto.setQuantidade(produto.getQuantidade()-quantidade);
                this.nota += "(" + produto.getId() + ")" + produto.getNome()
                     + "\n" + "Quantidade: " + quantidade + "x " + "\nValor R$" + String.format("%.2f", produto.getPreco()*quantidade) + "\n\n";
                total += produto.getPreco()*quantidade;
                return 1;
            } else {
                return 3;
            }
        } else {
            return 2;
        }
    }


    public String mostrarCarrinho(){
        if(this.nota.equals("")){
            return null;
        }
        return this.nota + "\nTotal: R$" + String.format("%.2f", this.total);
    }
}
