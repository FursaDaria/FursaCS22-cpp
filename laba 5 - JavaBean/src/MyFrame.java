import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame {
    private JPanel panel1;
    private JButton addButton;
    private JButton delButton;
    private JTable dataTable;
    private DataSheetTableModel tableModel;

    public MyFrame() {
        dataTable.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                dataTable.setModel(tableModel = new DataSheetTableModel());

            }
        });
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.setRowCount(tableModel.getRowCount() + 1);
                tableModel.getDataSheet().addData(tableModel.getDataSheet().createNewElement());
                dataTable.revalidate();
                tableModel.fireDataSheetChange();
            }
        });
        delButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tableModel.getDataSheet().removeData(tableModel.getRowCount() - 1);
                tableModel.setRowCount(tableModel.getRowCount() - 1);
                dataTable.revalidate();
                tableModel.fireDataSheetChange();
            }
        });
    }

//    public static void main(String[] args) {
//        JFrame window = new JFrame();
//        MyFrame form = new MyFrame();
//        window.setContentPane(form.panel1);
//        window.setTitle("JavaBean");
//        window.setSize(500, 600);
//        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        window.setVisible(true);
//        form.setTableModel(new DataSheetTableModel());
//    }

    public void setTableModel(DataSheetTableModel tableModel) {
        dataTable.setModel(this.tableModel = tableModel);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
        panel1.add(panel2, BorderLayout.SOUTH);
        addButton = new JButton();
        addButton.setText("Add (+)");
        panel2.add(addButton);
        delButton = new JButton();
        delButton.setText("Del (-)");
        panel2.add(delButton);
        final JScrollPane scrollPane1 = new JScrollPane();
        panel1.add(scrollPane1, BorderLayout.CENTER);
        dataTable = new JTable();
        scrollPane1.setViewportView(dataTable);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }
}
