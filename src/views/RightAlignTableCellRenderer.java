package views;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class RightAlignTableCellRenderer extends DefaultTableCellRenderer {
    public RightAlignTableCellRenderer() {
        setHorizontalAlignment(SwingConstants.RIGHT);
    }
}