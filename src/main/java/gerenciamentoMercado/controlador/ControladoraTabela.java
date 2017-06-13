package gerenciamentoMercado.controlador;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.gui.ClientesGUI;
import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.TableGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nding on 12/06/2017.
 */
public class ControladoraTabela implements ActionListener{

    private final MainGUI frame;
    private final TableGUI panel;
    private final BancoDeDados bd;

    private static final int COLUNA_CPF = 1;

    public ControladoraTabela(MainGUI frame, TableGUI panel){
        this.frame = frame;
        this.panel = panel;
        this.bd = frame.getBanco();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("REMOVER")){
            int linha = panel.getTabela().getSelectedRow();
            if(panel instanceof ClientesGUI && linha >= 0) {
                String cpf = (String) panel.getTabela().getValueAt(linha, COLUNA_CPF);

                if (JOptionPane.showConfirmDialog(panel, "Deseja remover o cliente: " + cpf + "?\n") == JOptionPane.OK_OPTION) {
                    bd.removerCliente(cpf);
                    panel.atualizarTabela();
                }
            }
        }

        else if(e.getActionCommand().equals("INSERIR")){

        }
    }
}
