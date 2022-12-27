package database;

public class OperationResult {

	private Integer jdbcCode;
	private String message;
	private Object content;
	
	public OperationResult(Object content) {
		this.content = content;
	}
	public OperationResult(String message, Object content) {
		this.message = message;
		this.content = content;
	}
	public OperationResult(Integer jdbcCode, String message) {
		this.jdbcCode = jdbcCode;
		this.message = message;
	}
	public OperationResult(Integer jdbcCode, String message, Object content) {
		this.jdbcCode = jdbcCode;
		this.message = message;
		this.content = content;
	}
	public Integer getJdbcCode() {
		return jdbcCode;
	}
	public void setJdbcCode(Integer jdbcCode) {
		this.jdbcCode = jdbcCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Object getContent() {
		return content;
	}
	public void setContent(Object content) {
		this.content = content;
	}
	@Override
	public String toString() {
		return "OperationResult [jdbcCode=" + jdbcCode + ", message=" + message + ", content=" + content + "]";
	}
	
}
