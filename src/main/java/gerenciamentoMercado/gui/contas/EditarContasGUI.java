package gerenciamentoMercado.gui.contas;

import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.abstractGUI.InserirGUI;
import gerenciamentoMercado.gui.produto.ProdutosGUI;
import gerenciamentoMercado.pessoa.Conta;
import gerenciamentoMercado.produto.Produto;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by nding on 14/06/2017.
 */
public class EditarContasGUI extends InserirGUI{
    public EditarContasGUI(MainGUI frame, ContasGUI contasGUI, JTable tabela, int linha) {
        super(frame, contasGUI);

        for(int i = 0; i < campos.size(); i++){
            String valor = "" + tabela.getValueAt(linha, i);
            campos.get(i).setText(valor);
        }

        campos.get(0).setEditable(false);
        campos.get(2).setText("");

        inserir.setText("Editar");
    }

    protected String[] criarLabels() {
        return new String[] {"CPF: ", "Usu�rio: ", "Senha: ", "Email: "};
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("INSERIR")) {
            String cpf = campos.get(0).getText();
            String usuario = campos.get(1).getText();
            String senha = campos.get(2).getText();
            String email = campos.get(3).getText();

            if(usuario.isEmpty() || senha.isEmpty()){
                JOptionPane.showMessageDialog(this,"Os campos 'Usu�rio' e 'Senha' n�o podem estar em branco");
                return;
            }

            Conta c = new Conta(cpf, usuario, senha, email);
            frame.getBanco().adicionarConta(c);

            this.setVisible(false);
            frame.setContentPane(tableGUI);
            tableGUI.setVisible(true);

            tableGUI.atualizarTabela();
        }
        else {
            this.setVisible(false);
            frame.setContentPane(tableGUI);
            tableGUI.setVisible(true);
        }

    }
}
