package DAO.Implementation;

import org.apache.ibatis.jdbc.ScriptRunner;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB {
    static String SQLScriptFilePath = "C:\\Users\\plwkc\\IdeaProjects\\Cruise_company\\src\\main\\resources\\Create_test_DB.sql";

    public static void createDB() throws SQLException {


        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cruise_company_test?user=root&password=root1");
        Statement stmt = null;
        try {
            ScriptRunner sr = new ScriptRunner(con);
            Reader reader = new BufferedReader(
                    new FileReader(SQLScriptFilePath));
            sr.runScript(reader);
        } catch (Exception e) {
            System.err.println("Failed to Execute" + SQLScriptFilePath
                    + " The error is " + e.getMessage());
        } finally {
            con.close();
            if (stmt != null) {
                stmt.close();
            }
        }
    }
}
