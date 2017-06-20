package gerenciamentoMercado.controlador;

import gerenciamentoMercado.bancoDeDados.BancoDeDados;
import gerenciamentoMercado.gui.*;
import gerenciamentoMercado.gui.abstractGUI.TableGUI;
import gerenciamentoMercado.gui.cliente.ClientesGUI;
import gerenciamentoMercado.gui.cliente.EditarClientesGUI;
import gerenciamentoMercado.gui.cliente.InserirClientesGUI;
import gerenciamentoMercado.gui.contas.ContasGUI;
import gerenciamentoMercado.gui.contas.EditarContasGUI;
import gerenciamentoMercado.gui.contas.InserirContasGUI;
import gerenciamentoMercado.gui.funcionario.EditarFuncionariosGUI;
import gerenciamentoMercado.gui.funcionario.FuncionariosGUI;
import gerenciamentoMercado.gui.funcionario.InserirFuncionariosGUI;
import gerenciamentoMercado.gui.produto.EditarProdutoGUI;
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
    private static final int COLUNA_USUARIO = 1;
    private static final int COLUNA_NOME = 0;
    private static final int COLUNA_DESC = 0;
    private static final int COLUNA_MARCA = 1;

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
                String nome = (String) panel.getTabela().getValueAt(linha, COLUNA_NOME);

                if(panel instanceof ClientesGUI){
                	if (JOptionPane.showConfirmDialog(panel, "Deseja remover o cliente?\nNome: " + nome + "\nCPF: " + cpf + "\n") == JOptionPane.OK_OPTION)
                		bd.removerCliente(cpf);
                }else{
                	if (JOptionPane.showConfirmDialog(panel, "Deseja remover o funcionario?\nNome: " + nome + "\nCPF: " + cpf + "\n") == JOptionPane.OK_OPTION)
                        bd.removerFuncionario(cpf);
                }
                    panel.atualizarTabela();
            }
            else if(panel instanceof ProdutosGUI && linha >= 0){
                try{
                    int codigo = (Integer) panel.getTabela().getValueAt(linha, COLUNA_CODIGO);
                    String desc = (String) panel.getTabela().getValueAt(linha, COLUNA_DESC);
                    String marca = (String) panel.getTabela().getValueAt(linha, COLUNA_MARCA);

                    if(JOptionPane.showConfirmDialog(panel, "Deseja remover o produto?\nCodigo: " + codigo + "\nDescrição: " + desc + "Marca: \n" + marca + "\n") == JOptionPane.OK_OPTION){
                        bd.removerProduto(codigo);
                        panel.atualizarTabela();
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Código inválido!");
                }
            }

            else if(panel instanceof  ContasGUI && linha > 0){
                String usuario = (String) panel.getTabela().getValueAt(linha, COLUNA_USUARIO);

                if(JOptionPane.showConfirmDialog(panel, "Deseja remover o usuário: " + usuario) == JOptionPane.OK_OPTION){
                    bd.removerConta(usuario);
                    panel.atualizarTabela();
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

            else if(panel instanceof ContasGUI)
                frame.setContentPane(new InserirContasGUI(frame, (ContasGUI) panel));

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

        else if(e.getActionCommand().equals("EDITAR")){
            int linha = panel.getTabela().getSelectedRow();

            if(linha < 0)
                return;
            frame.getContentPane().setVisible(false);

            if(panel instanceof ClientesGUI)
                frame.setContentPane(new EditarClientesGUI(frame, (ClientesGUI) panel, panel.getTabela(), linha));

           else if(panel instanceof ProdutosGUI)
                frame.setContentPane(new EditarProdutoGUI(frame, (ProdutosGUI) panel, panel.getTabela(), linha));

           else if (panel instanceof FuncionariosGUI)
               frame.setContentPane(new EditarFuncionariosGUI(frame, (FuncionariosGUI) panel, panel.getTabela(), linha));

           else if(panel instanceof  ContasGUI)
               frame.setContentPane(new EditarContasGUI(frame, (ContasGUI) panel, panel.getTabela(), linha));


        }
    }
}
