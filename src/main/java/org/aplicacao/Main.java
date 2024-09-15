package org.aplicacao;

import br.com.caelum.stella.validation.CPFValidator;
import org.aplicacao.dto.EnderecoDto;
import org.aplicacao.servico.ApiServico;

import java.io.IOException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        ApiServico apiServico = new ApiServico();
        CPFValidator cpfValidator = new CPFValidator();

        try {
            System.out.print("Digite seu CPF para descobrir seu endereço: ");
            String cpf = scanner.nextLine();
            cpfValidator.assertValid(cpf);
            System.out.println("CPF válido");
        } catch (Exception e) {
            System.out.println("CPF inválido");
        }

        try {
            EnderecoDto enderecoDto = apiServico.getEndereco(" ");
            System.out.println(enderecoDto);

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }

    }

}