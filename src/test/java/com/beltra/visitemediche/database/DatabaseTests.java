package com.beltra.visitemediche.database;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

@SpringBootTest
public class DatabaseTests {

    @Autowired
    private DataSource dataSource;

    @Disabled
    @Test
    void testDatabaseConnection() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();

            // Query per ottenere il nome del database
            ResultSet rs = stmt.executeQuery("SELECT current_database()"); // Per PostgreSQL
            if (rs.next()) {
                System.out.println("Database attualmente in uso: " + rs.getString(1));
            }
        }
        finally {
            dataSource.getConnection().close();
        }
    }


    @Disabled
    @Test
    public void resettaAndPopolaUtentiAndRuoli() throws Exception {
        try (Connection connection = dataSource.getConnection()) {
            Statement stmt = connection.createStatement();

            stmt.execute("CALL resetta_e_popola_utenti_ruoli()"); // esecuzione stored procedure su db visite_mediche

            System.out.println("Stored Procedure eseguita correttamente.\n");

        } finally {
            dataSource.getConnection().close();
        }
    }





}
