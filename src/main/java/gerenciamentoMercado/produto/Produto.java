package gerenciamentoMercado.produto;

public class Produto {
	public int quantidade, codigo;
	public float preco;
	public String nome, marca, descricao;
	
	public Produto(int quantidade, float preco, String nome, String marca, String descricao, int codigo) {
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

	public float getPreco() {
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

	public int getCodigo() {
		return codigo;
	}
}
