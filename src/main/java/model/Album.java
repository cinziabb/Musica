package model;

import java.sql.Date;

public class Album {
	private Integer id;
	private String nomeAlbum;
	private Date dataUscita;
	private int numTracce;
	private Artista artist; 
	
	public Album() {
	}

	public Album(String nomeAlbum, Date dataUscita, int numTracce) {
		this.nomeAlbum = nomeAlbum;
		this.dataUscita = dataUscita;
		this.numTracce = numTracce;
	}
	
	public Album(Integer id, String nomeAlbum, Date dataUscita, int numTracce) {
		this.id = id;
		this.nomeAlbum = nomeAlbum;
		this.dataUscita = dataUscita;
		this.numTracce = numTracce;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomeAlbum() {
		return nomeAlbum;
	}

	public void setNomeAlbum(String nomeAlbum) {
		this.nomeAlbum = nomeAlbum;
	}

	public Date getDataUscita() {
		return dataUscita;
	}

	public void setDataUscita(Date dataUscita) {
		this.dataUscita = dataUscita;
	}

	public int getNumTracce() {
		return numTracce;
	}

	public void setNumTracce(int numTracce) {
		this.numTracce = numTracce;
	}
	
	public Artista getArtist() {
		return artist;
	}

	public void setArtist(Artista artist) {
		this.artist = artist;
	}

	@Override
	public String toString() {
		return "Album [id=" + id + 
				", nomeAlbum=" + nomeAlbum + 
				", dataUscita=" + dataUscita + 
				", numTracce=" + numTracce +
				", artist=" + artist +
				"]";
	}
	
}
