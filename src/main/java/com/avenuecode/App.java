package com.avenuecode;

import java.net.URI;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class App {

    public static void main(String[] args) throws Exception {
        HttpServer server = inicializaServidor();
        System.out.println("Server is running. Press any key to shutdown...");
        System.in.read();
        server.shutdownNow();
    }

	public static HttpServer inicializaServidor() {
		ResourceConfig config = new ResourceConfig().packages("com.avenuecode.resource");
        URI uri = URI.create("http://localhost:8080/");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}}
