import java.io.File;
import java.time.LocalDate;
import java.util.Scanner;

/*
    Shout-out to my mom for the idea!
    If you want to do something cute for your programming girlfriend, feel free to use this!
    All I ask is that if you modify it, let me know if you do anything cool... There's always next year's gift

    Author: Aren Ashlock
    Date Published: November 16th, 2023
        (Updated: N/A)
 */

public class run {
    // THE DAY THAT AVA AND AREN SEE EACH OTHER AGAIN!!!
    private static final LocalDate seeArenDate = LocalDate.parse("2023-12-13");

    // Get the date of the system
    public static LocalDate getLocalDate() {
        return LocalDate.now();
    }

    public static void printAdventMessage(LocalDate givenDate) {
        // Don't have messages for anything before 11-16-2023 or after 12-13-2023
        // November 16th = 320th day of the year
        // December 13th = 347th day of the year
        // The year is intended for 2023
        if((givenDate.getDayOfYear() < 320) || (givenDate.getDayOfYear() > 347) || (givenDate.getYear() != 2023)) {
            System.out.println("Sorry, but I don't have a sweet message for you :(");
        }

        // Prevent the impatient girlfriend from cheating the advent calendar
        else if(givenDate.getDayOfYear() > getLocalDate().getDayOfYear()) {
            System.out.println("You've got to be patient fun size! Don't rush through life too quickly...");
        }

        else {
            String givenDateStr = "Here is the message for " + givenDate.getMonth() + " " + givenDate.getDayOfMonth() + ", " + givenDate.getYear() + ":";
            System.out.println(givenDateStr);

            try {
                File messages = new File("messages.txt");
                Scanner messagesScanner = new Scanner(messages);

                while(messagesScanner.hasNextLine()) {
                    // Get the date from the file
                    String fileDateStr = messagesScanner.nextLine().trim();
                    // Convert to LocalDate
                    LocalDate fileDate = LocalDate.parse(fileDateStr);

                    // Prepare the message regardless of whether it gets printed or not
                    String dailyMessage = messagesScanner.nextLine();
                    String signature = messagesScanner.nextLine();

                    if(fileDate.equals(givenDate)) {
                        System.out.println(dailyMessage);
                        System.out.println(signature);
                        break;
                    }
                }

                messagesScanner.close();
            }
            catch (Exception error) {
                System.out.println("Something is wrong with the file! Let Aren know please...");
                // For debugging the file (if needed). Otherwise, keep it commented out!
                //System.out.println(error);
            }

            printCountdown(givenDate);
        }
    }

    // Countdown of the days until I see the love of my life for winter break 2023
    public static void printCountdown(LocalDate givenDate) {
        // Only print the countdown for the current day (personal choice)
        if(givenDate.getDayOfYear() == getLocalDate().getDayOfYear()) {
            int daysLeft = seeArenDate.getDayOfYear() - givenDate.getDayOfYear();

            System.out.println("\nDAYS LEFT UNTIL YOU SEE ME FOR WINTER BREAK, SWEETHEART: " + daysLeft);
        }
    }

    public static void main(String args[]) {
        // Greetings!
        System.out.println("\nEnjoy Aren's Programmed Advent Calendar! (2023)\n");

        if(args.length <= 1) {
            // Use the system's date
            if(args.length == 0) {
                LocalDate currentDate = getLocalDate();

                printAdventMessage(currentDate);
            }

            // Ava gave us a date (to reminisce)
            else {
                LocalDate userInputDate = LocalDate.parse(args[0]);

                printAdventMessage(userInputDate);
            }
        }

        else {
            System.out.println("Unable to run the program because you passed in too many arguments");
        }

        // Little note for Ava...
        System.out.println("\n(If you want to revisit old days, type in \"java run yyyy-mm-dd\")\n");
    }
}
