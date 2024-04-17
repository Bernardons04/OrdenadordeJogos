package com.mycompany.main;

import packageLeitor.CSVLeitor;
import com.mycompany.main.Item;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Item> itemList = null;
        boolean arquivoLido = false;

        while (true) {
            exibirMenu();
            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println("Lendo o arquivo CSV...");
                    itemList = CSVLeitor.readCSV();
                    arquivoLido = true;
                    System.out.println("Arquivo lido com sucesso.");
                    exibirItens(itemList);
                    break;
                case 2:
                    if (arquivoLido) {
                        System.out.println("Ordenando por categoria (ordem alfabética) usando BubbleSort...");
                        ordenarPorCategoria(itemList);
                        exibirItensOrdenados(itemList);
                        salvarArquivoCSV("JogosOrdenadosporCategoria.csv", itemList);
                        System.out.println("Itens ordenados salvos em 'JogosOrdenadosporCategoria.csv'.");
                    } else {
                        System.out.println("Erro: o arquivo ainda não foi lido. Primeiro, leia o arquivo.");
                    }
                    break;
                case 3:
                    if (arquivoLido) {
                        System.out.println("Ordenando por avaliação (ordem decrescente) dentro de cada categoria usando SelectionSort...");
                        ordenarPorAvaliacao(itemList);
                        exibirItensOrdenados(itemList);
                        salvarArquivoCSV("JogosOrdenadosporAvaliacao.csv", itemList);
                        System.out.println("Itens ordenados por avaliação salvos em 'JogosOrdenadosporAvaliacao.csv'.");
                    } else {
                        System.out.println("Erro: o arquivo ainda não foi lido. Primeiro, leia o arquivo.");
                    }
                    break;
                case 4:
                    System.out.println("Saindo do programa. Obrigado por utilizar!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opcao inválida. Escolha uma opcao válida.");
                    break;
            }
        }
    }

    private static void exibirMenu() {
        System.out.println("===== MENU =====");
        System.out.println("[1] Ler arquivo");
        System.out.println("[2] Ordenar por categoria");
        System.out.println("[3] Ordenar por avaliacao");
        System.out.println("[4] Sair");
        System.out.print("Escolha uma opcao: ");
    }

    private static void exibirItens(List<Item> itemList) {
        System.out.println("Itens do arquivo:");
        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    private static void exibirItensOrdenados(List<Item> itemList) {
        System.out.println("Itens ordenados:");
        for (Item item : itemList) {
            System.out.println(item);
        }
    }

    private static void ordenarPorCategoria(List<Item> itemList) { //bubble
        int tamanhoListaJogos = itemList.size();
        for (int i = 0; i < tamanhoListaJogos - 1; i++) {
            for (int j = 0; j < tamanhoListaJogos - i - 1; j++) {
                if (itemList.get(j).getCategoria().compareTo(itemList.get(j + 1).getCategoria()) > 0) {
                    Collections.swap(itemList, j, j + 1);
                }
            }
        }
    }

    private static void ordenarPorAvaliacao(List<Item> itemList) {
        int tamanhoListaJogos = itemList.size();
        for (int i = 0; i < tamanhoListaJogos - 1; i++) {
            int index = i;
            for (int j = i + 1; j < tamanhoListaJogos; j++) {
                if (itemList.get(j).getCategoria().equals(itemList.get(index).getCategoria())) {
                  if (itemList.get(j).getAvaliacao() < itemList.get(index).getAvaliacao()) {
                      index = j;
                  }
              }
            }
            if (index != i) {
                Item temp = itemList.get(index);
                itemList.set(index, itemList.get(i));
                itemList.set(i, temp);
            }
        }
    }

    private static void salvarArquivoCSV(String fileName, List<Item> itemList) {
        try (FileWriter writer = new FileWriter(fileName)) {
            writer.write("Jogo" + ";" + "Categoria" + ";" + "Avaliacao" + "\n");
            for (Item item : itemList) {
                writer.write(item.getJogos() + ";" + item.getCategoria() + ";" + item.getAvaliacao() + "\n");
            }
            System.out.println("Itens salvos em '" + fileName + "'.");
        } catch (IOException e) {
            System.out.println("Erro ao salvar os itens no arquivo '" + fileName + "'.");
            e.printStackTrace();
        }
    }
}
