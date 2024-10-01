package projprodutojava;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author osaki
 */
public class ControleProduto {
    ResultSet rs;
    PreparedStatement sql;
    Statement comando;
    Connection conexao;
    
    Produto produto = new Produto();
    
    public void Conexao(){
        try{
            // Variáveis com os dados da conexão ao BD
            String nomeservidor = "localhost:3306";
            String nomebanco = "produtos";
            String nomeusuario = "root";
            String senha= "";
            // especificação do driver a ser utilizado
            String nomedriver = "com.mysql.cj.jdbc.Driver";
            // ativa o driver
            Class.forName(nomedriver);
            // Criando a conexão ao Banco
            String url = "jdbc:mysql://"+nomeservidor+"/"+nomebanco+"?useTimezone=true&serverTimezone=UTC";
            conexao=DriverManager.getConnection(url,nomeusuario,senha);
            comando = conexao.createStatement();
            
        }
        catch (ClassNotFoundException e)
	     {
	       JOptionPane.showMessageDialog(null,"Driver não encontrado!");
             }
        catch (Exception e){
               JOptionPane.showMessageDialog(null," Erro ao conectar o Banco de Dados ");
        }
        
    }
    public void Cadastrar(int id, String nome_prod, String setor, double custo_prod, double preco_prod) throws SQLException{
        produto.setId(id);
        produto.setNome_prod(nome_prod);
        produto.setSetor(setor);
        produto.setCusto_prod(custo_prod);
        produto.setPreco_prod(preco_prod);
        try{
        sql = conexao.prepareStatement("Insert into produtos (id, nome_produto, setor, custo_prod, preco_prod)"+
                "values (?,?,?,?,?)");
        sql.setInt(1, produto.getId());
        sql.setString(2, produto.getNome_prod());
        sql.setString(3,produto.getSetor());
        sql.setDouble(4, produto.getCusto_prod());
        sql.setDouble(5, produto.getPreco_prod());
        int reg = sql.executeUpdate();
        if (reg !=0){
            JOptionPane.showMessageDialog(null,"Dados cadastrados !!!");
        }
        else{
            JOptionPane.showMessageDialog(null,"Erro no SQL !!!");
        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao conectar Banco de Dados !!!");
        }
        
    }
    public void Listar(){
        try{
        // objeto sql recebe a instrução a ser executada
        sql=conexao.prepareStatement("select * from produtos");
        // rs recebe uma lista de retorno pelo executequery
        rs=sql.executeQuery();
        // percorre a lista (enquanto houver o próximo)
        while(rs.next()){
            // rs busca cada registro da lista pelo nome do campo da tabela
            System.out.println("Identificacao: "+rs.getString("id")+ " Nome do Produto: "+rs.getString("nome_produto")+
                    " Setor: "+rs.getString("setor")+ " Custo do Produto: "+rs.getString("custo_prod")+" Preço do Produto: "+rs.getString("preco_prod"));
        }
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao conectar Banco de Dados !!!");
        }
    }
   
    public String Consultar(int id){
        String dados="";
        try{
            sql = conexao.prepareStatement("Select * from produtos where id = ?");
            sql.setInt(1, id);
            rs = sql.executeQuery();
            if (rs.next()){
                dados+=rs.getString("nome_produto");
                dados+=";";
                dados+=rs.getString("setor");
                dados+=";";
                dados+=rs.getString("custo_prod");
                dados+=";";
                dados+=rs.getString("preco_prod");
            }
            else{
                JOptionPane.showMessageDialog(null,"ID não encontrado");
            }
              
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null,"Erro ao conectar Banco de Dados !!!");
        }
          
        return dados;
      
    } 
      
    
}
