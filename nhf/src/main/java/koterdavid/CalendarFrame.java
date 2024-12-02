package koterdavid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Calendar;
import java.util.Date;

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
     * Table displaying CalendarEntity-es
     * @param calendarData The CalendarEntity list to display as a table
     *                          and add CalendarEntities like Events and tasks to.
     */
    private void northHalf(CalendarData calendarData){
        JPanel pnlNorthHalf = new JPanel();
        this.add(pnlNorthHalf, BorderLayout.NORTH);
        JTable tblCalendarTable = new JTable(calendarData);
        JScrollPane scrCalendarTableScroll = new JScrollPane(tblCalendarTable);
        pnlNorthHalf.add(scrCalendarTableScroll, BorderLayout.CENTER);
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
            tblCalendarTable.getColumnModel().getColumn(3).setPreferredWidth(20);
            tblCalendarTable.getColumnModel().getColumn(4).setPreferredWidth(100);
            tblCalendarTable.getColumnModel().getColumn(5).setPreferredWidth(100);
            tblCalendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        }


    }

    /**
     * Form to add Event or Task
     * @param calendarData The CalendarEntity list to add CalendarEntities like Events and Tasks to.
     */
    public void southHalf(CalendarData calendarData){
        JPanel pnlSouthHalf = new JPanel();
        this.add(pnlSouthHalf, BorderLayout.SOUTH);

        //Declaration of variables needed by the forms:
        {
            Date eventBeginDate;
            Date eventEndDate;
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
        ActionListenerClearNewEventForm actionListenerClearNewEventForm = new ActionListenerClearNewEventForm();

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
        ActionListenerAddNewEvent actionListenerAddNewEvent = new ActionListenerAddNewEvent();

        //Event add form:
        {
            JPanel pnlEventForm = new JPanel();
            this.add(pnlEventForm, BorderLayout.WEST);
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
            //TODO: Date selector + deselector button
            JLabel lblEventEnd = new JLabel("Event end date (optional): ");
            pnlEventForm.add(lblEventEnd);
            DateTextField eventEndDateTextField = new DateTextField();
            pnlEventForm.add(eventEndDateTextField);
            //TODO: Date selector + deselector button
            JButton btnEventAdd = new JButton("Add event");
            pnlEventForm.add(btnEventAdd);
            btnEventAdd.addActionListener(actionListenerAddNewEvent);
            JButton btnClearNewEventForm = new JButton("Clear form");
            pnlEventForm.add(btnClearNewEventForm);
            btnClearNewEventForm.addActionListener(actionListenerClearNewEventForm);
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
        ActionListenerAddNewTask actionListenerAddNewTask = new ActionListenerAddNewTask();

        //Task add form:
        {
            JPanel pnlTaskForm = new JPanel();
            this.add(pnlTaskForm, BorderLayout.EAST);
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
            JLabel lblTaskDeadline = new JLabel("Task deadline (optional): ");
            pnlTaskForm.add(lblTaskDeadline);
            //TODO: Date selector + deselector button
            JLabel lblTaskDescription = new JLabel("Task description: ");
            pnlTaskForm.add(lblTaskDescription);
            JTextArea txaTaskDescription = new JTextArea(10, 50);
            pnlTaskForm.add(txaTaskDescription);
            JButton btnTaskAdd = new JButton("Add task");
            pnlTaskForm.add(btnTaskAdd);
            btnTaskAdd.addActionListener(actionListenerAddNewTask);
            JButton btnClearNewTaskForm = new JButton("Clear form");
            pnlTaskForm.add(btnClearNewTaskForm);
            btnClearNewTaskForm.addActionListener(actionListenerClearNewTaskForm);
        }
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
        setSize(1000, 800);
        setResizable(true);

        northHalf(calendarData);
        southHalf(calendarData);
        menuBar(calendarData, viewFilter);


        this.pack();
    }
}
