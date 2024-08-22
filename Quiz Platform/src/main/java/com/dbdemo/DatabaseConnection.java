package com.dbdemo;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseConnection {

    // URL = jdbc:postgresql://<host>:<port>/<database>
    private static String dbURL = "jdbc:postgresql://aws-0-ap-south-1.pooler.supabase.com:6543/postgres";
    private static String dbUser = "postgres.crtttzkozjadhjjvndga";
    private static String dbPass = "Hetv1@19.25";

    private static Connection con = null;

    public static Connection getConnection() throws SQLException {
        if (con == null || con.isClosed()) {
            try {
                con = DriverManager.getConnection(dbURL, dbUser, dbPass);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
                throw new SQLException("Failed to create the database connection");
            } catch (Exception e) {
                System.err.println("An unexpected error occurred.");
                // e.printStackTrace();
                throw new SQLException("Unexpected error", e);
            }
        }
        return con;
    }

    public static ResultSet getQuestions() throws SQLException {
        ResultSet rs = null;
        try {
            Connection con = getConnection();
            String query = "Select * from questions";
            Statement st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("SQL exception occurred while fetching questions.");
            // e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while fetching questions.");
            // e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getScore() throws SQLException {
        ResultSet rs = null;
        try {
            Connection con = getConnection();
            String query = "Select * from leaderboard";
            Statement st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("SQL exception occurred while fetching questions.");
            // e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while fetching questions.");
            // e.printStackTrace();
        }
        return rs;
    }

    public static ResultSet getUsers() throws SQLException {
        ResultSet rs = null;
        try {
            Connection con = getConnection();
            String query = "Select * from userdetails";
            Statement st = con.createStatement();
            rs = st.executeQuery(query);
        } catch (SQLException e) {
            System.err.println("SQL exception occurred while fetching questions.");
            // e.printStackTrace();
        } catch (Exception e) {
            System.err.println("An unexpected error occurred while fetching questions.");
            // e.printStackTrace();
        }
        return rs;
    }
}
