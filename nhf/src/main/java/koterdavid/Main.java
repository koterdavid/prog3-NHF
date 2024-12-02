package koterdavid;

/**
 * Invokes CalendarFrame, does no real data handling
 */
public class Main {
    public static void main(String[] args) {
        CalendarData calendarData = new CalendarData();
        CalendarFrame window = new CalendarFrame(calendarData);
        window.setVisible(true);
    }
}