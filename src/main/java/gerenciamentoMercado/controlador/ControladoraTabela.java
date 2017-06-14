package gerenciamentoMercado.controlador;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.gui.*;
import gerenciamentoMercado.gui.abstractGUI.TableGUI;
import gerenciamentoMercado.gui.cliente.ClientesGUI;
import gerenciamentoMercado.gui.cliente.InserirClientesGUI;
import gerenciamentoMercado.gui.funcionario.FuncionariosGUI;
import gerenciamentoMercado.gui.funcionario.InserirFuncionariosGUI;
import gerenciamentoMercado.gui.produto.InserirProdutoGUI;
import gerenciamentoMercado.gui.produto.ProdutosGUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

/**
 * Created by nding on 12/06/2017.
 */
public class ControladoraTabela implements ActionListener{

    private final MainGUI frame;
    private final TableGUI panel;
    private final BancoDeDados bd;

    private static final int COLUNA_CPF = 1;
    private static final int COLUNA_CODIGO = 2;

    public ControladoraTabela(MainGUI frame, TableGUI panel){
        this.frame = frame;
        this.panel = panel;
        this.bd = frame.getBanco();
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("REMOVER")){
            int linha = panel.getTabela().getSelectedRow();
            if((panel instanceof ClientesGUI || panel instanceof FuncionariosGUI)&& linha >= 0) {
                String cpf = (String) panel.getTabela().getValueAt(linha, COLUNA_CPF);

                if (JOptionPane.showConfirmDialog(panel, "Deseja remover o cliente: " + cpf + "?\n") == JOptionPane.OK_OPTION) {
                    if(panel instanceof ClientesGUI)
                        bd.removerCliente(cpf);
                    else
                        bd.removerFuncionario(cpf);

                    panel.atualizarTabela();
                }
            }
            else if(panel instanceof ProdutosGUI && linha >= 0){
                try{
                    int codigo = (Integer) panel.getTabela().getValueAt(linha, COLUNA_CODIGO);

                    if(JOptionPane.showConfirmDialog(panel, "Deseja remover o produto: " + codigo + "?\n") == JOptionPane.OK_OPTION){
                        bd.removerProduto(codigo);
                        panel.atualizarTabela();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Código inválido!");
                }
            }
        }

        else if(e.getActionCommand().equals("INSERIR")){
            frame.getContentPane().setVisible(false);

            if(panel instanceof ClientesGUI){
                frame.setContentPane(new InserirClientesGUI(frame, (ClientesGUI) panel));
            }
            else if (panel instanceof FuncionariosGUI){
                frame.setContentPane(new InserirFuncionariosGUI(frame, (FuncionariosGUI) panel));
            }
            else if(panel instanceof ProdutosGUI)
                frame.setContentPane(new InserirProdutoGUI(frame, (ProdutosGUI) panel));

            frame.getContentPane().setVisible(true);
        }

        else if(e.getActionCommand().equals("BUSCAR")){
            String campo = panel.getCampoBusca().getText();
            Vector resultado = new Vector();

            if(panel instanceof ProdutosGUI){
                try {
                    int codigo = Integer.parseInt(campo);
                    resultado.add(bd.mostrarProduto(codigo));
                }catch (Exception ex){
                    //
                }
            }
            else if (panel instanceof  ClientesGUI)
                resultado.add(bd.procurarCliente(campo));

            else if (panel instanceof FuncionariosGUI)
                resultado.add(bd.procurarFuncionario(campo));

            if(resultado.isEmpty())
                panel.atualizarTabela();
            else
                panel.atualizarTabela(resultado);
        }
    }
}
