package gerenciamentoMercado.bancoDeDados;

import gerenciamentoMercado.pessoa.Cliente;
import gerenciamentoMercado.produto.Produto;
import gerenciamentoMercado.pessoa.Funcionario;

import java.sql.*;
import java.util.Vector;

public class BancoDeDados {
    private Connection conn;

    public BancoDeDados() throws SQLException {
        String driver = "jdbc:derby:./Data/database;create=true"; //Conecta no banco local da maquina
        conn = DriverManager.getConnection(driver);

        PreparedStatement stmt;

        //Se o banco de dados estiver vazio, cria-se as tabelas do programa
        try {
            String sql = "CREATE TABLE Funcionario (nome VARCHAR(50), cpf VARCHAR(11) NOT NULL, rg VARCHAR(15), rua VARCHAR(50), cep VARCHAR(8), bairro VARCHAR(25), cidade VARCHAR(25), estado VARCHAR(25), numero INT, complemento VARCHAR(25), telefone VARCHAR(11), celular VARCHAR(12), salario FLOAT, cargo INT, primary key (cpf) )";

            stmt = conn.prepareStatement(sql); //Se nao existir, cria a tabela Funcionarios
            stmt.execute();
            stmt.close();
        } catch (SQLException se) {
            //Se deu excecao, a tabela ja existe no banco
        }

        try {
            String sql = "CREATE TABLE Cliente (nome VARCHAR(50), cpf VARCHAR(11) NOT NULL, rg VARCHAR(15), rua VARCHAR(50), cep VARCHAR(8), bairro VARCHAR(25), cidade VARCHAR(25), estado VARCHAR(25), numero INT, complemento VARCHAR(25), telefone VARCHAR(11), celular VARCHAR(12), cartao VARCHAR(16), primary key (cpf) )";

            stmt = conn.prepareStatement(sql); //Se nao existir, cria a tabela clientes
            stmt.execute();
            stmt.close();
        } catch (SQLException se) {
            //Se deu excecao, a tabela ja existe no banco
        }

        try{
            String sql = "CREATE TABLE Produto (nome VARCHAR(50), descricao VARCHAR(50), marca VARCHAR(50), codigo INT, valorUnitario FLOAT, quantidade INT, primary key (codigo))";

            stmt = conn.prepareStatement(sql);
            stmt.execute();
            stmt.close();

        }catch (SQLException se){

        }

        //Se o banco de dados estiver vazio, cria-se as tabelas do programa
        try {
            String sql = "CREATE TABLE Conta (cpf VARCHAR(11), usuario VARCHAR(25), senha VARCHAR(25), PRIMARY KEY (usuario))";

            stmt = conn.prepareStatement(sql);//Se nao existir, cria a tabela produtos
            stmt.execute();
            stmt.close();

            sql = "INSERT INTO Conta (usuario, senha) VALUES ('admin', 'admin')";
            stmt = conn.prepareStatement(sql);
            stmt.execute();
            stmt.close();

        } catch (SQLException se) {
            //Se deu excecao, a tabela ja existe no banco
        }
    }

    public Cliente procurarCliente(String cpf) {
        Cliente ret = null;
        String sql = "SELECT * FROM Cliente WHERE cpf=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);

            ResultSet res = stmt.executeQuery();
            if(res.next()) {
                String nome = res.getString(1);
                String rg = res.getString(3);
                String rua = res.getString(4);
                String cep = res.getString(5);
                String bairro = res.getString(6);
                String cidade = res.getString(7);
                String estado = res.getString(8);
                int numero = res.getInt(9);
                String complemento = res.getString(10);
                String telefone = res.getString(11);
                String celular = res.getString(12);
                String cartao = res.getString(13);

                ret = new Cliente(estado, cidade, bairro, rua, numero, complemento, cep, cpf, rg, telefone, celular, cartao, nome);
            }

            stmt.close();



        } catch (SQLException se) {
            se.printStackTrace();
        }
        return ret;
    }

    public void adicionarCliente(Cliente cliente){
        if(cliente != null){
            String sql = "INSERT INTO Cliente (nome, cpf, rg, cep, estado, cidade, bairro, rua, numero, complemento, telefone, celular, cartao) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = null;
            try {
                stmt = conn.prepareStatement(sql);

                stmt.setString(1, cliente.getNome());
                stmt.setString(2, cliente.getCPF());
                stmt.setString(3, cliente.getRG());
                stmt.setString(4, cliente.getEnd().getCEP());
                stmt.setString(5, cliente.getEnd().getEstado());
                stmt.setString(6, cliente.getEnd().getCidade());
                stmt.setString(7, cliente.getEnd().getBairro());
                stmt.setString(8, cliente.getEnd().getRua());
                stmt.setInt   (9, cliente.getEnd().getNumero());
                stmt.setString(10, cliente.getEnd().getComplemento());
                stmt.setString(11, cliente.getTelefone());
                stmt.setString(12, cliente.getCelular());
                stmt.setString(13, cliente.getCartao());

                stmt.execute();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removerCliente(String cpf){
        String sql = "DELETE FROM Cliente WHERE cpf = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,cpf);

            stmt.execute();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Vector<Cliente> mostrarClientes(){
        Vector<Cliente> clientes = new Vector<Cliente>();

        String sql = "SELECT * FROM Cliente";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
            while(res.next()) {
                String nome = res.getString(1);
                String cpf = res.getString(2);
                String rg = res.getString(3);
                String rua = res.getString(4);
                String cep = res.getString(5);
                String bairro = res.getString(6);
                String cidade = res.getString(7);
                String estado = res.getString(8);
                int numero = res.getInt(9);
                String complemento = res.getString(10);
                String telefone = res.getString(11);
                String celular = res.getString(12);
                String cartao = res.getString(13);

                clientes.add(new Cliente(estado, cidade, bairro, rua, numero, complemento, cep, cpf, rg, telefone, celular, cartao, nome));
            }

            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return clientes;
    }

    public boolean logar(String usuario, String senha){
        String sql = "SELECT * FROM Conta WHERE usuario = ?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, usuario);

            ResultSet re = stmt.executeQuery();
            if(re.next() && re.getString("senha").equals(senha)){
                return true;
            }
            return false;

        } catch (SQLException e) {
            return false;
        }

    }

    public void adicionarProduto(Produto produto){
        if(produto != null){
            String sql = "INSERT INTO Produto (nome, descricao, marca, codigo, valorUnitario, quantidade) VALUES (?, ?, ?, ?, ?, ?)";

            try {
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, produto.getNome());
                stmt.setString(2, produto.getDescricao());
                stmt.setString(3, produto.getMarca());
                stmt.setInt(4, produto.getCodigo());
                stmt.setFloat(5, produto.getPreco());
                stmt.setInt(6, produto.getQuantidade());

                stmt.execute();
                stmt.close();

            }catch(SQLException e){
                e.printStackTrace();
            }
        }
    }

    public Produto mostrarProduto(int codigo){
        String sql = "SELECT * FROM Produto WHERE codigo= ?";
        Produto produto = null;
        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, codigo);
            ResultSet res = stmt.executeQuery();

            if(res.next()){
                String nome = res.getString(1);
                String descricao = res.getString(2);
                String marca = res.getString(3);
                float valor = res.getFloat(5);
                int quantidade = res.getInt(6);

                produto = new Produto(quantidade, valor, nome, marca, descricao, codigo);
            }

            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return produto;
    }

    public void venderProduto(int codigo, int quantidade){
        String sql = "UPDATE Produto SET quantidade = ? WHERE codigo = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantidade);
            stmt.setInt(2, codigo);

            stmt.execute();
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void removerProduto (int codigo){
        String sql = "DELETE FROM Produto WHERE codigo = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, codigo);

            stmt.execute();
            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    
    public Vector<Produto> mostrarProdutos(){
        Vector<Produto> produtos = new Vector<Produto>();

        String sql = "SELECT * FROM Produto";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
            while(res.next()) {
                String nome = res.getString(1);
                String descricao = res.getString(2);
                String marca = res.getString(3);
                int codigo = res.getInt(4);
                float valor = res.getFloat(5);
                int quantidade = res.getInt(6);

                produtos.add(new Produto(quantidade, valor, nome, marca, descricao, codigo));
            }

            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return produtos;
    }
    
    public Funcionario procurarFuncionario(String cpf) {
        Funcionario ret = null;
        String sql = "SELECT * FROM Funcionario WHERE cpf=?";

        try {
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);

            ResultSet res = stmt.executeQuery();
            if(res.next()) {
                String nome = res.getString(1);
                String rg = res.getString(3);
                String rua = res.getString(4);
                String cep = res.getString(5);
                String bairro = res.getString(6);
                String cidade = res.getString(7);
                String estado = res.getString(8);
                int numero = res.getInt(9);
                String complemento = res.getString(10);
                String telefone = res.getString(11);
                String celular = res.getString(12);
                double salario = res.getDouble(13);
                int cargo = res.getInt(14);
                

                ret = new Funcionario(estado, cidade, bairro, rua, numero, complemento, cep, cpf, rg, telefone, celular, nome, salario, cargo);
            }

            stmt.close();



        } catch (SQLException se) {
            se.printStackTrace();
        }
        return ret;
    }

    public void adicionarFuncionario(Funcionario funcionario){
        if(funcionario != null){
            String sql = "INSERT INTO Funcionario (nome, cpf, rg, cep, estado, cidade, bairro, rua, numero, complemento, telefone, celular, salario, cargo) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = null;
            try {
                stmt = conn.prepareStatement(sql);

                stmt.setString(1, funcionario.getNome());
                stmt.setString(2, funcionario.getCPF());
                stmt.setString(3, funcionario.getRG());
                stmt.setString(4, funcionario.getEnd().getCEP());
                stmt.setString(5, funcionario.getEnd().getEstado());
                stmt.setString(6, funcionario.getEnd().getCidade());
                stmt.setString(7, funcionario.getEnd().getBairro());
                stmt.setString(8, funcionario.getEnd().getRua());
                stmt.setInt   (9, funcionario.getEnd().getNumero());
                stmt.setString(10, funcionario.getEnd().getComplemento());
                stmt.setString(11, funcionario.getTelefone());
                stmt.setString(12, funcionario.getCelular());
                stmt.setDouble(13, funcionario.getSalario());
                stmt.setInt(14,  funcionario.getCargo());

                stmt.execute();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void removerFuncionario(String cpf){
        String sql = "DELETE FROM Funcionario WHERE cpf = ?";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1,cpf);

            stmt.execute();
            stmt.close();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public Vector<Funcionario> mostrarFuncionarios(){
        Vector<Funcionario> funcionarios = new Vector<Funcionario>();

        String sql = "SELECT * FROM Funcionario";

        try{
            PreparedStatement stmt = conn.prepareStatement(sql);

            ResultSet res = stmt.executeQuery();
            while(res.next()) {
                String nome = res.getString(1);
                String cpf = res.getString(2);
                String rg = res.getString(3);
                String rua = res.getString(4);
                String cep = res.getString(5);
                String bairro = res.getString(6);
                String cidade = res.getString(7);
                String estado = res.getString(8);
                int numero = res.getInt(9);
                String complemento = res.getString(10);
                String telefone = res.getString(11);
                String celular = res.getString(12);
                double salario = res.getDouble(13);
                int cargo = res.getInt(14);

                funcionarios.add(new Funcionario(estado, cidade, bairro, rua, numero, complemento, cep, cpf, rg, telefone, celular, nome, salario, cargo));
            }

            stmt.close();

        }catch (SQLException e){
            e.printStackTrace();
        }

        return funcionarios;
    }
}
