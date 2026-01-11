
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.BasicStroke;
import java.awt.Dimension;

public class StatCard extends JPanel {
    private JLabel titleLabel;
    private JLabel valueLabel;

    public StatCard(String title, String value, Color accentColor) {
        setLayout(new BorderLayout(Theme.PADDING_MEDIUM, Theme.PADDING_MEDIUM));
        setBackground(Theme.SURFACE);
        setBorder(Theme.createCardBorder());

        // Icon panel with colored circle
        JPanel iconPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                // Draw colored circle
                g2.setColor(new Color(accentColor.getRed(), accentColor.getGreen(), accentColor.getBlue(), 20));
                g2.fillOval(0, 0, 48, 48);

                g2.setColor(accentColor);
                g2.setStroke(new BasicStroke(2));
                g2.drawOval(0, 0, 48, 48);
            }

            @Override
            public Dimension getPreferredSize() {
                return new Dimension(48, 48);
            }
        };
        iconPanel.setOpaque(false);

        // Title and value panel
        JPanel textPanel = new JPanel();
        textPanel.setLayout(new BoxLayout(textPanel, BoxLayout.Y_AXIS));
        textPanel.setOpaque(false);

        titleLabel = new JLabel(title);
        titleLabel.setFont(Theme.FONT_SMALL);
        titleLabel.setForeground(Theme.TEXT_SECONDARY);
        titleLabel.setAlignmentX(LEFT_ALIGNMENT);

        valueLabel = new JLabel(value);
        valueLabel.setFont(Theme.FONT_HEADING);
        valueLabel.setForeground(Theme.TEXT_PRIMARY);
        valueLabel.setAlignmentX(LEFT_ALIGNMENT);

        textPanel.add(titleLabel);
        textPanel.add(Box.createVerticalStrut(4));
        textPanel.add(valueLabel);

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout(Theme.PADDING_MEDIUM, 0));
        topPanel.setOpaque(false);
        topPanel.add(iconPanel, BorderLayout.WEST);
        topPanel.add(textPanel, BorderLayout.CENTER);

        add(topPanel, BorderLayout.CENTER);
    }

    public void setValue(String value) {
        valueLabel.setText(value);
    }

    public void setValueColor(Color color) {
        valueLabel.setForeground(color);
    }

    public String getValue() {
        return valueLabel.getText();
    }

    public JLabel getValueLabel() {
        return valueLabel;
    }
}
