package gerenciamentoMercado.bancoDeDados;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BancoDeDados {
    private Connection conn;

    public BancoDeDados() throws SQLException{
        String driver = "jdbc:derby:./Data/database;create=true"; //Conecta no banco local da maquina
        conn = DriverManager.getConnection(driver);

        PreparedStatement stmt;

        //Se o banco de dados estiver vazio, cria-se as tabelas do programa
        try{
            String sql = "CREATE TABLE Funcionario (nome VARCHAR(50), cpf VARCHAR(11) NOT NULL, rg VARCHAR(15), rua VARCHAR(50), cep INT(8), bairro VARCHAR(25), cidade VARCHAR(25), estado VARCHAR(25), numero INT, complemento VARCHAR(25), telefone INT(11), celular INT(12), salario FLOAT, cargo INT, primary key (cpf) )";

            stmt = conn.prepareStatement(sql); //Se nao existir, cria a tabela Funcionarios
            stmt.execute();
            stmt.close();
        } catch(SQLException se){
            //Se deu excecao, a tabela ja existe no banco
        }

        try{
            String sql = "CREATE TABLE Cliente (nome VARCHAR(32), cpf VARCHAR(11) NOT NULL, rg VARCHAR(15), rua VARCHAR(50), cep INT(8), bairro VARCHAR(25), cidade VARCHAR(25), estado VARCHAR(25), numero INT, complemento VARCHAR(25), telefone INT(11), celular INT(12), cartao INT(16), primary key (cpf) )";

            stmt = conn.prepareStatement(sql); //Se nao existir, cria a tabela clientes
            stmt.execute();
            stmt.close();
        } catch(SQLException se){
            //Se deu excecao, a tabela ja existe no banco
        }


        //Se o banco de dados estiver vazio, cria-se as tabelas do programa
        try{
            String sql = "CREATE TABLE Produto (codigo BIGINT NOT NULL, descricao VARCHAR(255), valorunit FLOAT, quantidade INT, primary key (codigo) )";

            stmt = conn.prepareStatement(sql);//Se nao existir, cria a tabela produtos
            stmt.execute();
            stmt.close();
        } catch(SQLException se){
            //Se deu excecao, a tabela ja existe no banco
        }
    }
}
