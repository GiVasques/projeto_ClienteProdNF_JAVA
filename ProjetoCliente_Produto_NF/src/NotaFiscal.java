import java.util.Random;

public class NotaFiscal {
	private int numNF;
	private String dtEmissao;
	private Cliente cliente;
	private Produto [] produtos;
	private int auxP;
	private double valorTotal;
	
	public NotaFiscal(String dtEmissao, Cliente cliente) {
		this.numNF = gerarNumNF();
		this.dtEmissao = dtEmissao;
		this.cliente = cliente;
		int auxP = 0;
	}
	
	public int gerarNumNF () {
		Random rd = new Random ();
		return rd.nextInt(100,200);
	}
	
	public void calculoValorTotal () {
		for (int i = 0; i < produtos.length; i++) {
			
		}
	}
	
	public void adicionarProduto (Produto produto, int qnt) {
		produtos [auxP] = produto;
		auxP ++;
		produto.atualizarEstoque((produto.getQntEstoque() - qnt));
		valorTotal += (qnt * produto.getPrecoUnit());
	}
	
	public boolean pesquisarProdutosNF (int id) {
		for (int i = 0; i < auxP; i++) {
			if (produtos [i].getId() == id) {
				return true;
			}
		}
		return false;
	}
	
}
