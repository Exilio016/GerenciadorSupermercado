package gerenciamentoMercado.pessoa;

public class Supervisor extends Funcionario{

	public Supervisor(String estado, String cidade, String bairro, String rua, int numero, String complemento, String CEP,
			String CPF, String RG, String telefone, String celular, String nome, double salario, int cargo) {
		
		super(estado, cidade, bairro, rua, numero, complemento, CEP, CPF, RG, telefone, celular, nome, salario, cargo);
	}
}