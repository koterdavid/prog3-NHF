package koterdavid;

/**
 * Invokes CalendarFrame and provides it with an empty CalendarData,
 * does no real data handling
 */
public class Main {
    public static void main(String[] args) {
        CalendarData calendarData = new CalendarData();
        CalendarFrame window = new CalendarFrame(calendarData);
        window.setVisible(true);
    }
}