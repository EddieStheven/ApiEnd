package org.aplicacao.servico;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aplicacao.dto.EnderecoDto;
import org.aplicacao.exception.ApiException;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Scanner;


public class ApiServico {

    EnderecoDto enderecoDto = new EnderecoDto();

        public EnderecoDto getEndereco(String cep) throws IOException, InterruptedException {

            Scanner scanner = new Scanner(System.in);

            try{

                System.out.print("Digite seu CEP para descobrir seu endereço: ");
                cep = scanner.nextLine();

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://viacep.com.br/ws/"+ cep +"/json/")).build();
                HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

                if (response.statusCode() != 200 ){
                    throw new ApiException("Erro ao efetuar a requisição - Status" +response.statusCode());
                }

                ObjectMapper mapper = new ObjectMapper();

                if(enderecoDto != null){
                    enderecoDto = mapper.readValue(response.body(), EnderecoDto.class);
                    System.out.println(enderecoDto);
                } else{
                    throw new ApiException("Erro ao efetuar a requisição - Objeto Nulo");
                }

            } catch (ApiException e){

                System.err.println("Erro geral ao efetuar a requisição na API" + e.getMessage());
                e.printStackTrace();

            }

            return enderecoDto;
        }


}
