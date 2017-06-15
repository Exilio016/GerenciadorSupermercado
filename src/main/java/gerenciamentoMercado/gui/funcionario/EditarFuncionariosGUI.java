package gerenciamentoMercado.gui.funcionario;

import gerenciamentoMercado.controlador.ControladoraTabela;
import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.abstractGUI.InserirGUI;
import gerenciamentoMercado.pessoa.Funcionario;
import gerenciamentoMercado.pessoa.Pessoa;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Created by nding on 14/06/2017.
 */
public class EditarFuncionariosGUI extends InserirGUI {

    public EditarFuncionariosGUI(MainGUI frame, FuncionariosGUI funcionariosGUI, JTable tabela, int linha) {
        super(frame, funcionariosGUI);

        for(int i = 0; i < campos.size(); i++){
            String valor = "" + tabela.getValueAt(linha, i);
            campos.get(i).setText(valor);
        }

        campos.get(1).setEditable(false);

        inserir.setText("Editar");
    }

    protected String[] criarLabels() {
        return new String[] {"Nome: ", "CPF: ", "RG: ", "CEP: ", "Estado: ", "Cidade: ", "Bairro: ", "Rua: ", "Número: ", "Complemento: ", "Telefone: ", "Celular: ", "Salário: ", "Cargo: "};
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("INSERIR")) {
            String nome = campos.get(0).getText();
            String cpf = campos.get(1).getText();
            String rg = campos.get(2).getText();
            String cep = campos.get(3).getText();
            String estado = campos.get(4).getText();
            String cidade = campos.get(5).getText();
            String bairro = campos.get(6).getText();
            String rua = campos.get(7).getText();
            String numeroStr = campos.get(8).getText();
            String complemento = campos.get(9).getText();
            String telefone = campos.get(10).getText();
            String celular = campos.get(11).getText();
            String salarioStr = campos.get(12).getText();
            String cargoStr = campos.get(13).getText();

            frame.getBanco().removerFuncionario(cpf);

            int numero;
            try {
                numero = Integer.parseInt(numeroStr);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "'Número' deve ser um número inteiro!");
                return;
            }

            float salario;
            try {
                salario = Float.parseFloat(salarioStr);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "'Salário' deve ser um número real!\nAtenção: não use vírgula(,) use ponto(.)");
                return;
            }

            int cargo = Funcionario.parseCargo(cargoStr);

            Funcionario funcionario = new Funcionario(estado, cidade, bairro, rua, numero, complemento, cep, cpf, rg, telefone, celular, nome, salario, cargo);
            frame.getBanco().adicionarFuncionario(funcionario);

            tableGUI.atualizarTabela();

            this.setVisible(false);
            frame.setContentPane(tableGUI);
            tableGUI.setVisible(true);
        }
        else {
            this.setVisible(false);
            frame.setContentPane(tableGUI);
            tableGUI.setVisible(true);
        }

    }
}
