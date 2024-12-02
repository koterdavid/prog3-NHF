package koterdavid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

/**
 * Main application window, consisting of the table view and the forms to add Event and Task
 */
public class CalendarFrame extends JFrame {
    CalendarData calendarData;
    /*
     * Declarations of needed form input fields for accessing their data
     * in the data processing methods:
     */
        // Input fields in Event form:
        JTextField txtEventName;
        JTextField txtEventLocation;
        JTextField txtEventType;
        DateTextField eventBeginDateTextField;
        JToggleButton tglVisibilityOfEventEndDateTextField;
        DateTextField eventEndDateTextField;
        // Buttons in Event form (to access their ActionListeners):
        JButton btnEventAdd;
        JButton btnClearNewEventForm;

        // Input fields in Task form:
        JTextField txtTaskName;
        JTextField txtTaskLocation;
        JTextField txtTaskPriority;
        JToggleButton tglVisibilityOfTaskDeadLineDateTextField;
        DateTextField taskDeadlineDateTextField;
        JTextField txtTaskDescription;
        // Buttons in Task form (to access their ActionListeners):
        JButton btnTaskAdd;
        JButton btnClearNewTaskForm;


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
        this.calendarData=calendarData;
        this.setLayout(new FlowLayout(FlowLayout.CENTER));
        this.add(pnlNorthHalf());
        this.add(pnlSouthHalf());
        menuBar(viewFilter);
        this.pack();
    }

    /**
     * Table displaying CalendarEntities in a Table
     *
     * @return The JPanel containing the table
     */
    private JPanel pnlNorthHalf(){
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
            tblCalendarTable.getColumnModel().getColumn(2).setMinWidth(10);
            tblCalendarTable.getColumnModel().getColumn(3).setMinWidth(10);
            tblCalendarTable.getColumnModel().getColumn(4).setMinWidth(40);
            tblCalendarTable.getColumnModel().getColumn(5).setMinWidth(40);            tblCalendarTable.getColumnModel().getColumn(0).setMinWidth(30);
            tblCalendarTable.getColumnModel().getColumn(0).setPreferredWidth(100);
            tblCalendarTable.getColumnModel().getColumn(1).setPreferredWidth(100);
            tblCalendarTable.getColumnModel().getColumn(2).setPreferredWidth(20);
            tblCalendarTable.getColumnModel().getColumn(3).setPreferredWidth(40);
            tblCalendarTable.getColumnModel().getColumn(4).setPreferredWidth(120);
            tblCalendarTable.getColumnModel().getColumn(5).setPreferredWidth(120);
            tblCalendarTable.setAutoResizeMode(JTable.AUTO_RESIZE_LAST_COLUMN);
        }

        return pnlNorthHalf;
    }

    /**
     * Form to add Event or Task
     *
     * @return The JPanel containing the event form on the left side and the task form on the right side
     */
    public JPanel pnlSouthHalf(){
        JPanel pnlSouthHalf = new JPanel(); //Form to build and then return it
        pnlSouthHalf.setLayout(new GridLayout(0,2));
        this.add(pnlSouthHalf);

        pnlSouthHalf.add(pnlEventForm());
        pnlSouthHalf.add(pnlTaskForm());
        return pnlSouthHalf;
    }

    /**
     * Clear the contents of the New Event form
     * on press of the Clear form button or when
     * called by the Add event handler after a successful
     * operation
     */
    class ActionListenerClearEventForm implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            txtEventName.setText("");
            txtEventLocation.setText("");
            txtEventType.setText("");
            eventBeginDateTextField.setDate(new Date());
            tglVisibilityOfEventEndDateTextField.setSelected(true);
            //Then invoke the ActionListeners to do their thing instead of manual intervention:
            for(ActionListener a: tglVisibilityOfEventEndDateTextField.getActionListeners()){
                a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
            eventEndDateTextField.setDate(new Date());
            calendarData.fireTableDataChanged(); //Notify Table about data change
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
            Event event = new Event(
                    txtEventName.getText(),
                    txtEventLocation.getText(),
                    txtEventType.getText(),
                    false, //TODO
                    eventBeginDateTextField.getDate(),
                    eventEndDateTextField.isVisible() ? eventEndDateTextField.getDate() : null
            );
            calendarData.add(event);
            //Clear form through the ActionListener of the Clear Form button:
            for(ActionListener a: btnClearNewEventForm.getActionListeners()){
                a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
        }
    }

    /**
     * Form to add new event
     */
    public JPanel pnlEventForm(){
        JPanel pnlEventForm = new JPanel(); //Form to build and then return it
        pnlEventForm.setLayout(new GridLayout(0,2)); //Organize fields and their labels vertically
        JLabel lblEventName = new JLabel("Event name: ");
        pnlEventForm.add(lblEventName);
        /*JTextField*/ txtEventName = new JTextField(20);
        pnlEventForm.add(txtEventName);
        JLabel lblEventLocation = new JLabel("Event location: ");
        pnlEventForm.add(lblEventLocation);
        /*JTextField*/ txtEventLocation = new JTextField(20);
        pnlEventForm.add(txtEventLocation);
        JLabel lblEventType = new JLabel("Event type: ");
        pnlEventForm.add(lblEventType);
        /*JTextField*/ txtEventType = new JTextField(20);
        pnlEventForm.add(txtEventType);
        JLabel lblEventBegin = new JLabel("Event begin date (required): ");
        pnlEventForm.add(lblEventBegin);
        /*DateTextField*/ eventBeginDateTextField = new DateTextField();
        pnlEventForm.add(eventBeginDateTextField);
        { //Event end date toggle and selector
            // If eventEndDateTextField is visible, it is considered set.
            // The following Toggle Button toggles its visibility in the form.
            /*JToggleButton*/ tglVisibilityOfEventEndDateTextField = new JToggleButton("Event end date (optional): ", true);
            pnlEventForm.add(tglVisibilityOfEventEndDateTextField, true);
            /*DateTextField*/ eventEndDateTextField = new DateTextField();
            pnlEventForm.add(eventEndDateTextField);
            tglVisibilityOfEventEndDateTextField.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        //if button is selected, show it, if not selected, don't show it:
                        eventEndDateTextField.setVisible( tglVisibilityOfEventEndDateTextField.isSelected() );
                    }
                }
            );
        }
        /*JButton*/ btnEventAdd = new JButton("Add event");
        pnlEventForm.add(btnEventAdd);
        ActionListenerAddNewEvent actionListenerAddNewEvent = new ActionListenerAddNewEvent();
        btnEventAdd.addActionListener(actionListenerAddNewEvent);
        /*JButton*/ btnClearNewEventForm = new JButton("Clear event form");
        pnlEventForm.add(btnClearNewEventForm);
        ActionListenerClearEventForm actionListenerClearEventForm = new ActionListenerClearEventForm();
        btnClearNewEventForm.addActionListener(actionListenerClearEventForm);
        return pnlEventForm;
    }

    /**
     * Clear the contents of the New Task form
     * on press of the Clear form button or when
     * called by the Add task handler after a successful
     * operation
     */
    class ActionListenerClearTaskForm implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            txtTaskName.setText("");
            txtTaskLocation.setText("");
            txtTaskPriority.setText("");
            tglVisibilityOfTaskDeadLineDateTextField.setSelected(true);
            //Then invoke the ActionListeners to do their thing instead of manual intervention:
            for(ActionListener a: tglVisibilityOfTaskDeadLineDateTextField.getActionListeners()){
                a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
            taskDeadlineDateTextField.setDate(new Date());
            txtTaskDescription.setText("");
        }
    }


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
            Task task = new Task(
                    txtTaskName.getText(),
                    txtTaskLocation.getText(),
                    txtTaskPriority.getText(),
                    taskDeadlineDateTextField.isVisible() ? taskDeadlineDateTextField.getDate() : null,
                    txtTaskDescription.getText()
            );
            calendarData.add(task);
            //Clear form through the ActionListener of the Clear Form button:
            for(ActionListener a: btnClearNewTaskForm.getActionListeners()){
                a.actionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
            }
            calendarData.fireTableDataChanged(); //Notify Table about data change
        }
    }

    /**
     * Form to add new task.
     */
    public JPanel pnlTaskForm(){
        JPanel pnlTaskForm = new JPanel(); //Form to build and then return it
        pnlTaskForm.setLayout(new GridLayout(0,2)); //Organize fields and their labels vertically
        JLabel lblTaskName = new JLabel("Task name: ");
        pnlTaskForm.add(lblTaskName);
        /*JTextField*/ txtTaskName = new JTextField(20);
        pnlTaskForm.add(txtTaskName);
        JLabel lblTaskLocation = new JLabel("Task location: ");
        pnlTaskForm.add(lblTaskLocation);
        /*JTextField*/ txtTaskLocation = new JTextField(20);
        pnlTaskForm.add(txtTaskLocation);
        JLabel lblTaskPriority = new JLabel("Task priority: ");
        pnlTaskForm.add(lblTaskPriority);
        /*JTextField*/ txtTaskPriority = new JTextField(10);
        pnlTaskForm.add(txtTaskPriority);
        { //Task Deadline toggle and selector
            // If taskDeadlineDateTextField is visible, it is considered set.
            // The following Toggle Button toggles its visibility in the form.
            /*JToggleButton*/ tglVisibilityOfTaskDeadLineDateTextField = new JToggleButton("Task has deadline", true);
            pnlTaskForm.add(tglVisibilityOfTaskDeadLineDateTextField);
            /*DateTextField*/ taskDeadlineDateTextField = new DateTextField();
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
        /*JTextField*/ txtTaskDescription = new JTextField(20);
        pnlTaskForm.add(txtTaskDescription);
        /*JButton*/ btnTaskAdd = new JButton("Add task");
        pnlTaskForm.add(btnTaskAdd);
        ActionListenerAddNewTask actionListenerAddNewTask = new ActionListenerAddNewTask();
        btnTaskAdd.addActionListener(actionListenerAddNewTask);
        /*JButton*/ btnClearNewTaskForm = new JButton("Clear task form");
        pnlTaskForm.add(btnClearNewTaskForm);
        ActionListenerClearTaskForm actionListenerClearTaskForm = new ActionListenerClearTaskForm();
        btnClearNewTaskForm.addActionListener(actionListenerClearTaskForm);
        return pnlTaskForm;
    }

    /**
     * Serializes the whole calendarData into a file
     */
    class ActionListenerMenuBarSave implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try {
                ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Calendar-data-file.dat"));
                oos.writeObject(calendarData.getCalendarEntities());
                oos.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    class ActionListenerMenuBarLoad implements ActionListener{
        public void actionPerformed(ActionEvent e) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream("Calendar-data-file.dat"));
                ArrayList<CalendarEntity> list = (ArrayList<CalendarEntity>) ois.readObject();
                calendarData.setCalendarEntities(list);
                ois.close();
                calendarData.fireTableDataChanged(); //Notify Table about data change
            } catch(FileNotFoundException ex){
                //TODO: some kind of file not found notification
            } catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }

    /**
     * @param viewFilter {@link CalendarFrame#viewFilter}
     */
    public void menuBar(boolean viewFilter){

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
        mniSaveCalendarData.addActionListener(new ActionListenerMenuBarSave());
        JMenuItem mniLoadCalendarData = new JMenuItem("Load...");
        mnuCalendarFileMenu.add(mniLoadCalendarData);
        mniLoadCalendarData.addActionListener(new ActionListenerMenuBarLoad());
    }
}
