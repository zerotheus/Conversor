import com.google.gson.Gson;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ConversorMoedas {
    private Moeda MoedaIn = new Moeda();
    private Moeda MoedaOut = new Moeda();;
    private float taxaConversao;
    private static String URLdaAPI = "https://economia.awesomeapi.com.br/";

    public float converte(int indexIn, int indexOut, Float quantidade) {
        MoedaIn.setMoeda(indexIn, quantidade);
        MoedaOut.setCode(indexOut);
        System.out.println("MoedaInCode" + MoedaIn.getCode());
        System.out.println("MoedaOutCode" + MoedaOut.getCode());
        String URLRequisicao = URLdaAPI + MoedaIn.getCode() + "-" + MoedaOut.getCode();
        System.out.println(URLRequisicao);

        try {
            URL url = new URL(URLRequisicao);
            HttpURLConnection conexao = (HttpURLConnection) url.openConnection();

            if (conexao.getResponseCode() != 200) {
                System.out.println("error na resposta");
                throw new RuntimeException("Erro http :" + conexao.getResponseCode());
            }
            BufferedReader resposta = new BufferedReader(new InputStreamReader(conexao.getInputStream()));
            String jsonString = converteJsonemString(resposta);

            Gson gson = new Gson();
            GetTaxadeConversao ask = gson.fromJson(jsonString, GetTaxadeConversao.class);
            System.out.println("ask " + ask.getTaxa());
            float f = Float.parseFloat(ask.getTaxa());

            System.out.println("pre in" + f);

            taxaConversao = (1 / f);
            System.out.println("taxa out " + taxaConversao);
            MoedaOut.setQuantidade(MoedaIn.getQuantidade() / taxaConversao);

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return MoedaOut.getQuantidade();
    }

    public String converteJsonemString(BufferedReader bufferedReader) throws IOException {
        String resposta, JsonString = "";
        while ((resposta = bufferedReader.readLine()) != null) {
            JsonString += resposta;
        }
        JsonString = limpaJson(JsonString);
        System.out.println(JsonString);
        return JsonString;
    }

    public String limpaJson(String JsonSujo) { // "^\"|\"$",
        String Json = JsonSujo.replaceAll("\\[|]", "");

        return Json;
    }
}
