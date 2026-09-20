import java.net.URI; //Representa um endereço da WEB
import java.net.URLEncode; // Codifica String para uso na WEB 
import java.net.http.HttpClient; // Envia e recebe dados via HTTP 
import java.net.http.HttpRequest; // Representa uma solicitação HTTP
import java.net.http.HttpResponse; // Representa uma respota HTTP
import java.nio.charset.StandardCharsets; // Define o padrão de codificação de caracteres; 
import java.nio.file.Paths;

import org.json.JSONObject; // Biblioteca JSONObject
import java.util.Scanner; // Scanner para entrada de dados 

public class PrevisaoTempo{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        System.out.print("Digite o nome da cidade: ");
        String cidade = sc.nextLine(); // Pega a cidade digitada 

        try{
            String dadosClimaticos = getDadosClimaticos(cidade); // retorna um JSON 
            
            // Código 1006 indica "Localização não encontrada";
            if(dadosClimaticos.contains("\"code\":1006")){
                System.out.println("Localização não encontrada. Por favor, tente novamente.");
            }else{
                imprimirDadosClimaticos(dadosClimaticos);
            }
        }catch(Exception e){
                System.out.println(e.getMessage());
            }
    }

    public static String getDadosClimaticos(String cidade) throws Exception{
        String apiKey = Files.readString(Paths.get("api-key.txt")).trim();

        String formataNomeCidade = URLEncode.encode(cidade, StandardCharsets.UTF_8);
        // Cria o link da api 
        String apiUrl = "http://api.weatherapi.com/v1.current.json?key=" + apiKey + "&q=" + formataNomeCidade; 
        HttpRequest request = HttpRequest.newBuilder() // Começa a contrução de uma nova solicitação HTTP
            .uri(URI.create(apiUrl)) // Define a uri da solicitação
            .build(); // Finaliza a construção da solicitação;

            //Criar objeto enviar solicitação HTTP e receber respostas HTTP, para acessar o site WeatherAPI 
            HttpClient cliente = HttpClient.newHttpClient();

            // Envia requisições HTTP e recebe respostas HTTP, comunicar com o site da API 
            HttpResponse responde = cliente.send(request, HttpResponse.BodyHandlers.ofString());

        return response.body();
    }
	
}