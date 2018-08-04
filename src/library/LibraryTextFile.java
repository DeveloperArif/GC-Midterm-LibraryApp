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
	import java.text.SimpleDateFormat;
	import java.time.LocalDate;

	public class LibraryTextFile {
		
		// The path to the file to use
		public static final String FILE_NAME = "book.txt";
		
		// Modify this method as necessary to convert a line of text from the file to a new item instance
		private static Book convertLineToItem(String line) throws ParseException {
			String[] parts = line.split("\t");
			//System.out.println(parts[0]+parts[1]+parts[2]+parts[3]);
	//		SimpleDateFormat date = new SimpleDateFormat("MM/dd/yyyy");
			LocalDate date = LocalDate.now();
			Book book = new Book();
			book.setBookTitle(parts[0].trim());
			book.setBookAuthor(parts[1].trim());
			//book.setBookStatus(Enum.valueOf(Status.class, parts[2]));
			book.setBookStatus(Status.valueOf(parts[2]));
			if(parts[3].equals("")|| parts[3].equals("null")|| parts[3].isEmpty())
				book.setDueDate(null);
			else 
				book.setDueDate(date.parse(parts[3].trim()));
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
				// loop until the end of the file
				while (fileScanner.hasNextLine()) {
					// read each line as a string
					String line = fileScanner.nextLine();
					// then convert it to an object
					items.add( convertLineToItem(line) );
				}
				
			} catch (FileNotFoundException ex) {
				// If the file doesn't exist, there just aren't any items.
				
				return items;
			} catch (IOException e) {
				// If something else crazy goes wrong, print out the error.
				System.err.println("Something unexpected happended.");
				e.printStackTrace();
			}
			
			// Finally return the array of items.
			return items;
		}
		
		public static void appendLine(Book item) {
			// convert player object to a string line of text to be written to the file
			String line = convertItemToLine(item);
			
			try (
				// The `true` here tells the FileOutputStream to append to the file rather than overwriting it
				FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME, true);
				PrintWriter fileWriter = new PrintWriter(fileOutputStream);
			) {
				// write to the file
				fileWriter.println(line);
				
			} catch (IOException e) {
				// If something else crazy goes wrong, print out the error.
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
				// write to the file
				for (Book item : items) {
					// each item must be converted to a string of text to write to the file
					String line = convertItemToLine(item);
					fileWriter.println(line);
				}
				
			} catch (IOException e) {
				// If something else crazy goes wrong, print out the error.
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
		
/*		public static List<Book> readFile_theOldPainfulWay() throws ParseException {
			List<Book> items = new ArrayList<>();
			
			FileInputStream fileInputStream = null;
			Scanner fileScanner = null;
			try {
				fileInputStream = new FileInputStream(FILE_NAME);
				fileScanner = new Scanner(fileInputStream);
				
				while (fileScanner.hasNextLine()) {
					String line = fileScanner.nextLine();
					items.add( convertLineToItem(line) );
				}
			
				return items;
			} catch (FileNotFoundException ex) {
				return items;
			} finally {
				// whether or not there is an exception, make sure to close the scanner.
				if (fileScanner != null) {
					fileScanner.close();
				}
				// whether or not there is an exception, make sure to close the input stream.
				if (fileInputStream != null) {
					try {
						fileInputStream.close();
					} catch (IOException e) {
						// annoyingly, even closing the stream can cause an error. We have to catch that too!
						System.err.println("Failed to close stream. :(");
						e.printStackTrace();
					}
				}
			}
		}*/

	}
	

