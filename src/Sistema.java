import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Sistema {
	public int tamanhoParticao;
	public Scanner scanner;
	public String pastaAtual;
	public Integer[] disco;
	public ArrayList<Arquivo> arquivos;
	public int COUNT_ID;

	public Sistema() {
		this.COUNT_ID = 1;
		this.pastaAtual = "/";
		this.tamanhoParticao = 20;
		this.scanner = new Scanner(System.in);
		this.arquivos = new ArrayList<>();
		this.disco = new Integer[this.tamanhoParticao];

		for (int i = 0; i < disco.length; i++) {
			this.disco[i] = 0;
		}
	}

	public void run() {
		while (true) {
			this.printTerminal();
			String entrada = this.scanner.nextLine();
			String[] partesComando = entrada.split(" ");

			try {
				Comando comando = Comando.valueOf(partesComando[0]);
				exec(comando);
			} catch (IllegalArgumentException e) {
				this.printErro("Comando n�o existe");
			}
		}
	}

	public void printTerminal() {
		System.out.print("root@localhost: " + this.pastaAtual + " $ ");
	}

	public void printErro(String erro) {
		System.out.println("\n" + erro + "\n");
	}

	public void exec(Comando comando) {
		switch (comando) {
		case novoArquivo:
			novoArquivo();
			break;
		case novaPasta:
			novaPasta();
			break;
		case removerArquivo:
			removerArquivo();
			break;
		case removerPasta:
			removerPasta();
			break;
		case detalheArquivo:
			detalheArquivo();
			break;
		case detalhePasta:
			detalhePasta();
			break;
		case infoDisco:
			infoDisco();
			break;
		case comandos:
			comandos();
			break;
		case mudarPasta:
			mudarPasta();
			break;
		case voltarPasta:
			voltarPasta();
			break;
		case verPasta:
			verPasta();
			break;
		}
	}

	public void novoArquivo() {
		Arquivo arquivo = new Arquivo();

		System.out.print("Informe o Nome: ");
		arquivo.setNomeExt(this.scanner.nextLine());

		System.out.print("Informe o Tamanho: ");
		int aux = Integer.parseInt(this.scanner.nextLine());
		while (aux > this.tamanhoParticao || aux < 0) {// || Integer.parseInt(this.scanner.nextLine()) <= 1){
			this.printErro("Tamanho inv�lido! N�o pode ser maior que o tamanho da parti��o ou menor que 1");
			System.out.print("Informe o Tamanho: ");
			aux = Integer.parseInt(this.scanner.nextLine());
		}
		arquivo.setTamanho(aux);

		arquivo.setDataCriacao(new Date());
		arquivo.setPasta(this.pastaAtual);
		arquivo.setId(this.COUNT_ID);

		Integer posicaoInicial = this.addDisco(arquivo);
		arquivo.setPrimeiraPosicao(posicaoInicial);

		this.arquivos.add(arquivo);

		this.COUNT_ID++;
	}

	public void novaPasta() {
		Arquivo arquivo = new Arquivo();

		System.out.print("Informe o Nome: ");
		arquivo.setNome(this.scanner.nextLine());
		arquivo.setTamanho(0);
		arquivo.setExtensao("pasta");
		arquivo.setDataCriacao(new Date());
		arquivo.setPasta(this.pastaAtual);
		arquivo.setPrimeiraPosicao(0);
		arquivo.setId(this.COUNT_ID);

		this.arquivos.add(arquivo);

		this.COUNT_ID++;
	}

	public void mudarPasta() {
		System.out.print("Informe o Nome: ");
		String pasta = this.scanner.nextLine();
		boolean entrou = false;
		for (int i = 0; i < this.arquivos.size(); i++) {
			if (this.arquivos.get(i).getExtensao().equals("pasta")) {
				if (this.arquivos.get(i).getNome().equals(pasta)
						&& this.arquivos.get(i).getPasta().equals(this.pastaAtual)) {
					this.pastaAtual += (this.arquivos.get(i).getNome() + "/");
					entrou = true;
					break;
				}
			}
		}

		if (!entrou) {
			this.printErro("Pasta n�o existe");
		}
	}

	public void voltarPasta() {
		if (this.pastaAtual.equals("/")) {
			this.printErro("N�o pode voltar da pasta raiz");
			return;
		}
		String[] aux = this.pastaAtual.split("/");
		String pasta = aux[aux.length - 1];

		String pastaVoltar = "/";

		for (int i = 1; i < aux.length - 1; i++) {
			pastaVoltar += aux[i] + "/";
		}

		for (int i = 0; i < this.arquivos.size(); i++) {
			if (this.arquivos.get(i).getExtensao().equals("pasta")) {
				if (this.arquivos.get(i).getNome().equals(pasta)
						&& this.arquivos.get(i).getPasta().equals(pastaVoltar)) {
					this.pastaAtual = this.arquivos.get(i).getPasta();
				}
			}
		}

	}

	public void infoDisco() {
		System.out.println();
		System.out.print("Disco: ");
		for (int i = 0; i < this.disco.length; i++) {
			System.out.print(this.disco[i] + " | ");
		}
		System.out.println();
		System.out.println();
	}

	public void comandos() {
		System.out.println();
		System.out.println("\t\t------------ Comandos ------------");
		System.out.println();
		System.out.println("\t\tnovoArquivo -> Criar Novo Arquivo");
		System.out.println("\t\tnovaPasta -> Criar Nova Pasta");
		System.out.println("\t\tremoveArquivo -> Remover Novo Arquivo");
		System.out.println("\t\tremovePasta -> Remover Nova Pasta");
		System.out.println("\t\tdetalheArquivo -> Detalhes do Arquivo");
		System.out.println("\t\tdetalhePasta -> Detalhes da Pasta");
		System.out.println("\t\tinfoDisco -> Informa��es de Disco");
		System.out.println("\t\tcomandos -> Lista Comandos");
		System.out.println("\t\tmudarPasta -> Mudar Pasta");
		System.out.println("\t\tverPasta -> Lista arquivos da Pasta");
		System.out.println();
	}

	public void detalheArquivo() {
		System.out.print("Informe o Nome: ");
		String pasta = this.scanner.nextLine();
		boolean entrou = false;
		for (int i = 0; i < this.arquivos.size(); i++) {
			if (!this.arquivos.get(i).getExtensao().equals("pasta")) {
				if (this.arquivos.get(i).getNome().equals(pasta)
						&& this.arquivos.get(i).getPasta().equals(this.pastaAtual)) {
					System.out.println(this.arquivos.get(i).toString());
					entrou = true;
				}
			}
		}

		if (!entrou) {
			this.printErro("Arquivo n�o existe");
		}
	}

	public void detalhePasta() {
		System.out.print("Informe o Nome: ");
		String pasta = this.scanner.nextLine();
		boolean entrou = false;
		Arquivo arquivoPasta = null;
		for (int i = 0; i < this.arquivos.size(); i++) {
			if (this.arquivos.get(i).getExtensao().equals("pasta")) {
				if (this.arquivos.get(i).getNome().equals(pasta)
						&& this.arquivos.get(i).getPasta().equals(this.pastaAtual)) {
					arquivoPasta = this.arquivos.get(i);
					entrou = true;
				}
			}
		}

		if (!entrou) {
			this.printErro("Pasta n�o existe");
		} else {
			int tamanho = 0;
			for (int i = 0; i < this.arquivos.size(); i++) {
				if (this.arquivos.get(i).getPasta().equals(arquivoPasta.getPasta() + arquivoPasta.getNome() + "/")) {
					tamanho += this.arquivos.get(i).getTamanho();
				}
			}

			System.out.println();
			System.out.println("Nome: " + arquivoPasta.getNome());
			System.out.println("Diret�rio: " + arquivoPasta.getPasta());
			System.out.println("Data de Cria��o: " + arquivoPasta.getDataCriacao());
			System.out.println("Tamanho: " + tamanho);
			System.out.println();
		}
	}

	public void verPasta() {
		System.out.println();
		for (int i = 0; i < this.arquivos.size(); i++) {
			if (this.arquivos.get(i).getPasta().equals(this.pastaAtual)) {
				System.out.println(this.arquivos.get(i).getNome() + " --------------------------- "
						+ this.arquivos.get(i).getTamanho() + "B");
			}
		}
		System.out.println();
	}

	public void removerArquivo() {
		System.out.print("Informe o Nome: ");
		String pasta = this.scanner.nextLine();
		boolean entrou = false;

		Arquivo oldArquivo = null;

		for (int i = 0; i < this.arquivos.size(); i++) {
			if (!this.arquivos.get(i).getExtensao().equals("pasta")) {
				if (this.arquivos.get(i).getNome().equals(pasta)
						&& this.arquivos.get(i).getPasta().equals(this.pastaAtual)) {
					oldArquivo = this.arquivos.get(i);
					this.arquivos.remove(i);
					entrou = true;
					break;
				}
			}
		}

		if (!entrou) {
			this.printErro("Arquivo n�o existe");
		} else {
			this.removerDisco(oldArquivo);
		}
	}

	public void removerPasta() {
		System.out.print("Informe o Nome: ");
		String pasta = this.scanner.nextLine();
		boolean entrou = false;
		Arquivo arquivoPasta = null;
		for (int i = 0; i < this.arquivos.size(); i++) {
			if (this.arquivos.get(i).getExtensao().equals("pasta")) {
				if (this.arquivos.get(i).getNome().equals(pasta)
						&& this.arquivos.get(i).getPasta().equals(this.pastaAtual)) {
					arquivoPasta = this.arquivos.get(i);
					entrou = true;
				}
			}
		}

		if (!entrou) {
			this.printErro("Pasta n�o existe");
		} else {
			ArrayList<Arquivo> remover = new ArrayList<>();
			for (Arquivo arquivo : this.arquivos) {
				if (arquivo.getPasta().equals(arquivoPasta.getPasta() + arquivoPasta.getNome() + "/")) {
					remover.add(arquivo);
					this.removerDisco(arquivo);
				}
			}
			this.arquivos.removeAll(remover);
			this.arquivos.remove(arquivoPasta);
		}
	}

	private void removerDisco(Arquivo arquivo) {
		for (int i = arquivo.getPrimeiraPosicao(); i < (arquivo.getTamanho() + arquivo.getPrimeiraPosicao()); i++) {
			this.disco[i] = 0;
		}
	}

	public Integer addDisco(Arquivo arquivo) {
		int countlivre = 0;
		int posicao = 0;

		if (isDesfragmentar(arquivo.getTamanho())) {
			desfragmentar();
		}

		for (int i = 0; i < disco.length; i++) {
			if (disco[i] == 0)
				countlivre++;
			else {
				countlivre = 0;
				posicao = i + 1;
			}

			if (countlivre == arquivo.getTamanho())
				break;
		}

		for (int i = posicao; i < (arquivo.getTamanho() + posicao); i++) {
			disco[i] = arquivo.getId();
		}

		return posicao;
	}

	protected boolean existeMemoria(int espaco) {
		int countlivre = 0;
		for (int i = 0; i < disco.length; i++) {
			if (disco[i] == 0)
				countlivre++;
		}

		if (espaco <= countlivre)
			return true;
		else
			return false;
	}

	protected boolean isDesfragmentar(int tamanho) {
		int sequencia = 0;
		for (int i = 0; i < disco.length; i++) {
			if (disco[i] == 0)
				sequencia++;
			else
				sequencia = 0;

			if (sequencia >= tamanho)
				return false;
		}
		// Caso chegue no final da mem�ria e o processo n�o for alocado, realizar
		// desfragmenta��o
		return true;
	}

	protected void desfragmentar() {
		ArrayList<Integer> aux = new ArrayList<>();

		// Copia toda a mem�ria para um auxiliar
		for (int i = 0; i < this.disco.length; i++) {
			if (this.disco[i] != 0)
				aux.add(disco[i]);
		}

		// Cria uma mem�ria nova
		Integer[] memoria = new Integer[this.disco.length];

		for (int i = 0; i < memoria.length; i++)
			memoria[i] = 0;

		int count = 0;

		// Insere os dados
		for (Integer i : aux) {
			memoria[count] = i;
			count++;
		}

		// Substitui a mem�ria
		this.disco = memoria;
	}
}
