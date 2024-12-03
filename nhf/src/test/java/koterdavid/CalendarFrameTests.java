package koterdavid;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.*;

class CalendarFrameTests {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }

    @org.junit.jupiter.api.Test
    void CalendarFrame() {
        CalendarData calendarData = new CalendarData();
        assertNotNull(calendarData, "The CalendarData should be initialized and not be null");
        CalendarFrame calendarFrame = new CalendarFrame(calendarData);
        assertNotNull(calendarFrame, "The CalendarFrame should be initialized and not be null");
    }

    @org.junit.jupiter.api.Test
    void pnlSouthHalf() {
    }

    @org.junit.jupiter.api.Test
    void pnlEventForm_NotNullAfterInit() {
        CalendarData calendarData = new CalendarData();
        CalendarFrame calendarFrame = new CalendarFrame(calendarData);


        assertNotNull(calendarFrame.pnlEventForm(), "The panel should be initialized and not be null");
    }

    @org.junit.jupiter.api.Test
    void pnlEventForm_FieldsNotNullAfterInit() {
        CalendarData calendarData = new CalendarData();
        CalendarFrame calendarFrame = new CalendarFrame(calendarData);


        assertNotNull(calendarFrame.txtEventName, "Event Name TextField should be initialized");
        assertNotNull(calendarFrame.txtEventLocation, "Event Location TextField should be initialized");
        assertNotNull(calendarFrame.txtEventType, "Event Type TextField should be initialized");
        assertNotNull(calendarFrame.eventBeginDateTextField, "Event Begin Date TextField should be initialized");
        assertNotNull(calendarFrame.tglVisibilityOfEventEndDateTextField, "Visibility Toggle for Event End Date TextField should be initialized");
        assertNotNull(calendarFrame.eventEndDateTextField, "Event End Date TextField should be initialized");
        assertNotNull(calendarFrame.btnEventAdd, "Add Event Button should be initialized");
        assertNotNull(calendarFrame.btnClearNewEventForm, "Clear Event Form Button should be initialized");
    }

    @org.junit.jupiter.api.Test
    void pnlTaskForm_NotNullAfterInit() {
        CalendarData calendarData = new CalendarData();
        CalendarFrame calendarFrame = new CalendarFrame(calendarData);

        assertNotNull(calendarFrame.pnlTaskForm(), "The panel should be initialized and not be null");
    }

    @org.junit.jupiter.api.Test
    void pnlTaskForm_FieldsNotNullAfterInit() {
        CalendarData calendarData = new CalendarData();
        CalendarFrame calendarFrame = new CalendarFrame(calendarData);

        assertNotNull(calendarFrame.txtTaskName, "Task Name TextField should be initialized");
        assertNotNull(calendarFrame.txtTaskLocation, "Task Location TextField should be initialized");
        assertNotNull(calendarFrame.txtTaskPriority, "Task Priority TextField should be initialized");
        assertNotNull(calendarFrame.tglVisibilityOfTaskDeadLineDateTextField, "Task Set Deadline button should be initialized");
        assertNotNull(calendarFrame.taskDeadlineDateTextField,"Task Deadline Date TextField should be initialized");
        assertNotNull(calendarFrame.txtTaskDescription, "Task Description TextField should be initialized");
        assertNotNull(calendarFrame.btnTaskAdd, "Add Task Button should be initialized");
        assertNotNull(calendarFrame.btnClearNewTaskForm, "Clear Task Form Button should be initialized");
        
    }

    /**
     * The first created event should have a correct end Date,
     * the second created event's end Date should be null.
     *
     */
    @org.junit.jupiter.api.Test
    void addEventThroughForm_CheckCalendarDataForNewEvent() throws ParseException {
        CalendarData calendarData = new CalendarData();
        CalendarFrame calendarFrame = new CalendarFrame(calendarData);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        calendarFrame.txtEventName.setText("First Test Event");
        calendarFrame.txtEventLocation.setText("First Test Location");
        calendarFrame.txtEventType.setText("First Test Type");
        calendarFrame.eventBeginDateTextField.setDate(simpleDateFormat.parse("2001-01-01") );
        calendarFrame.tglVisibilityOfEventEndDateTextField.setSelected(true);
        calendarFrame.eventEndDateTextField.setDate(simpleDateFormat.parse("2002-02-02"));
        calendarFrame.btnEventAdd.doClick();

        calendarFrame.txtEventName.setText("Second Test Event");
        calendarFrame.txtEventLocation.setText("Second Test Location");
        calendarFrame.txtEventType.setText("Second Test Type");
        calendarFrame.eventBeginDateTextField.setDate(simpleDateFormat.parse("2003-03-03") );
        calendarFrame.tglVisibilityOfEventEndDateTextField.doClick();
        calendarFrame.btnEventAdd.doClick();

        assertInstanceOf(Event.class, calendarData.get(0), "The first calendar entity should be an instance of Event");
        assertEquals("First Test Event", calendarData.get(0).getName(), "Event name not saved correctly");
        assertEquals("First Test Location", calendarData.get(0).getLocation(), "Event location not saved correctly");
        assertEquals("First Test Type", ((Event)calendarData.get(0)).getType(), "Event type not saved correctly");
        assertEquals(simpleDateFormat.parse("2001-01-01"), ((Event)calendarData.get(0)).getBegin(), "Event begin date not saved correctly");
        assertEquals(simpleDateFormat.parse("2002-02-02"), ((Event)calendarData.get(0)).getEnd(), "Event end date not saved correctly");

        assertInstanceOf(Event.class, calendarData.get(1), "The second calendar entity should be an instance of Event");
        assertEquals("Second Test Event", calendarData.get(1).getName(), "Event name not saved correctly");
        assertEquals("Second Test Location", calendarData.get(1).getLocation(), "Event location not saved correctly");
        assertEquals("Second Test Type", ((Event)calendarData.get(1)).getType(), "Event type not saved correctly");
        assertEquals(simpleDateFormat.parse("2003-03-03"), ((Event)calendarData.get(1)).getBegin(), "Event begin date not saved correctly");
        assertNull(((Event)calendarData.get(1)).getEnd(), "Event end Date should be null when not set");
    }

    /**
     * The first created task should have the correct Date as deadline,
     * the second created task's deadline should be null
     */
    @org.junit.jupiter.api.Test
    void addTaskThroughForm_CheckCalendarDataForNewTask() throws ParseException {
        CalendarData calendarData = new CalendarData();
        CalendarFrame calendarFrame = new CalendarFrame(calendarData);

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

        calendarFrame.txtTaskName.setText("First Test Task");
        calendarFrame.txtTaskLocation.setText("First Test Location");
        calendarFrame.txtTaskPriority.setText("First Test Priority");
        calendarFrame.taskDeadlineDateTextField.setDate(simpleDateFormat.parse("2001-01-01") );
        calendarFrame.txtTaskDescription.setText("First Test Description");
        calendarFrame.btnTaskAdd.doClick();

        calendarFrame.txtTaskName.setText("Second Test Task");
        calendarFrame.txtTaskLocation.setText("Second Test Location");
        calendarFrame.txtTaskPriority.setText("Second Test Priority");
        calendarFrame.tglVisibilityOfTaskDeadLineDateTextField.doClick();
        calendarFrame.taskDeadlineDateTextField.setDate(simpleDateFormat.parse("2002-02-02"));
        calendarFrame.txtTaskDescription.setText("Second Test Description");
        calendarFrame.btnTaskAdd.doClick();

        assertInstanceOf(Task.class, calendarData.get(0), "The first calendar entity should be an instance of Task");
        assertEquals("First Test Task", calendarData.get(0).getName(), "Task name not saved correctly");
        assertEquals("First Test Location", calendarData.get(0).getLocation(), "Task location not saved correctly");
        assertEquals("First Test Priority", ((Task)calendarData.get(0)).getPriority(), "Task priority not saved correctly");
        assertEquals(simpleDateFormat.parse("2001-01-01"), ((Task)calendarData.get(0)).getDeadline(), "Task deadline not saved correctly");
        assertEquals("First Test Description", ((Task)calendarData.get(0)).getDescription(), "Task description not saved correctly");

        assertInstanceOf(Task.class, calendarData.get(1), "The second calendar entity should be an instance of Task");
        assertEquals("Second Test Task", calendarData.get(1).getName(), "Task name not saved correctly");
        assertEquals("Second Test Location", calendarData.get(1).getLocation(), "Task location not saved correctly");
        assertEquals("Second Test Priority", ((Task)calendarData.get(1)).getPriority(), "Task priority not saved correctly");
        assertNull(((Task)calendarData.get(1)).getDeadline(), "Task Deadline should be null when not set");
        assertEquals("Second Test Description", ((Task)calendarData.get(1)).getDescription(), "Task description not saved correctly");
    }
}
