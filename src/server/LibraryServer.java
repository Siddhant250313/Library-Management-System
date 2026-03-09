package server;

import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpExchange;
import com.google.gson.Gson;
import dao.BookDAO;
import Model.Book;

import java.io.*;
import java.net.InetSocketAddress;
import java.util.List;

public class LibraryServer {

    public static void main(String[] args) throws IOException {
        // Create an HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Root context
        server.createContext("/", exchange -> {
            String response = "<h1>✅ Library Server is running!</h1>";
            exchange.getResponseHeaders().add("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.getBytes().length);
            try (OutputStream os = exchange.getResponseBody()) {
                os.write(response.getBytes());
            }
        });

        // GET all books
        server.createContext("/books", exchange -> {
            if ("GET".equalsIgnoreCase(exchange.getRequestMethod())) {
                List<Book> books = new BookDAO().getAllBooks();
                String json = new Gson().toJson(books);
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, json.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(json.getBytes());
                }
            }
        });

        // POST add book
        server.createContext("/addbook", exchange -> {
            if ("POST".equalsIgnoreCase(exchange.getRequestMethod())) {
                InputStreamReader reader = new InputStreamReader(exchange.getRequestBody());
                String body = new BufferedReader(reader).lines().reduce("", (a, b) -> a + b);
                Book book = new Gson().fromJson(body, Book.class);
                new BookDAO().addBook(book);

                String response = "{\"message\":\"Book added successfully!\"}";
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Content-Type", "application/json");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                try (OutputStream os = exchange.getResponseBody()) {
                    os.write(response.getBytes());
                }
            }
        });

        // Start the server
        server.start();
        System.out.println("✅ Server running on http://localhost:8080");
    }
}
