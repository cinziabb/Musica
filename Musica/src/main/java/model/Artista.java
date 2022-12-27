package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Artista {
	private Integer id;
	private String nome;
	private Date dataNascita;
	private int annoInizio;
	private int annoFine;
	private List<Album> album;
	
	public Artista() {
		album = new ArrayList<Album>();
	}

	public Artista(String nome, Date dataNascita, int annoInizio, int annoFine) {
		this.nome = nome;
		this.dataNascita = dataNascita;
		this.annoInizio = annoInizio;
		this.annoFine = annoFine;
		album = new ArrayList<Album>();
	}

	public Artista(Integer id, String nome, Date dataNascita, int annoInizio, int annoFine) {
		this.id = id;
		this.nome = nome;
		this.dataNascita = dataNascita;
		this.annoInizio = annoInizio;
		this.annoFine = annoFine;
		album = new ArrayList<Album>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascita() {
		return dataNascita;
	}

	public void setDataNascita(Date dataNascita) {
		this.dataNascita = dataNascita;
	}

	public int getAnnoInizio() {
		return annoInizio;
	}

	public void setAnnoInizio(int annoInizio) {
		this.annoInizio = annoInizio;
	}

	public int getAnnoFine() {
		return annoFine;
	}

	public void setAnnoFine(int annoFine) {
		this.annoFine = annoFine;
	}

	public List<Album> getAlbum() {
		return album;
	}

	public void setAlbum(List<Album> album) {
		this.album = album;
	}
	
	public void addAlbum(Album nuovoAlbum) {
		album.add(nuovoAlbum);
	}

	@Override
	public String toString() {
		return "Artista [id=" + id 
				+ ", nome=" + nome 
				+ ", dataNascita=" + dataNascita 
				+ ", annoInizio=" + annoInizio
				+ ", annoFine=" + annoFine 
				+ ", album=" + album + "]";
	}

	
	
}
