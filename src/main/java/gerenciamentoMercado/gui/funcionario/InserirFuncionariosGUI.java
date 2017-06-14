package gerenciamentoMercado.gui.funcionario;

import gerenciamentoMercado.gui.MainGUI;
import gerenciamentoMercado.gui.abstractGUI.InserirGUI;
import gerenciamentoMercado.pessoa.Funcionario;

import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by nding on 14/06/2017.
 */
public class InserirFuncionariosGUI extends InserirGUI {

    public InserirFuncionariosGUI(MainGUI frame, FuncionariosGUI funcionariosGUI) {
        super(frame, funcionariosGUI);
    }

    protected String[] criarLabels() {
        return new String[] {"Nome: ", "CPF: ", "RG: ", "CEP: ", "Estado: ", "Cidade: ", "Bairro: ", "Rua: ", "Número: ", "Complemento: ", "Telefone: ", "Celular: ", "Salário: ", "Cargo: "};
    }

    public void actionPerformed(ActionEvent e) {
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

        int numero;
        try{
            numero = Integer.parseInt(numeroStr);
        }catch (Exception ex){
            numero = 0;
        }

        float salario;
        try{
            salario = Float.parseFloat(salarioStr);
        }catch (Exception ex){
            salario = 0;
        }

        int cargo = Funcionario.parseCargo(cargoStr);

        Funcionario funcionario = new Funcionario(estado, cidade, bairro, rua, numero, complemento, cep, cpf, rg, telefone, celular, nome, salario, cargo);
        frame.getBanco().adicionarFuncionario(funcionario);

        tableGUI.atualizarTabela();

        this.setVisible(false);
        frame.setContentPane(tableGUI);
        tableGUI.setVisible(true);

    }
}
