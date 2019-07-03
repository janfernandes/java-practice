package br.everis.exerciciofinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;


public class CSVFileReader extends Book {
	
	/**
	  * Método que lê as linhas de um arquivo csv e chama o método addBooktoSet()
	  */
	public void read(String fileName, Set<Book> sortedSet) {
		BufferedReader br = null;
		FileReader fr = null;

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			// read line by line
			String line;
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				List<String> bookAttributes = Arrays.asList(line.split(","));
				addBooktoSet(bookAttributes, sortedSet);
			}

		} catch (java.io.FileNotFoundException e) {
			System.out.println("Nao foi possível abrir o arquivo para leitura" + e.toString());
			e.printStackTrace();
		} catch (IOException e) {
			System.err.format("IOException: %s%n", e.toString());
		} catch (Exception e) {
			System.err.format("Exception: %s%n", e.toString());
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fr != null) {
					fr.close();
				}	
			} catch (IOException ex) {
				System.err.format("IOException: %s%n", ex);
			}
		}
	}
	
	
	/**
	  * Método que adiciona um livro ao Set de forma ordenada de acordo com o compareTo da super classe.
	  */
	private void addBooktoSet(List<String> bookAttributes, Set<Book> sortedSet) {
		
		Book book = new Book();
		book.setTitle(bookAttributes.get(0));
		book.setAuthor(bookAttributes.get(1));
		book.setIsbn(bookAttributes.get(2));
		book.setYear(Integer.parseInt(bookAttributes.get(3)));
		sortedSet.add(book);
		
	}
	
	/**
	  * Método que imprime na tela os valores dentro do Set.
	  */
	private void printTheElements(Set<Book> sortedSet) {
		for (Book valor : sortedSet) {
			System.out.println(valor.toString());
		}
	}
	
	public static void main(String[] args) {
		Set<Book> sortedSet = new TreeSet<Book>();
		
		CSVFileReader csv = new CSVFileReader();
		
		csv.read("books.csv", sortedSet);
		
		csv.printTheElements(sortedSet);
	}

}
