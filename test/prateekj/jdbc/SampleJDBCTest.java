package prateekj.jdbc;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;

import static junit.framework.Assert.assertEquals;

/**
 * Created by prateekj on 1/25/14.
 */


public class SampleJDBCTest {
    Connection conn = null;
    Statement stmt = null;
    ResultSet result;
    static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost/test";

    static final String USER = "prateek";
    static final String PASS = "pprrateek";
    @Before
    public void setUp() throws Exception {
        System.out.println("Setup is running");
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
                stmt = conn.createStatement();
            String om_customer = "create table om_customer(" +
                    "cust_id int primary key auto_increment," +
                    "cust_name char(40)," +
                    "address char(100)," +
                    "phone_no char(20)" +
                    ");";
            String om_orders = "create table om_orders(" +
                    "cust_id int," +
                    "order_id int primary key auto_increment," +
                    "total_bill int," +
                    "date_of_order date" +
                    ");";
            String om_payment = "create table om_payment(" +
                    "payment_status char(1)," +
                    "payment_id int primary key auto_increment," +
                    "order_id int," +
                    "date_of_payment date" +
                    ");";
            String om_products = "create table om_products(" +
                    "product_id int primary key auto_increment," +
                    "product_name char(30)," +
                    "unit_price int" +
                    ");";
            String om_order_item = "create table om_order_item(\n" +
                    "order_item int primary key auto_increment,\n" +
                    "order_id int," +
                    "price int," +
                    "quantity int," +
                    "product_id int" +
                    ");";
            String addForeignKeyToOrdersTable ="alter table om_orders " +
                    "add constraint CUST_ID_FK foreign key(cust_id) references om_customer(cust_id);";
            boolean rs = stmt.execute(om_customer);
            stmt.execute(om_orders);
            stmt.execute(om_order_item);
            stmt.execute(om_payment);
            stmt.execute(om_products);
//            stmt.execute(addForeignKeyToOrdersTable);

            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tear down is running");
        try{
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
            String sql1 = "drop table om_customer";
            String sql2 = "drop table om_orders";
            String sql3 = "drop table om_order_item";
            String sql4 = "drop table om_payment";
            String sql5 = "drop table om_products";
            boolean rs = stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
            stmt.execute(sql5);
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }
    }



}
