package koterdavid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Main application window, consisting of the table view and the forms to add Event and Task
 */
public class CalendarFrame extends JFrame {
    JTextField txtName, txtLocation, txtType;
    JCheckBox chkAllDay;
    /**
     * Variable to communicate the status
     * of the filtering option toggleable in the MenuBar from
     * the MenuBar to the JTable in the northHalf.
     */
    boolean viewFilter=false;

    /**
     * The main window of the application, split into
     * a form to add new data and a table to view and possibly edit data.
     * Also contains a MenuBar for some general options.
     * @param calendarData The CalendarEntity list to display as a table
     *                          and add CalendarEntities like Events and tasks to.
     */
    public CalendarFrame(CalendarData calendarData){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setName("Calendar - Prog3 NHF Koter Dávid");
        setSize(1200, 750);
        setResizable(true);
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(pnlNorthHalf(calendarData));
        this.add(pnlSouthHalf(calendarData));
        menuBar(calendarData, viewFilter);


        this.pack();
    }

    /**
     * Table displaying CalendarEntities in a Table
     *
     * @param calendarData The CalendarEntity list to display as a table
     *                     and add CalendarEntities like Events and tasks to.
     * @return The JPanel containing the table
     */
    private JPanel pnlNorthHalf(CalendarData calendarData){
        JPanel pnlNorthHalf = new JPanel(new GridLayout(1,0)); //Form to build and then return it
        pnlNorthHalf.setPreferredSize(new Dimension(900,700));
        JTable tblCalendarTable = new JTable(calendarData);
        JScrollPane scrCalendarTableScroll = new JScrollPane(tblCalendarTable);
        pnlNorthHalf.add(scrCalendarTableScroll);
        //Table formatting:
        {
            //Header formatting
            //Source: https://stackoverflow.com/questions/2343391/creating-multi-line-header-for-jtable
            // Align header text left:
            ((JLabel) tblCalendarTable.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.LEFT);
            // Set header height manually, due to having multi-line header text,
            // but the row height did not adjust automatically:
            tblCalendarTable.getTableHeader().setPreferredSize(
                    new Dimension(tblCalendarTable.getColumnModel().getTotalColumnWidth(), 60));

            //Column widths:
            //Source: https://stackoverflow.com/questions/953972/java-jtable-setting-column-width
            //Currently resizing does not seem to work as it should
            tblCalendarTable.getColumnModel().getColumn(0).setMinWidth(60);
            tblCalendarTable.getColumnModel().getColumn(1).setMinWidth(60);
            tblCalendarTable.getColumnModel().getColumn(2).setMinWidth(30);
            tblCalendarTable.getColumnModel().getColumn(3).setMinWidth(10);
            tblCalendarTable.getColumnModel().getColumn(4).setMinWidth(40);
            tblCalendarTable.getColumnModel().getColumn(5).setMinWidth(40);            tblCalendarTable.getColumnModel().getColumn(0).setMinWidth(30);
            tblCalendarTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblCalendarTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblCalendarTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            tblCalendarTable.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblCalendarTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblCalendarTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblCalendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        }

        return pnlNorthHalf;
    }

    /**
     * Form to add Event or Task
     * @param calendarData The CalendarEntity list to add CalendarEntities like Events and Tasks to.
     * @return The JPanel containing the event form on the left side and the task form on the right side
     */
    public JPanel pnlSouthHalf(CalendarData calendarData){
        JPanel pnlSouthHalf = new JPanel(); //Form to build and then return it
        pnlSouthHalf.setLayout(new GridLayout(0,2));
        this.add(pnlSouthHalf);

        pnlSouthHalf.add(pnlEventForm(calendarData));
        pnlSouthHalf.add(pnlTaskForm(calendarData));
        return pnlSouthHalf;
    }

    /**
     * Clear the contents of the New Event form
     * on press of the Clear form button or when
     * called by the Add event handler after a successful
     * operation
     */
    class ActionListenerClearNewEventForm implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        }
    }

    /**
     * Button event handler
     * <p>
     * When pressing the "Add event" form button,
     * this class ensures that the data is validated
     * and added to the calendarData list properly,
     * then clears the form inputs.
     * <p>
     * If the inputs are not valid,
     * does not clear the inputs.
     * Currently does nothing in this case,
     * could be extended to provide an error message.
     */
    class ActionListenerAddNewEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    /**
     * Form to add new event
     */
    public JPanel pnlEventForm(CalendarData calendarData){
        JPanel pnlEventForm = new JPanel(); //Form to build and then return it
        pnlEventForm.setLayout(new GridLayout(0,2)); //Organize fields and their labels vertically
        JLabel lblEventName = new JLabel("Event name: ");
        pnlEventForm.add(lblEventName);
        JTextField txtEventName = new JTextField(20);
        pnlEventForm.add(txtEventName);
        JLabel lblEventLocation = new JLabel("Event location: ");
        pnlEventForm.add(lblEventLocation);
        JTextField txtEventLocation = new JTextField(20);
        pnlEventForm.add(txtEventLocation);
        JLabel lblEventType = new JLabel("Event type: ");
        pnlEventForm.add(lblEventType);
        JTextField txtEventType = new JTextField(20);
        pnlEventForm.add(txtEventType);
        JLabel lblEventBegin = new JLabel("Event begin date (required): ");
        pnlEventForm.add(lblEventBegin);
        DateTextField eventBeginDateTextField = new DateTextField();
        pnlEventForm.add(eventBeginDateTextField);
        JLabel lblEventEnd = new JLabel("Event end date (optional): ");
        pnlEventForm.add(lblEventEnd);
        DateTextField eventEndDateTextField = new DateTextField();
        pnlEventForm.add(eventEndDateTextField);
        JButton btnEventAdd = new JButton("Add event");
        pnlEventForm.add(btnEventAdd);
        ActionListenerAddNewEvent actionListenerAddNewEvent = new ActionListenerAddNewEvent();
        btnEventAdd.addActionListener(actionListenerAddNewEvent);
        JButton btnClearNewEventForm = new JButton("Clear event form");
        pnlEventForm.add(btnClearNewEventForm);
        ActionListenerClearNewEventForm actionListenerClearNewEventForm = new ActionListenerClearNewEventForm();
        btnClearNewEventForm.addActionListener(actionListenerClearNewEventForm);
        return pnlEventForm;
    }

    /**
     * Clear the contents of the New Task form
     * on press of the Clear form button or when
     * called by the Add task handler after a successful
     * operation
     */
    class ActionListenerClearNewTaskForm implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        }
    }
    ActionListenerClearNewTaskForm actionListenerClearNewTaskForm = new ActionListenerClearNewTaskForm();

    /**
     * Button event handler
     * <p>
     * When pressing the "Add task" form button,
     * this class ensures that the data is validated
     * and added to the calendarData list properly,
     * then clears the form inputs.
     * <p>
     * If the inputs are not valid,
     * does not clear the inputs.
     * Currently does nothing in this case,
     * could be extended to provide an error message.
     */
    class ActionListenerAddNewTask implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        }
    }

    /**
     * Form to add new task.
     */
    public JPanel pnlTaskForm(CalendarData calendarData){
        JPanel pnlTaskForm = new JPanel(); //Form to build and then return it
        pnlTaskForm.setLayout(new GridLayout(0,2)); //Organize fields and their labels vertically
        JLabel lblTaskName = new JLabel("Task name: ");
        pnlTaskForm.add(lblTaskName);
        JTextField txtTaskField = new JTextField(20);
        pnlTaskForm.add(txtTaskField);
        JLabel lblTaskLocation = new JLabel("Task location: ");
        pnlTaskForm.add(lblTaskLocation);
        JTextField txtTaskLocation = new JTextField(20);
        pnlTaskForm.add(txtTaskLocation);
        JLabel lblTaskPriority = new JLabel("Task priority: ");
        pnlTaskForm.add(lblTaskPriority);
        JTextField txtTaskPriority = new JTextField(10);
        pnlTaskForm.add(txtTaskPriority);
        //JLabel lblTaskDeadline = new JLabel("Task deadline (optional): ");
        //pnlTaskForm.add(lblTaskDeadline);
        { //Task Deadline toggle and selector
            // If taskDeadlineDateTextField is visible, it is considered set.
            // The following Toggle Button toggles its visibility in the form.
            JToggleButton tglVisibilityOfTaskDeadLineDateTextField = new JToggleButton("Task has deadline", true);
            pnlTaskForm.add(tglVisibilityOfTaskDeadLineDateTextField);
            DateTextField taskDeadlineDateTextField = new DateTextField();
            pnlTaskForm.add(taskDeadlineDateTextField);
            tglVisibilityOfTaskDeadLineDateTextField.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //if button is selected, show it, if not selected, don't show it:
                    taskDeadlineDateTextField.setVisible( tglVisibilityOfTaskDeadLineDateTextField.isSelected() );
                }
            });
        }
        JLabel lblTaskDescription = new JLabel("Task description: ");
        pnlTaskForm.add(lblTaskDescription);
        //JTextArea makes layout sizing change overall, so it was replaced with JTextField.
        //Still, a larger text area would be ideal
        //JTextArea txaTaskDescription = new JTextArea(10, 50);
        //pnlTaskForm.add(txaTaskDescription);
        JTextField txtTaskDescription = new JTextField("20");
        pnlTaskForm.add(txtTaskDescription);
        JButton btnTaskAdd = new JButton("Add task");
        pnlTaskForm.add(btnTaskAdd);
        ActionListenerAddNewTask actionListenerAddNewTask = new ActionListenerAddNewTask();
        btnTaskAdd.addActionListener(actionListenerAddNewTask);
        JButton btnClearNewTaskForm = new JButton("Clear task form");
        pnlTaskForm.add(btnClearNewTaskForm);
        btnClearNewTaskForm.addActionListener(actionListenerClearNewTaskForm);
        return pnlTaskForm;
    }

    /**
     * @param calendarData The data to save and load
     * @param viewFilter {@link CalendarFrame#viewFilter}
     */
    public void menuBar(CalendarData calendarData, boolean viewFilter){

        JMenuBar mnuCalendarMenuBar = new JMenuBar();
        this.setJMenuBar(mnuCalendarMenuBar);
        JMenu mnuCalendarFileMenu = new JMenu("File");
        mnuCalendarMenuBar.add(mnuCalendarFileMenu);
        JMenu mnuCalendarViewMenu = new JMenu("View");
        mnuCalendarMenuBar.add(mnuCalendarViewMenu);
        JCheckBoxMenuItem mniShowFutureOnlyInTableView = new JCheckBoxMenuItem("Show only current and future events");
        mnuCalendarViewMenu.add(mniShowFutureOnlyInTableView);
        JMenuItem mniSaveCalendarData = new JMenuItem("Save...");
        mnuCalendarFileMenu.add(mniSaveCalendarData);
        JMenuItem mniLoadCalendarData = new JMenuItem("Load...");
        mnuCalendarFileMenu.add(mniLoadCalendarData);
    }
}
