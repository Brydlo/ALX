package sklep.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;


@WebServlet("/products1")
public class Products0 extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest requets, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        PrintWriter out = response.getWriter();
        out.println("Zaraz odczytam produkty z bazy...");

        final String url = "jdbc:postgresql://localhost/sklep";
        final String sql = "SELECT * FROM products";

        try(Connection c = DriverManager.getConnection(url , "kurs", "abc123");
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql)) {

            while(rs.next()) {
                out.printf("Produkt nr %s to jest %s za cenę %s\n",
                        rs.getInt("product_id"), rs.getString("product_name"), rs.getBigDecimal("price"));
            }
        } catch(SQLException e) {
            out.println("Wielka bieda!");
            e.printStackTrace(out);
        }
    }
}

