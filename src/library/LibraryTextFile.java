//@ Sasi, Anesha and Arif
package library;

	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.FileOutputStream;
	import java.io.IOException;
	import java.io.PrintWriter;
	import java.nio.file.Files;
	import java.nio.file.Path;
	import java.nio.file.Paths;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Scanner;
	import java.text.ParseException;
	import java.time.LocalDate;

	public class LibraryTextFile {
		
		// The path to the file to use
		public static final String FILE_NAME = "book.txt";
		
		// Modify this method as necessary to convert a line of text from the file to a new item instance
		private static Book convertLineToItem(String line) throws ParseException {
			String[] parts = line.split("\t");
			Book book = new Book();
			
			book.setBookTitle(parts[0].trim());
			book.setBookAuthor(parts[1].trim());
			book.setBookStatus(Status.valueOf(parts[2]));
			if(parts[3].equals("")|| parts[3].equals("null")|| parts[3].isEmpty())
				book.setDueDate(null);
			else 
				book.setDueDate(LocalDate.parse(parts[3].trim()));
			return book;
		}
		
		// Modify this method as necessary to convert an item instance to a line of text in the file
		private static String convertItemToLine(Book book) {
			return String.format("%s\t%s\t%s\t%s", book.getBookTitle(),book.getBookAuthor(), book.getBookStatus(),book.getDueDate());
		}
		
		public static ArrayList<Book> readFile() throws ParseException {
			ArrayList<Book> items = new ArrayList<>();
			
			try (
				// Open/prepare the resources in the try resources block
				FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
				Scanner fileScanner = new Scanner(fileInputStream)
			    ) {
				// loop until the end of the file, reads each line as a string and passed on to 
				// convertlinetoitem method and the result is stored in ArrayList and is returned to the caller
				while (fileScanner.hasNextLine()) {
					String line = fileScanner.nextLine();
					items.add( convertLineToItem(line) );
				}
				
			} catch (FileNotFoundException ex) {				
				return items;
			} catch (IOException e) {
				System.err.println("Something unexpected happended.");
				e.printStackTrace();
			}
			
			return items;
		}
		
		public static void appendLine(Book item) {
			// convert book object to a string line of text to be written to the file
			String line = convertItemToLine(item);
			try (
				// The `true` here tells the FileOutputStream to append to the file rather than overwriting it
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME, true);
				PrintWriter fileWriter = new PrintWriter(fileOutputStream);
			) {
				fileWriter.println(line);
			} catch (IOException e) {
				System.err.println("Something unexpected happended.");
				e.printStackTrace();
			}
		}
		
		public static void writeFile(List<Book> items) {
			try (
				// The `false` here tells the FileOutputStream to overwrite the file, rather than append to it
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME, false);
				PrintWriter fileWriter = new PrintWriter(fileOutputStream);
			) {
				for (Book item : items) {
					String line = convertItemToLine(item);
					fileWriter.println(line);
				}
			} catch (IOException e) {
				System.err.println("Something unexpected happended.");
				e.printStackTrace();
			}
		}
		
		
		public static void createDirectory(String pathName) {
			Path path = Paths.get(pathName);
			if (Files.notExists(path)) {
				try {
					Files.createDirectories(path);
					System.out.println("Directory created at " + path.toAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		public static void createBlankFile(String pathName) {
			Path path = Paths.get(pathName);
			if (Files.notExists(path)) {
				try {
					Files.createFile(path);
					System.out.println("File created at " + path.toAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}

	}
	

