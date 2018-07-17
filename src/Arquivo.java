import java.util.Date;

public class Arquivo {
	private String nome;
	private String extensao;
	private String pasta;
	private Date dataCriacao;
	private Integer tamanho;
	
	public Arquivo(String nome, String extensao, Date dataCriacao, Integer tamanho) {
		super();
		this.nome = nome;
		this.extensao = extensao;
		this.dataCriacao = dataCriacao;
		this.tamanho = tamanho;
	}
	
	public Arquivo() {
		
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public void setNomeExt(String nomeExt) {
		String[] aux = nomeExt.split("[.]");
		String formato = aux[aux.length-1];
		this.nome = nomeExt.replace("."+formato, "");
		if(aux.length==1) {
			this.extensao = "txt";
		} else {
			this.extensao = formato;
		}
	}
	
	public String getExtensao() {
		return extensao;
	}
	
	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public Integer getTamanho() {
		return tamanho;
	}
	
	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}
	
	public void setTamanho(String tamanho) {
		this.tamanho = Integer.parseInt(tamanho);
	}

	public String getPasta() {
		return pasta;
	}

	public void setPasta(String pasta) {
		this.pasta = pasta;
	}

	@Override
	public String toString() {
		return "\nArquivo \nNome=" + nome + "\nExtensão=" + extensao + "\nDiretório=" + pasta + "\nData de Criação=" + dataCriacao + "\nTamanho="
				+ tamanho+ "\n";
	}
}
