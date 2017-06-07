package gerenciamentoMercado.gui;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MainGUI extends JFrame implements ActionListener{
    BancoDeDados bd;
    Dimension screenSize;

    public MainGUI (BancoDeDados bd){
        this.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(400,400);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new LoginGUI(this));
        this.bd = bd;
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
                this.setLocation(0, 0); //Centraliza a GUI
                this.setSize(screenSize); //Aumenta o tamanho para a proxima GUI
                System.out.println("Login realizado com sucesso");
                
                //TODO trocar para janela de controle do caixa
                this.setContentPane(new CaixaGUI(this));
            }
            else {
            	JOptionPane.showMessageDialog(this, "Erro ao realizar o login! \nUsuario ou senha incorretos");
            	senha.setText("");
            }

        }
    }
}
