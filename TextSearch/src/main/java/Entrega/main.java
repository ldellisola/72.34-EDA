package Entrega;

import java.io.*;
import java.nio.file.*;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.codec.binary.StringUtils;

public class main {
	

	
	public static void main(String[] args) throws Exception {

		boolean leave= false, hasFile =false;
		
		String customFile = "";
		
		
		
		Scanner in  = new Scanner(System.in);
		
		while(!leave) {
			
			if(!hasFile) {
				
				do {
					System.out.println("Ingresa el path a tu archivo con los productos");
					customFile = in.nextLine();
					
					File file = new File(customFile);
					if( file.exists() || file.canRead())
						hasFile = true;
					else
						System.out.println("File Doesn't exist");
					
				}while(!hasFile);
			}
			
			
			
			System.out.println("Que item queres buscar?");
			String input = in.nextLine().toLowerCase().replaceAll("( )+", " ");
			
			input = org.apache.commons.lang3.StringUtils.stripAccents(input);
				
			if(input.compareToIgnoreCase("exit") == 0)
				leave = true;
			
			List<ItemFind> items = new LinkedList<ItemFind>();
			
			try (Stream<String> stream = Files.lines(Paths.get(customFile))) {

				List<String> lines = stream.collect(Collectors.toList());
				
				
				for (String string : lines) {
					items.add(new ItemFind(string, input));
				}

			} catch (IOException e) {
				System.err.println("Archivo invalido");
				continue;
			}
			catch (InvalidParameterException e) {
				System.err.println("Entrada invalida");
				continue;
			}
			
			
			items.sort((o,t)-> t.compareTo(o));
			
			System.out.println("Estos son los productos que encontramos:");
			for (ItemFind itemFind : items.subList(0, 5)) {
				
				System.out.println(itemFind.toString());
				
			}
			
			System.out.println("\n\n");
			
			
			
			
			

		}
			
		System.out.println("Exiting...");
		
		
	}

}
