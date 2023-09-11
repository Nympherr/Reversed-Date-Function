import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

// Did not use date/time library on purpose
 
public class ReverseDateFunction {
	
    // Private constructor to prevent object creation
    private ReverseDateFunction() {}
	
	// MAX_YEAR_DIFFERENCE is used to give control to the developer
	private static final int MAX_YEAR_DIFFERENCE = 100000;
	
	// Program start
    public static void main(String[] args) {
    	printBonusDatesBetween(2000,2500);
    }
    
    /*
     * Method which displays all dates between
     * two given years that remain the same even
     * if numbers of the date are reversed
     */
    public static void printBonusDatesBetween(int startingYear, int endingYear) {
    	
    	if(!checkIfValidDateInput(startingYear, endingYear)) {
    		System.exit(1);
    	}
    	
    	int userChoice = whereToDisplayResult();
    	
    	if(userChoice == 1) {
    		printDateToConsole(startingYear, endingYear);
    	}
    	else {
    		printDateToFile(startingYear, endingYear);
    	}
    }
    
    // Checks if current year is leap year
    private static boolean isLeapYear(int year) {
    	
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }
        else {
            return false;
        }
    }
    
    // Returns how many days are in particular month
    private static int getDaysInMonth(int year, int month) {
    	
        switch (month) {
        	case 4:
        	case 6:
        	case 9:
        	case 11:
        		return 30;
        	case 2:
        		if (isLeapYear(year)) {
        			return 29;
        		}
        		else {
        			return 28;
        		}
        	default:
        		return 31;
        }
    }
    
    /*
     * Adds zeroes to months and days if in range 1-9
     * For example it will transform 2023-9-5 to 2023-09-05
     * Turns separate numbers into one formatted string 
     */
    private static String getFormattedDate(int year, int month, int day) {
    	
    	String formattedMonth;
    	String formattedDay;
    	
    	if(month > 9) {
    		formattedMonth = Integer.toString(month);
    	}
    	else {
    		formattedMonth = "0" + Integer.toString(month);
    	}
    	
    	if(day > 9) {
    		formattedDay = Integer.toString(day);
    	}
    	else {
    		formattedDay = "0" + Integer.toString(day);
    	}
    	
    	return year + "-" + formattedMonth + "-" + formattedDay;
    }
    
    /* 
     * Checks if reversed date is equal to original
     * For example it will reverse 7056-11-05 to 5011-65-07
     * (and will check their equality)
     */
    private static boolean checkIfReversedDateEqual(String date) {
    	
    	String originalDate = date.replaceAll("-", "");
    	StringBuilder reversedDate = new StringBuilder(originalDate).reverse();
    	
    	if(reversedDate.toString().equals(originalDate)) {
    		return true;
    	}
    	else {
    		return false;
    	}
    }
    
    // Checks if invalid input was given by the user
    private static boolean checkIfValidDateInput(int startingYear, int endingYear) {
    	
    	if(startingYear < 0 || endingYear < 0) {
    		System.out.println("Starting or ending year cannot be less than 0!");
    		System.out.println("Change parameters and try again");
    		return false;
    	}
    	
    	if((endingYear - startingYear) < 0) {
    		System.out.println("Starting year cannot be higher than ending year!");
    		System.out.println("Change parameters and try again");
    		return false;
    	}
    	if((endingYear - startingYear) > MAX_YEAR_DIFFERENCE) {
    		System.out.println("Year difference is too large!");
    		System.out.println("Change parameters and try again");
    		return false;
    	}
    	
    	return true;
    }
    
    // Prints result to the user's console
    private static void printDateToConsole(int startingYear, int endingYear) {
    	
    	int startYear = startingYear;
    	int endYear = endingYear;

    	while(startYear <= endYear) {
    		
    		for(int month = 1; month < 13; month++) {
    			
    			int daysInMonth = getDaysInMonth(startYear, month);
    			
    			for(int currentDay = 1; currentDay <= daysInMonth; currentDay++) {
    				
    				String day = getFormattedDate(startYear,month,currentDay);
    				if(checkIfReversedDateEqual(day)) {
    					System.out.println(day);
    				}
    				else {
    					continue; // Skipping when reversed date isn't equal to original
    				}
    			}
    		}
    		startYear++;
    	}
    	System.out.println("\nAll results are diplayed!");
    }
    
    // Writes result to result.txt which will appear in current project folder
    private static void printDateToFile(int startingYear, int endingYear) {
    	
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("result.txt");
            int startYear = startingYear;
            int endYear = endingYear;

            fileWriter.write("Dates between " + startYear + " and " + endYear);
            fileWriter.write("\n-------------------------------------------\n");
            fileWriter.write("(year-month-day)\n\n");

            while (startYear <= endYear) {
            	
                for (int month = 1; month < 13; month++) {
                	
                    int daysInMonth = getDaysInMonth(startYear, month);

                    for (int currentDay = 1; currentDay <= daysInMonth; currentDay++) {
                    	
                        String day = getFormattedDate(startYear, month, currentDay);
                        if (checkIfReversedDateEqual(day)) {
                            fileWriter.write(day + System.lineSeparator());
                        }
                        else {
                            continue; // Skipping when reversed date isn't equal to original
                        }
                    }
                }
                startYear++;
            }
            
            System.out.println("Successfully written!");
        }
        catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
        finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            }
            catch (IOException e) {
                System.err.println("Error closing FileWriter: " + e.getMessage());
            }
        }
    }
    
    /*
     * Gives user an option on where he wants results to be displayed
     * 1 - to the console
     * 2 - write to the file
     */
    private static int whereToDisplayResult() {
    	
    	    Scanner scanner = new Scanner(System.in);

    	    int userChoice = 0;
    	    boolean validChoice = false;

    	    do {
    	        System.out.println("Where would you like to display the result?");
    	        System.out.println("1. Console");
    	        System.out.println("2. File");

    	        try {
    	            userChoice = Integer.parseInt(scanner.nextLine());

    	            if (userChoice == 1 || userChoice == 2) {
    	                validChoice = true;
    	            }
    	            else {
    	                System.out.println("Invalid choice. Please enter 1 for Console or 2 for File.");
    	            }
    	        }
    	        catch (NumberFormatException e) {
    	            System.out.println("Invalid input. Please enter 1 for Console or 2 for File.");
    	        }

    	    } while (!validChoice);
    	    
    	    scanner.close();
    	    return userChoice;
    }
}
