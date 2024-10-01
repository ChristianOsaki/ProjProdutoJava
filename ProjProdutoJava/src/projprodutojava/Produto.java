package projprodutojava;
/**
 *
 * @author osaki
 */
public class Produto {
    private int id;
    private String nome_prod;
    private String setor;
    private double custo_prod;
    private double preco_prod;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome_prod() {
        return nome_prod;
    }

    public void setNome_prod(String nome_prod) {
        this.nome_prod = nome_prod;
    }

    public String getSetor() {
        return setor;
    }

    public void setSetor(String setor) {
        this.setor = setor;
    }

    public double getCusto_prod() {
        return custo_prod;
    }

    public void setCusto_prod(double custo_prod) {
        this.custo_prod = custo_prod;
    }

    public double getPreco_prod() {
        return preco_prod;
    }

    public void setPreco_prod(double preco_prod) {
        this.preco_prod = preco_prod;
    }
    
}
