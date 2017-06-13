package gerenciamentoMercado.gui;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainGUI extends JFrame implements ActionListener{
    private BancoDeDados bd;
    private Dimension screenSize;
    private JMenuBar menuBar = new JMenuBar();

    private CaixaGUI caixaGUI;
    private LoginGUI loginGUI;
    private ClientesGUI clientesGUI;
    private FuncionariosGUI funcionariosGUI;
    private ProdutosGUI produtosGUI;

    public MainGUI (BancoDeDados bd){
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(400,400);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.bd = bd;

        loginGUI = new LoginGUI(this);
        this.setContentPane(loginGUI);

        this.setTitle("Gerenciador de Supermercado");

        JMenuItem caixa = new JMenuItem("  Caixa");
        caixa.setActionCommand("CAIXA");
        caixa.addActionListener(this);

        JMenuItem clientes = new JMenuItem("| Clientes");
        clientes.setActionCommand("CLIENTES");
        clientes.addActionListener(this);

        JMenuItem estoque = new JMenuItem("| Estoque");
        estoque.setActionCommand("ESTOQUE");
        estoque.addActionListener(this);

        JMenuItem funcionarios = new JMenuItem("| Funcionarios");
        funcionarios.setActionCommand("FUNCIONARIOS");
        funcionarios.addActionListener(this);

        menuBar.add(caixa);
        menuBar.add(clientes);
        menuBar.add(estoque);
        menuBar.add(funcionarios);
    }

    public static void main(String[] args) throws SQLException {
        MainGUI gui = new MainGUI(new BancoDeDados());
        gui.setVisible(true);

    }

    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getActionCommand());
        if(e.getActionCommand().equals("LOGIN")){
        	
            JTextField usuario = ((LoginGUI) this.getContentPane()).getUsuario();
            JPasswordField senha = ((LoginGUI) this.getContentPane()).getSenha();

            if(bd.logar(usuario.getText(), new String(senha.getPassword()))){

                //Inicialização das GUI
                this.caixaGUI = new CaixaGUI(this, screenSize);
                this.clientesGUI = new ClientesGUI(this, screenSize);
                this.funcionariosGUI = new FuncionariosGUI(this, screenSize);
                this.produtosGUI = new ProdutosGUI(this, screenSize);

                this.setLocation(0, 0); //Centraliza a GUI
                this.setSize(screenSize); //Aumenta o tamanho para a proxima GUI
                System.out.println("Login realizado com sucesso");
                
                this.setJMenuBar(menuBar);
                this.setContentPane(caixaGUI);

            }
            else {
            	JOptionPane.showMessageDialog(this, "Erro ao realizar o login! \nUsuario ou senha incorretos");
            	senha.setText("");
            }

        }

        else if(e.getActionCommand().equals("CAIXA")){
            /*
            Se o programa entrou no 'if' deve-se trocar a tela para o CaixaGUI
             */
            this.getContentPane().setVisible(false);
            this.setContentPane(caixaGUI);
            this.getContentPane().setVisible(true);
        }

        else if (e.getActionCommand().equals("CLIENTES")) {
            /*
            Se o programa entrou no 'if' deve-se trocar a tela para o ClientesGUI
             */
            this.getContentPane().setVisible(false);
            this.setContentPane(clientesGUI);
            this.getContentPane().setVisible(true);
        }
        
        else if (e.getActionCommand().equals("FUNCIONARIOS")) {
        	/*
            Se o programa entrou no 'if' deve-se trocar a tela para o FuncionariosGUI
             */
            this.getContentPane().setVisible(false);
            this.setContentPane(funcionariosGUI);
            this.getContentPane().setVisible(true);
        }
        
        else if (e.getActionCommand().equals("ESTOQUE")) {
        	/*
            Se o programa entrou no 'if' deve-se trocar a tela para o FuncionariosGUI
             */
            this.getContentPane().setVisible(false);
            this.setContentPane(produtosGUI);
            this.getContentPane().setVisible(true);
        }

    }

    public BancoDeDados getBanco(){
        return bd;
    }

    public void close(){
        this.dispose();
    }
}
