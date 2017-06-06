package gerenciamentoMercado.pessoa;

public class Cliente extends Pessoa{
    String cartao;
    public static float desconto = 0.1f;

    public Cliente(String estado, String cidade, String bairro, String rua, int numero, String complemento, String CEP, String CPF, String RG, String telefone, String celular, String cartao, String nome) {
        super(estado, cidade, bairro, rua, numero, complemento, CEP, CPF, RG, telefone, celular, nome);

        this.cartao = cartao;
    }

    public String getCartao() {
        return cartao;
    }
    
    public static float getDesconto() {
	return desconto;
    }

    public static void setDesconto(float desconto) {
	Cliente.desconto = desconto;
    }
}
