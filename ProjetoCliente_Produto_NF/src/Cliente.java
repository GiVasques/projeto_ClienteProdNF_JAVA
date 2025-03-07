import java.util.Random;

public class Cliente {
	private int id;
	private String nome;
	private String endereco;
	
	public Cliente (String nome, String endereco) {
		this.nome = nome;
		this.endereco = endereco;
		id = gerarId();
	}
	
	public int gerarId () {
		Random rd = new Random ();
		return rd.nextInt(1000,2000);
	}

	public int getId() {
		return id;
	}
	
	public String getDados () {
		String dados = "ID: " + id;
		dados += "\n Nome: " + nome;
		dados += "\n Endereço: " + endereco;
		return dados;
	}
}
