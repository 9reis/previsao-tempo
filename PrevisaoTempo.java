import java.net.URI; //Representa um endereço da WEB
import java.net.URLEncode; // Codifica String para uso na WEB 
import java.net.http.HttpClient; // Envia e recebe dados via HTTP 
import java.net.http.HttpRequest; // Representa uma solicitação HTTP
import java.net.http.HttpResponse; // Representa uma respota HTTP
import java.nio.charset.StandardCharsets; // Define o padrão de codificação de caracteres; 
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
	
}