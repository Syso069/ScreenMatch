package br.com.alura.screenmatch.principal;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner buscaFilme = new Scanner(System.in);
        System.out.println("Digite o filme que deseja buscar: ");
        var busca = buscaFilme.nextLine();

        String retonaBuscaFilme = "http://www.omdbapi.com/?t=" + busca + "&apikey=c72c96bf";


        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(retonaBuscaFilme))
                .build();
        HttpResponse<String> response = client
                .send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
    }
}