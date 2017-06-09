package gerenciamentoMercado.gui;

import javax.swing.*;
import java.awt.*;

public class LoginGUI extends JPanel {
    private JTextField usuario = new JTextField();
    private JPasswordField senha = new JPasswordField();
    private JButton login = new JButton("Login");

    public LoginGUI(MainGUI frame){
        login.setActionCommand("LOGIN");
        login.addActionListener(frame);
        
        usuario.setPreferredSize(new Dimension(300, 30));
        //As 2 linhas abaixo permitem que ao pressionar ENTER ele faça o login
        usuario.setActionCommand("LOGIN");
        usuario.addActionListener(frame);

        senha.setPreferredSize(new Dimension(300, 30));
        //As 2 linhas abaixo permitem que ao pressionar ENTER ele faça o login
        senha.setActionCommand("LOGIN");
        senha.addActionListener(frame);

        JPanel usuarioPanel = new JPanel();
        usuarioPanel.setLayout(new FlowLayout());
        usuarioPanel.add(new JLabel("Usuário: "));
        usuarioPanel.add(usuario);

        JPanel senhaPanel = new JPanel();
        senhaPanel.setLayout(new FlowLayout());
        senhaPanel.add(new JLabel("Senha: "));
        senhaPanel.add(senha);

        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new FlowLayout());
        loginPanel.add(login);

        this.setLayout(new GridLayout(3, 1));
        this.add(usuarioPanel);
        this.add(senhaPanel);
        this.add(loginPanel);
       
    }

    public JTextField getUsuario(){
        return usuario;
    }

    public JPasswordField getSenha(){
        return senha;
    }

}
