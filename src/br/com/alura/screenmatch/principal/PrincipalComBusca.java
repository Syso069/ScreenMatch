package br.com.alura.screenmatch.principal;

import br.com.alura.screenmatch.excecao.ConversaoDeAnoExeception;
import br.com.alura.screenmatch.modelos.Titulo;
import br.com.alura.screenmatch.modelos.TituloOmdb;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;

public class PrincipalComBusca {
    public static void main(String[] args) throws IOException, InterruptedException {
        Scanner buscaFilme = new Scanner(System.in);
        System.out.println("Digite o filme que deseja buscar: ");
        var busca = buscaFilme.nextLine();
        var buscaSemEspaco = URLEncoder.encode(busca, "UTF-8");

        String retonaBuscaFilme = "http://www.omdbapi.com/?t=" + buscaSemEspaco + "&apikey=c72c96bf";
        try{
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(retonaBuscaFilme))
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());
            String json = response.body();
            System.out.println(json);

            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                    .create();
            TituloOmdb meuTituloOmdb = gson.fromJson(json, TituloOmdb.class);
            System.out.println(meuTituloOmdb);


            Titulo meuTitulo = new Titulo(meuTituloOmdb);
            System.out.println("\nMeu titulo já convertido");
            System.out.println(meuTitulo);

            FileWriter escrita = new FileWriter("filmes.txt");
            escrita.write(meuTitulo.toString());
            escrita.close();

        }catch (NumberFormatException e){
            System.out.println("Ocorreu um erro:\n" + e.getMessage());
        }catch (IllegalArgumentException e){
            System.out.println("Argumento inválido, verifique o endereço");
        }catch (ConversaoDeAnoExeception e){
            System.out.println(e.getMessage());
        }
        System.out.println("Programa finalizado com sucesso!");
    }
}