package Task_Level_Intermediate;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.regex.Pattern;

public class InventoryManagementSystem extends JFrame {
    private DefaultTableModel tableModel;
    private JTable table;
    private JTextField nameField, quantityField, priceField, searchField;
    private JButton addButton, updateButton, deleteButton, clearButton, searchButton, sortButton, exportButton;
    private TableRowSorter<DefaultTableModel> sorter;

    public InventoryManagementSystem() {
        setTitle("Inventory Management System");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Table model and table
        tableModel = new DefaultTableModel(new Object[]{"Name", "Quantity", "Price"}, 0);
        table = new JTable(tableModel);
        sorter = new TableRowSorter<>(tableModel);
        table.setRowSorter(sorter);

        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(table);

        // Input fields
        nameField = new JTextField(20);
        quantityField = new JTextField(10);
        priceField = new JTextField(10);
        searchField = new JTextField(20);

        // Labels
        JLabel nameLabel = new JLabel("Name:");
        JLabel quantityLabel = new JLabel("Quantity:");
        JLabel priceLabel = new JLabel("Price:");
        JLabel searchLabel = new JLabel("Search:");

        // Buttons
        addButton = new JButton("Add Item");
        updateButton = new JButton("Update Item");
        deleteButton = new JButton("Delete Item");
        clearButton = new JButton("Clear Fields");
        searchButton = new JButton("Search");
        sortButton = new JButton("Sort");
        exportButton = new JButton("Export to CSV");

        // Button action listeners
        addButton.addActionListener(e -> addItem());
        updateButton.addActionListener(e -> updateItem());
        deleteButton.addActionListener(e -> deleteItem());
        clearButton.addActionListener(e -> clearFields());
        searchButton.addActionListener(e -> searchItems());
        sortButton.addActionListener(e -> sortItems());
        exportButton.addActionListener(e -> exportToCSV());

        // Layout using GroupLayout
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        // Create horizontal group
        GroupLayout.SequentialGroup hGroup = layout.createSequentialGroup()
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameLabel)
                .addComponent(quantityLabel)
                .addComponent(priceLabel)
                .addComponent(searchLabel)
                .addComponent(clearButton))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(nameField)
                .addComponent(quantityField)
                .addComponent(priceField)
                .addComponent(searchField)
                .addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
                .addGroup(layout.createSequentialGroup()
                    .addComponent(addButton)
                    .addComponent(updateButton)
                    .addComponent(deleteButton)
                    .addComponent(searchButton)
                    .addComponent(sortButton)
                    .addComponent(exportButton))
            );

        // Create vertical group
        GroupLayout.ParallelGroup vGroup = layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 300, GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(nameLabel)
                    .addComponent(nameField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(quantityLabel)
                    .addComponent(quantityField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(priceLabel)
                    .addComponent(priceField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(searchLabel)
                    .addComponent(searchField))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(addButton)
                    .addComponent(updateButton)
                    .addComponent(deleteButton)
                    .addComponent(searchButton)
                    .addComponent(sortButton)
                    .addComponent(exportButton))
                .addComponent(clearButton)
            );

        layout.setHorizontalGroup(hGroup);
        layout.setVerticalGroup(vGroup);
    }

    private void addItem() {
        String name = nameField.getText();
        int quantity;
        double price;

        try {
            quantity = Integer.parseInt(quantityField.getText());
            price = Double.parseDouble(priceField.getText());

            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a name.");
                return;
            }

            tableModel.addRow(new Object[]{name, quantity, price});
            clearFields();
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Please enter valid numbers for quantity and price.");
        }
    }

    private void updateItem() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow >= 0) {
            String name = nameField.getText();
            int quantity;
            double price;

            try {
                quantity = Integer.parseInt(quantityField.getText());
                price = Double.parseDouble(priceField.getText());

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(this, "Please enter a name.");
                    return;
                }

                tableModel.setValueAt(name, selectedRow, 0);
                tableModel.setValueAt(quantity, selectedRow, 1);
                tableModel.setValueAt(price, selectedRow, 2);

                clearFields();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter valid numbers for quantity and price.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to update.");
        }
    }

    private void deleteItem() {
        int selectedRow = table.getSelectedRow();

        if (selectedRow >= 0) {
            int confirmation = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete this item?", "Delete Item", JOptionPane.YES_NO_OPTION);
            if (confirmation == JOptionPane.YES_OPTION) {
                tableModel.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Please select an item to delete.");
        }
    }

    private void clearFields() {
        nameField.setText("");
        quantityField.setText("");
        priceField.setText("");
        searchField.setText("");
        table.clearSelection();
    }

    private void searchItems() {
        String searchTerm = searchField.getText();
        if (searchTerm.trim().length() > 0) {
            RowFilter<DefaultTableModel, Object> rowFilter = RowFilter.regexFilter("(?i)" + Pattern.quote(searchTerm));
            sorter.setRowFilter(rowFilter);
        } else {
            sorter.setRowFilter(null);
        }
    }

    private void sortItems() {
        String[] options = {"Name", "Quantity", "Price"};
        String choice = (String) JOptionPane.showInputDialog(this, "Select sorting criteria:", "Sort Items",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (choice != null) {
            int columnIndex;
            switch (choice) {
                case "Name":
                    columnIndex = 0;
                    break;
                case "Quantity":
                    columnIndex = 1;
                    break;
                case "Price":
                    columnIndex = 2;
                    break;
                default:
                    return;
            }

            sorter.setSortKeys(java.util.Collections.singletonList(new RowSorter.SortKey(columnIndex, SortOrder.ASCENDING)));
        }
    }

    private void exportToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv"))) {
            for (int i = 0; i < tableModel.getColumnCount(); i++) {
                writer.write(tableModel.getColumnName(i));
                if (i < tableModel.getColumnCount() - 1) writer.write(",");
            }
            writer.newLine();

            for (int i = 0; i < tableModel.getRowCount(); i++) {
                for (int j = 0; j < tableModel.getColumnCount(); j++) {
                    writer.write(tableModel.getValueAt(i, j).toString());
                    if (j < tableModel.getColumnCount() - 1) writer.write(",");
                }
                writer.newLine();
            }

            JOptionPane.showMessageDialog(this, "Data exported to inventory.csv");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error exporting data.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventoryManagementSystem().setVisible(true));
    }
}


