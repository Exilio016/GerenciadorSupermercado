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

    public MainGUI (BancoDeDados bd){
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(400,400);
        this.setAlwaysOnTop(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.bd = bd;

        loginGUI = new LoginGUI(this);
        this.setContentPane(loginGUI);

        this.setTitle("Gerenciador de Supermercado");

        JMenu caixa = new JMenu("Caixa");
        caixa.setActionCommand("CAIXA");
        caixa.addActionListener(this);

        JMenu clientes = new JMenu("Clientes");
        caixa.setActionCommand("CLIENTES");
        caixa.addActionListener(this);

        JMenu estoque = new JMenu("Estoque");
        caixa.setActionCommand("ESTOQUE");
        caixa.addActionListener(this);

        JMenu funcionarios = new JMenu("Funcionarios");
        caixa.setActionCommand("FUNCIONARIOS");
        caixa.addActionListener(this);

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
        if(e.getActionCommand().equals("LOGIN")){
        	
            JTextField usuario = ((LoginGUI) this.getContentPane()).getUsuario();
            JPasswordField senha = ((LoginGUI) this.getContentPane()).getSenha();

            if(bd.logar(usuario.getText(), new String(senha.getPassword()))){
                this.caixaGUI = new CaixaGUI(this, screenSize);

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
            Se o programa entrou no 'if' deve-se trocar a tela, para o CaixaGUI
             */
            this.setContentPane(caixaGUI);
        }
    }

    public BancoDeDados getBanco(){
        return bd;
    }

    public void close(){
        this.dispose();
    }
}
