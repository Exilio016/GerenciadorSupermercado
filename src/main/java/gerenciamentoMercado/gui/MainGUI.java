package gerenciamentoMercado.gui;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Created by nding on 05/06/2017.
 */
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
            String usuario = ((LoginGUI) this.getContentPane()).getUsuario().getText();
            String senha = new String(((LoginGUI) this.getContentPane()).getSenha().getPassword());

            if(bd.logar(usuario, senha)){
                //TODO trocar para janela de controle do caixa
                this.setSize(screenSize);
                System.out.println("Login realizado com sucesso");
            }

        }
        else if(e.getActionCommand().equals("CADASTRAR")){

        }
    }
}
