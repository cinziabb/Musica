package database;

public class ErrorCode {
	
	public static String getMessage(int code) {
		String msg = null;
		
		switch(code) {
		case 1062:
			msg = "Inserimento di un campo gi� esistente";
			break;
		}
		return msg;
	}
}
