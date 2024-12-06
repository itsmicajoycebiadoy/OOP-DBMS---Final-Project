
package MainFiles;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class CustomRenderer extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        Component cell = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        
        String expirationStatus = (String) table.getValueAt(row, 7);

        // Set background color based on expiration status
        if ("Expired".equals(expirationStatus)) {
            cell.setBackground(Color.RED); // Red for expired products
        } else if ("Expiring Soon".equals(expirationStatus)) {
            cell.setBackground(Color.YELLOW); // Yellow for products expiring soon
        } else {
            cell.setBackground(Color.WHITE); // Default background for other products
        }

        return cell;
    }
}