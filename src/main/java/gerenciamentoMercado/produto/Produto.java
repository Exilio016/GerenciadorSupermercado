package gerenciamentoMercado.produto;

public class Produto {
	public int quantidade;
	public double preco;
	public String nome, marca, descricao, codigo;
	
	public Produto(int quantidade, double preco, String nome, String marca, String descricao, String codigo) {
		this.quantidade = quantidade;
		this.preco = preco;
		this.nome = nome;
		this.marca = marca;
		this.descricao = descricao;
		this.codigo = codigo;
	}
	
	public void vendeProduto(int quantidade) {
		this.quantidade -= quantidade;
	}
	
	public void restocaProduto(int quantidade) {
		this.quantidade += quantidade;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public double getPreco() {
		return preco;
	}

	public String getNome() {
		return nome;
	}

	public String getMarca() {
		return marca;
	}

	public String getDescricao() {
		return descricao;
	}

	public String getCodigo() {
		return codigo;
	}
}
