package util;

public interface Messages {
	//Messaggi derivanti da operazioni di insert into
	static final String NO_ROW_AFFECTED = "Nessuna riga inserita";
	static final String NO_PK_GENERATED = "Nessuna primary key generata";
	static final String PK_GENERATED = "La primary key è stata generata";
	//Messaggi derivanti da operazioni di select
	static final String GET_OPERATION_OK = "Recupero dati avvenuto con successo";
	static final String GET_OPERATION_KO = "Non ci sono dati disponibili";
}
