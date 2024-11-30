package koterdavid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalendarFrame extends JFrame {
    JTextField txtName, txtLocation, txtType;
    JCheckBox chkAllDay;
    public CalendarFrame(CalendarData calendarData){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setName("SwingLab");
        setSize(800, 600);
        setResizable(true);

        JPanel pnlNorthHalf = new JPanel();
        this.add(pnlNorthHalf, BorderLayout.NORTH);
        JPanel pnlSouthHalf = new JPanel();
        this.add(pnlSouthHalf, BorderLayout.SOUTH);

        JTable tblCalendarTable = new JTable(calendarData);
        JScrollPane scrCalendarTableScroll = new JScrollPane(tblCalendarTable);
        pnlNorthHalf.add(scrCalendarTableScroll, BorderLayout.CENTER);

        JPanel pnlEventForm = new JPanel();
        JPanel pnlTaskForm = new JPanel();

        JMenuBar mnuCalendarMenuBar = new JMenuBar();
        this.setJMenuBar(mnuCalendarMenuBar);
        JMenu mnuCalendarFileMenu = new JMenu("File");
        mnuCalendarMenuBar.add(mnuCalendarFileMenu);
        JMenu mnuCalendarViewMenu = new JMenu("View");
        mnuCalendarMenuBar.add(mnuCalendarViewMenu);
        JCheckBoxMenuItem mniShowFutureOnlyInTableView = new JCheckBoxMenuItem("Show only current and future events");
        mniShowFutureOnlyInTableView.
        mnuCalendarViewMenu.add(mniShowFutureOnlyInTableView);
        JMenuItem mniSaveCalendarData = new JMenuItem("Save...");
        mnuCalendarFileMenu.add(mniSaveCalendarData);
        JMenuItem mniLoadCalendarData = new JMenuItem("Load...");
        mnuCalendarFileMenu.add(mniLoadCalendarData);
    }
    class ActionListenerCreateNewEvent implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }
    class ActionListenerCreateNewTask implements ActionListener{
        public void actionPerformed(ActionEvent e) {
        }
    }
}
