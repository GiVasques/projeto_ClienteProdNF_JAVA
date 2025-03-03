import java.util.Random;

public class Produto {
	private int id;
	private String descricao;
	private double precoUnit;
	private int qntEstoque;
	
	public Produto (String descricao, double precoUnit, int qntEstoque) {
		this.descricao = descricao;
		this.precoUnit = precoUnit;
		this.qntEstoque = qntEstoque;
		this.id = gerarId();
	}
	
	public int gerarId () {
		Random rd = new Random ();
		return rd.nextInt(100,200);
	}
	
	public String getDados () {
		String dados = "ID: " + id;
		dados += "\n Descrição: " + descricao;
		dados += "\n Preço Unitário: " + precoUnit;
		dados += "\n Quantidade em estoque: " + qntEstoque;
		return dados;
	}
	
	public int getId() {
		return id;
	}

	public int getQntEstoque() {
		return qntEstoque;
	}

	public double getPrecoUnit() {
		return precoUnit;
	}

	public void atualizarEstoque (int quantidade) {
		this.qntEstoque = quantidade;
	}
}
