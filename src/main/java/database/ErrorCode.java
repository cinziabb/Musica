package database;

public class ErrorCode {
	
	public static String getMessage(int code) {
		String msg = null;
		
		switch(code) {
		case 1062:
			msg = "Inserimento di un campo già esistente";
			break;
		}
		return msg;
	}
}
