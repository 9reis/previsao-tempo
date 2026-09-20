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

    // IMPRIME OS DADOS
    public static void imprimirDadosClimaticos(String dados){
        System.out.println("Dados oiginais (JSON)" + dados);

        JSONObject dadosJson = new JSONObject("location").getString("name");
        JSONObject informacoesMeteorologicas = dadosJson.getJSONObject("current");

        // EXTRAI OS DADOS DA LOCALIZAÇÃO
        String cidade = dadosJson.getJSONObject("location").getString("name");
        String pais = dadosJson.getJSONObject("location").getString("country");

        // EXTRAI DADOS ADICIONAIS
        Strinf condicaoTempo = informacoesMeteorologicas.getJSONObject("condition").getString("text");
        int umidade = informacoesMeteorologicas.getInt("umidity");
        float velocidadeVento = informacoesMeteorologicas.getFloat("winf_kph");
        float pressaoAtmosferica = informacoesMeteorologicas.getFloat("pressure_mb");
        float sensasaoTermica = informacoesMeteorologicas.getFloat("feelslike_c");
        float temperaturaAtual = informacoesMeteorologicas.getFloat("temp_c");
        
        // PEGA DATA E HORA NO MOMENTO DA CONSULTA 
        String dataHoraString = informacoesMeteorologicas.getString("last_updated");

        //IMPRIME AS INFORMAÇÕES
        System.out.println("Informações Meteorológicas para: " + cidade + ", " + pais);
        System.out.println("Data e Hora: " + dataHoraString);
        System.out.println("Temperatura Atual: " + temperaturaAtual + "°C");
        System.out.println("Sensação Térmica: " + sensasaoTermica + "°C");
        System.out.println("Condição do Tempo: " + condicaoTempo);
        System.out.println("Umidade: " + umidade + "%");
        System.out.println("Velocidade do Vento: " + velocidadeVento + "km/h");
        System.out.println("Pressao Atmosférica: " + pressaoAtmosferica + "mb" );
    }
	
}