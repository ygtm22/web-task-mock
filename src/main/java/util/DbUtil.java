package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbUtil {
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql:axizdb_kadai", "axizuser", "axiz");
        } catch (Exception e) {
            // 本来は専用の例外クラスを作成したほうがよい
        	e.printStackTrace();
        }
		return null;
    }
}
