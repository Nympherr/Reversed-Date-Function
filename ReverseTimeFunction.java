
public class ReverseTimeFunction {
	
	private static final int MAX_YEAR_DIFFERENCE = 100000;
	
    public static void main(String[] args) {
    	printBonusDatesBetween(5,100000000);
    }
    
    public static void printBonusDatesBetween(int startingYear, int endingYear) {
    	
    	if(!checkIfValidDateInput(startingYear, endingYear)) {
    		System.exit(1);
    	}
    		
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
    
}
