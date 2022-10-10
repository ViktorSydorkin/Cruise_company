package DAO.Util;

import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;
import com.mysql.cj.jdbc.MysqlDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionPool {

    private static final String DB_RESOURCE_BUNDLE = "db";
    private static final String URL = "db.url";


    private ConnectionPool() {
    }

    private static ConnectionPool instance = null;

    /**
     * A singleton method of getting a ConnectionPool instance
     *
     * @return - an instance of connection
     */
    public static synchronized ConnectionPool getInstance() {
        if (instance == null)
            instance = new ConnectionPool();
        return instance;
    }

    /**
     * Creates a DataSource object the was created from the
     * data in the "db.properties" file
     *
     * @return - a datasource instance
     * @see DataSource
     */
    public DataSource getPooledConnectionDataSource() {
        ResourceBundle rb = ResourceBundle.getBundle(DB_RESOURCE_BUNDLE);
        MysqlDataSource ds = new MysqlConnectionPoolDataSource();
        ds.setURL(rb.getString(URL));
        return ds;
    }

    /**
     * Connects to the DB calling the method,
     * that returns the pooled connection source.
     * Creates a pooled connection instance
     *
     * @return - created connection instance
     * @see Connection
     */
    public Connection getConnection() {
        DataSource ds = getPooledConnectionDataSource();
        Connection connection = null;
        try {
            connection = ds.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Gets an autocloseable object and calls a "close" method on it.
     *
     * @param autoCloseable - object to be closed
     * @see AutoCloseable
     */
    public void close(AutoCloseable autoCloseable) {
        if (autoCloseable != null) {
            try {
                autoCloseable.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Gets a connection object and calls "rollback" function on it.
     *
     * @param connection - object to be rolled back
     * @see Connection
     */
    public void rollback(Connection connection) {
        if (connection != null) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}