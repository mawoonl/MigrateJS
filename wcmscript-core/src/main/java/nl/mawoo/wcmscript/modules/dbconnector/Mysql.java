/**
 * Copyright 2016 Mawoo Nederland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.mawoo.wcmscript.modules.dbconnector;

import nl.mawoo.wcmscript.AbstractScriptModule;

import java.sql.*;

/**
 * This class is responsible to connect to a mysql database
 *
 * @author Bob van der Valk
 */
public class Mysql extends AbstractScriptModule{

    private String db = "jdbc:mysql://localhost/";
    private String dbUser = "root";
    private String dbPass;

    private Connection connection;

    public Mysql() {
    }

    /**
     * Connect to the database
     */
    public Mysql connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(db, dbUser, dbPass);
        } catch (ClassNotFoundException e) {
            getScriptLogger().error("MySql driver class not found: "+ e.getMessage());
        } catch (SQLException e) {
            getScriptLogger().error("Cannot connect to MySql database: "+ e.getMessage());
        }
        return this;
    }

    /**
     * Set a database to use
     * @param db
     */
    public Mysql setDatabase(String db) {
        this.db = "jdbc:mysql://localhost/" + db;
        return this;
    }

    /**
     * Set a database user
     * @param dbUser
     */
    public Mysql setDbUser(String dbUser) {
        this.dbUser = dbUser;
        return this;
    }

    /**
     * Set a database password
     * @param dbPass
     */
    public Mysql setDbPass(String dbPass) {
        this.dbPass = dbPass;
        return this;
    }

    /**
     * Run a sql query and get results back.
     * @param sql your sql manager
     * @return resultSet of your query
     */
    public ResultSetObject query(String sql) {
        try {
            Statement stmt = connection.createStatement();
            String[] queryType = sql.split(" ");

            if("SELECT".equals(queryType[0])) {
                ResultSet rs = stmt.executeQuery(sql);

                return new ResultSetObject(rs);
            } else {
                stmt.execute(sql);
            }
        } catch (SQLException e) {
            getScriptLogger().error("A SQL exception occurred: "+ e.getMessage());
        }

        return null;
    }
}