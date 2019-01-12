package pl.lukaszgrymulski;

public class DBInfo {
	
	String url = "jdbc:mysql://localhost:3306/jdbc_demo?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	String user = "root";
	String password = "";
	
	public DBInfo() {
		
	}
	
	public DBInfo(String url, String user, String password) {
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}	
}
