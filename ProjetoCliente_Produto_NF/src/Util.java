import static javax.swing.JOptionPane.*;
import static java.lang.Integer.parseInt;
import static java.lang.Double.parseDouble;

public class Util {
	
	private final int N = 10;
	private Cliente [] clientes = new Cliente [N];
	private int auxC;
	private Produto [] produtos = new Produto [N];
	private int auxP;
	private NotaFiscal [] nfs = new NotaFiscal [N];
	private int auxNf;

	public void menu () {
		String texto = "Bem vindo! Qual área deseja acessar? \n";
		texto += "1. Cliente \n";
		texto += "2. Produto \n";
		texto += "3. NFs \n";
		texto += "4. Encerrar programa \n";
		
		int opcao;
		do {
			opcao = parseInt(showInputDialog(texto));
			
			switch (opcao) {
				case 1:
					menuCliente();
					break;
				case 2:
					menuProduto();
					break;
				case 3:
					menuNF ();
			}
		} while (opcao != 4);
	}
	
	public void menuCliente () {
		String texto = "Bem vindo à Área de Gerenciamento de Clientes! \n";
		texto += "1. Cadastrar Novo Cliente \n";
		texto += "2. Pesquisar Clientes \n";
		texto += "3. Listagem Clientes \n";
		texto += "4. Retornar para menu inicial \n";
		
		int opcao;
		do {
			opcao = parseInt(showInputDialog(texto));
		
			switch (opcao) {
				case 1:
					if (auxC < N) {
						clientes [auxC] = adicionarCliente();
						auxC++;
					}
					else {
						showMessageDialog(null, "Número máximo de clientes cadastrados atingido!");
					}
					break;
				case 2:
					int id = parseInt(showInputDialog("Qual o ID do cliente que se deseja pesquisar?"));
					Cliente aux = pesquisarCliente(id);
					if (aux == null) {
						showMessageDialog(null, "Não existe um cliente com esse ID!");
					}
					else {
						showMessageDialog(null, aux.getDados());
					}
					break;
				case 3:
					showMessageDialog(null, listarClientes ());
					break;
			}
		
		} while (opcao != 4);
	}
	
	public void menuProduto () {
		String texto = "Bem vindo à Área de Gerenciamento de Produtos! \n";
		texto += "1. Cadastrar Novo Produto \n";
		texto += "2. Pesquisar Produtos \n";
		texto += "3. Listagem Produtos \n";
		texto += "4. Retornar para menu inicial \n";
		
		int opcao;
		do {
			opcao = parseInt(showInputDialog(texto));
			
			switch (opcao) {
				case 1:
					if (auxP < N) {
						produtos [auxP] = adicionarProduto();
						auxP++;
					}
					else {
						showMessageDialog(null, "Número máximo de produtos cadastrados atingido!");
					}
					break;
				case 2:
					int id = parseInt(showInputDialog("Qual o ID do produto que se deseja pesquisar?"));
					Produto aux = pesquisarProduto(id);
					if (aux == null) {
						showMessageDialog(null, "Não existe um produto com esse ID!");
					}
					else {
						showMessageDialog(null, aux.getDados());
					}
					break;
				case 3:
					showMessageDialog(null, listarProdutos ());
					break;
			}
			
			
		} while (opcao != 4);
	}
	
	public void menuNF () {
		String texto = "Bem vindo à Área de Gerenciamento de NFs! \n";
		texto += "1. Cadastrar nova Nota Fiscal \n";
		texto += "2. Pesquisar NFs \n";
		texto += "3. Listagem de NFs \n";
		texto += "4. Retornar para menu inicial \n";
		
		int opcao;
		do {
			opcao = parseInt(showInputDialog(texto));
			
			switch (opcao) {
				case 1:
					adicionarNF();
					break;
				case 2:
					
			}
		} while(opcao != 4);
	}
	
	public Cliente adicionarCliente () {
		String nome = showInputDialog("Qual é o nome do Novo Cliente?");
		String endereco = showInputDialog("Qual é o endereço do Novo Cliente?");
		Cliente cliente = new Cliente (nome, endereco);
		showMessageDialog(null, "O cliente de ID " + cliente.getId() + "foi cadastrado!");
		return cliente;
	}
	
	public Cliente pesquisarCliente (int id) {
		for (int i = 0; i < auxC; i++) {
			if (clientes [i].getId() == id) {
				return clientes [i];
			}
		}
		return null;
	}
	
	public String listarClientes () {
		String dados = "";
		for (int i = 0; i < auxC; i++) {
			dados += clientes [i].getDados() + "\n\n";
		}
		return dados;
	}
	
	public Produto adicionarProduto () {
		String descricao = showInputDialog("Qual é a descrição do Novo Produto?");
		double preco = parseDouble(showInputDialog("Qual é o preço unitário do Novo Produto?"));
		int qntEstoque = parseInt(showInputDialog("Qual é a quantidade em estoque do Novo Produto?"));
		Produto produto = new Produto(descricao, preco, qntEstoque);
		showMessageDialog(null, "O produto de ID " + produto.getId() + "foi cadastrado!");
		return produto;
	}
	
	public Produto pesquisarProduto(int id) {
		for (int i = 0; i < auxP; i++) {
			if (produtos [i].getId() == id) {
				return produtos [i];
			}
		}
		return null;
	}
	
	public String listarProdutos () {
		String dados = "";
		for (int i = 0; i < auxP; i++) {
			dados += produtos [i].getDados() + "\n\n";
		}
		return dados;
	}
	
	public NotaFiscal adicionarNF () {
		Cliente cliente;
		do {
			int id = parseInt(showInputDialog(listarClientes() + "\nQual o ID do cliente que está fazendo a compra?"));
			cliente = pesquisarCliente(id);
		} while (cliente == null);
		
		String data = showInputDialog("\nQual a data de emissao da NF?");
		
		NotaFiscal nf = new NotaFiscal (data, cliente);
		
		int opcao;
		do {
			Produto produto;
			do {
				int id = parseInt(showInputDialog(listarProdutos() + "\nQual o ID do produto que se deseja comprar?"));
				while (nf.pesquisarProdutosNF(id)) {
					id = parseInt(showInputDialog("\nProduto já adicionado na NF, selecione outro!\n" + listarProdutos() + "\nQual o ID do produto que se deseja comprar?"));
				}
				produto = pesquisarProduto(id);
			} while (produto == null);
			
			int qnt = parseInt(showInputDialog(listarProdutos() + "\nQual a quantidade que se deseja comprar desse produto?"));
			while (qnt > produto.getQntEstoque()) {
				qnt = parseInt(showInputDialog(listarProdutos() + "\nEssa quantidade é maior do que o que temos em estoque (" + produto.getQntEstoque() + "). \nDigite uma nova quantidade: "));
			}
			
			nf.adicionarProduto(produto, qnt);
		
			do {
				opcao = parseInt(showInputDialog("Deseja adicionar mais produtos a NF? (1-sim / 2-nao)"));
			} while (opcao < 1 || opcao > 2);
			
		} while (opcao == 1);
		
		return nf;
	}
	
}
