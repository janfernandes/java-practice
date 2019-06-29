package br.everis.exerciciofinal;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class CSVFileReader extends Book {
	// static Set<Book> books = new HashSet<Book>();

	public void read(String fileName) {
		BufferedReader br = null;
		FileReader fr = null;
		Set<Book> sortedSet = new TreeSet<Book>();

		try {
			fr = new FileReader(fileName);
			br = new BufferedReader(fr);
			// read line by line
			String line;
			while ((line = br.readLine()) != null) {
				// System.out.println(line);
				List<String> bookAttributes = Arrays.asList(line.split(","));
				Book book = new Book();
				book.setTitle(bookAttributes.get(0));
				book.setAuthor(bookAttributes.get(1));
				book.setIsbn(bookAttributes.get(2));
				book.setYear(Integer.parseInt(bookAttributes.get(3)));
				sortedSet.add(book);
			}
			for (Book valor : sortedSet) {
				System.out.println(valor.toString());
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

	public static void main(String[] args) {
		CSVFileReader csv = new CSVFileReader();
		csv.read("books.csv");
	}
}
