package gerenciamentoMercado.pessoa;

public class Funcionario extends Pessoa{
	private double salario;
	private int cargo;
	/**
	 * Cargos:
	 * 1 - gerente
	 * 2 - supervisor
	 * 3 - caixa
	 * 4 - funcionários em geral
	 */
	
	public Funcionario(String estado, String cidade, String bairro, String rua, int numero, String complemento,
			String CEP, String CPF, String RG, String telefone, String celular, String nome, double salario,
			int cargo) {
		super(estado, cidade, bairro, rua, numero, complemento, CEP, CPF, RG, telefone, celular, nome);
		this.salario = salario;
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public int getCargo() {
		return cargo;
	}

	public void setCargo(int cargo) {
		this.cargo = cargo;
	}

    public static int parseCargo(String cargo) {
		if(cargo.toLowerCase().equals("gerente"))
			return 2;
		else if(cargo.toLowerCase().equals("supervisor"))
			return 1;
		else
			return 0;
    }

    public static String cargoToString (int cargo){
		if(cargo == 1)
			return "Supervisor";
		if(cargo == 2)
			return "Gerente";
		return "Outro";
	}
}