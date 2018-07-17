import java.util.Date;
import java.util.Scanner;

public class Sistema {
	public int tamanhoParticao;
	public Scanner scanner;
	public String pastaAtual;
	
	public Sistema() {
		this.pastaAtual = "/";
		this.tamanhoParticao = 20;
		this.scanner = new Scanner(System.in);
	}
	
	public void run() {
		while(true) {
			this.printTerminal();
			String entrada = this.scanner.nextLine();
			String[] partesComando = entrada.split(" ");
			
			try {
				Comando comando = Comando.valueOf(partesComando[0]);
				exec(comando);
			} catch(IllegalArgumentException e) {
				System.err.println("Comando não existe");
			}
		}
	}
	
	public void printTerminal() {
		System.out.print("root@localhost: / $ ");
	}
	
	public void exec(Comando comando) {
		switch(comando) {
			case novoArquivo:
				novoArquivo();
				break;
			case novaPasta:
				novaPasta();
				break;
			case removeArquivo:
				break;
			case removePasta:
				break;
			case detalheArquivo:
				break;
			case detalhePasta:
				break;
			case infoDisco:
				break;
			case comandos:
				comandos();
				break;
			case mudarPasta:
				break;
			case verPasta:
				break;
		}
	}
	
	public void novoArquivo() {
		Arquivo arquivo = new Arquivo();
		
		System.out.print("Informe o Nome: ");
		arquivo.setNomeExt(this.scanner.nextLine());
		
		System.out.print("Informe o Tamanho: ");
		arquivo.setTamanho(this.scanner.nextLine());
		
		arquivo.setDataCriacao(new Date());
		arquivo.setPasta(this.pastaAtual);
		
		System.out.print(arquivo);
		
	}
	
	public void novaPasta() {
		Arquivo arquivo = new Arquivo();
		
		System.out.print("Informe o Nome: ");
		arquivo.setNome(this.scanner.nextLine());
		arquivo.setTamanho(0);
		arquivo.setExtensao("pasta");
		arquivo.setDataCriacao(new Date());
		arquivo.setPasta(this.pastaAtual);
		
		System.out.print(arquivo);
	}
	
	public void comandos() {
		System.out.println("----Comandos----");
		System.out.println();
		System.out.println("novoArquivo -> Criar Novo Arquivo");
		System.out.println("novaPasta -> Criar Nova Pasta");
		System.out.println("removeArquivo -> Remover Novo Arquivo");
		System.out.println("removePasta -> Remover Nova Pasta");
		System.out.println("detalheArquivo -> Detalhes do Arquivo");
		System.out.println("detalhePasta -> Detalhes da Pasta");
		System.out.println("infoDisco -> Informações de Disco");
		System.out.println("comandos -> Lista Comandos");
		System.out.println("mudarPasta -> Mudar Pasta");
		System.out.println("verPasta -> Lista arquivos da Pasta");
		System.out.println();
	}
}
