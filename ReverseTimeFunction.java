import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ReverseTimeFunction {
	
    private static final int MAX_YEAR_DIFFERENCE = 100000;
	
    public static void main(String[] args) {
    	printBonusDatesBetween(5,2000);
    }
    
    public static void printBonusDatesBetween(int startingYear, int endingYear) {
    	
    	if(!checkIfValidDateInput(startingYear, endingYear)) {
    		System.exit(1);
    	}
    	
    	int choice = whereToDisplayResult();
    	
    	if(choice == 1) {
    		printDateToConsole(startingYear, endingYear);
    	}
    	else {
    		printDateToFile(startingYear, endingYear);
    	}
    }
    
    private static boolean isLeapYear(int year) {
    	
        if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
            return true;
        }
        else {
            return false;
        }
    }
    
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
    
    private static String formatDate(int year, int month, int day) {
    	
    	String formatMonth;
    	String formatDay;
    	
    	if(month > 9) {
    		formatMonth = Integer.toString(month);
    	}
    	else {
    		formatMonth = "0" + Integer.toString(month);
    	}
    	
    	if(day > 9) {
    		formatDay = Integer.toString(day);
    	}
    	else {
    		formatDay = "0" + Integer.toString(day);
    	}
    	
    	return year + "-" + formatMonth + "-" + formatDay;
    }
    
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
    
    private static void printDateToConsole(int startingYear, int endingYear) {
    	
    	int startYear = startingYear;
    	int endYear = endingYear;

    	while(startYear <= endYear) {
    		
    		for(int month = 1; month < 13; month++) {
    			
    			int daysInMonth = getDaysInMonth(startYear, month);
    			
    			for(int currentDay = 1; currentDay <= daysInMonth; currentDay++) {
    				String day = formatDate(startYear,month,currentDay);
    				if(checkIfReversedDateEqual(day)) {
    					System.out.println(day);
    				}
    				else {
    					continue;
    				}
    			}
    		}
    		startYear++;
    	}
    	System.out.println("\nAll results are diplayed!");
    }
    
    private static void printDateToFile(int startingYear, int endingYear) {
    	
        try (FileWriter fileWriter = new FileWriter("result.txt")) {
        	
            int startYear = startingYear;
            int endYear = endingYear;
            
            fileWriter.write("Dates between " + startYear + " and " + endYear);
            fileWriter.write("\n-------------------------------------------\n");
            fileWriter.write("(year-month-day)\n\n");

            while (startYear <= endYear) {
                for (int month = 1; month < 13; month++) {
                    int daysInMonth = getDaysInMonth(startYear, month);

                    for (int currentDay = 1; currentDay <= daysInMonth; currentDay++) {
                        String day = formatDate(startYear, month, currentDay);
                        if (checkIfReversedDateEqual(day)) {
                            fileWriter.write(day + System.lineSeparator());
                        }
                    }
                }
                startYear++;
            }
            System.out.println("Succesfully written!");
        }
        catch (IOException e) {
            System.err.println("An error occurred while writing to the file: " + e.getMessage());
        }
    }
    
    private static int whereToDisplayResult() {
    	
    	    Scanner scanner = new Scanner(System.in);

    	    int choice = 0;
    	    boolean validChoice = false;

    	    do {
    	        System.out.println("Where would you like to display the result?");
    	        System.out.println("1. Console");
    	        System.out.println("2. File");

    	        try {
    	            choice = Integer.parseInt(scanner.nextLine());

    	            if (choice == 1 || choice == 2) {
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

    	    return choice;
    }
    
}

