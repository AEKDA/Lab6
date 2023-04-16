package server.dbutil;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Logger;

public class DBConnection {
    private Connection connection;
    private Logger logger;

    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    {
        logger = Logger.getLogger(DBConnection.class.getName());
    }

    public DBConnection(String config) {
        try {
            Properties prop = new Properties();
            prop.load(new FileReader(config));
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5433/studs",
                    prop.getProperty("DB_LOGIN"), prop.getProperty("DB_PASSWORD"));

            logger.info("Соединение установлено");
        } catch (SQLException e) {
            logger.info(e.getMessage());
        } catch (IOException e) {
            logger.info("Не удалось прочитать файл конфигурации");
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
