package gerenciamentoMercado.pessoa;

public class Pessoa{
	private Endereco end;
	private String CPF;
	private String RG;
	private String telefone;
	private String celular;
	
	public Pessoa(String estado, String cidade, String bairro, String rua, int numero, String complemento, String CEP, String CPF, String RG, String telefone, String celular) {
		this.end = new Endereco(estado, cidade, bairro, rua, numero, complemento, CEP);
		this.CPF = CPF;
		this.RG = RG;
		this.telefone = telefone;
		this.celular = celular;
	}

	public Endereco getEnd() {
		return end;
	}

	public void setEnd(Endereco end) {
		this.end = end;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}

	public String getRG() {
		return RG;
	}

	public void setRG(String rG) {
		RG = rG;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}
	
	public static boolean verificaCPF(String cpf) {
		if (cpf != null && !cpf.isEmpty() && cpf.length() > 10 && cpf.length() < 16) { 
			String sub[] = cpf.split("\\D"); //tudo menos numero
			String numerocpf = "";
			int digitos[] = new int[11];
			
			for (int i = 0; i < sub.length; i++)
				numerocpf += sub[i];
			if (numerocpf.length() != 11) return false;
			
			for (int i = 0; i < digitos.length; i++)
				digitos[i] = Integer.parseInt(numerocpf.substring(i, i+1));
			
			int soma = 0, verificador1, verificador2;
			
			for (int i = 0; i < 9; i++)
				soma += (10-i) * digitos[i];
			verificador1 = 11 - (soma % 11) < 10 ? 11 - (soma % 11) : 0; //checagem do digito 0
			
			soma = 0;
			for (int i = 0; i < 10; i++)
				soma += (11-i) * digitos[i];
			verificador2 = 11 - (soma % 11) < 10 ? 11 - (soma % 11) : 0; //checagm do digito 0
			
			return digitos[9] == verificador1 && digitos[10] == verificador2 ? true : false; //checagem dos digitos
		}
		return false; //erro
	}
	
}

