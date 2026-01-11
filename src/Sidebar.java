
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.LinkedHashMap;
import java.util.Map;

public class Sidebar extends JPanel {
    private Map<String, JButton> menuButtons;
    private JButton logoutButton;
    private JButton selectedButton;

    public Sidebar() {
        this.menuButtons = new LinkedHashMap<>();
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setPreferredSize(new Dimension(240, 0));
        setBackground(Theme.SURFACE);
        setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Theme.SURFACE_DARK));

        // Header with gradient
        JPanel header = Theme.createGradientPanel(Theme.PRIMARY, Theme.PRIMARY_DARK);
        header.setLayout(new BorderLayout());
        header.setPreferredSize(new Dimension(240, 80));
        header.setMaximumSize(new Dimension(Integer.MAX_VALUE, 80));

        JLabel titleLabel = new JLabel("Finance Tracker");
        titleLabel.setFont(Theme.FONT_HEADING);
        titleLabel.setForeground(Theme.TEXT_ON_PRIMARY);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(Theme.PADDING_LARGE, Theme.PADDING_LARGE,
                Theme.PADDING_LARGE, Theme.PADDING_LARGE));
        header.add(titleLabel, BorderLayout.CENTER);

        add(header);
        add(Box.createVerticalStrut(Theme.PADDING_MEDIUM));
    }

    public void addMenuItem(String text, ActionListener listener) {
        JButton button = createMenuButton(text, listener);
        menuButtons.put(text, button);

        // Remove logout button if it exists
        if (logoutButton != null && logoutButton.getParent() != null) {
            remove(logoutButton);
            Component glue = null;
            for (Component c : getComponents()) {
                if (c instanceof Box.Filler) {
                    glue = c;
                    break;
                }
            }
            if (glue != null) {
                remove(glue);
            }
        }

        add(button);
    }

    public void addLogoutButton(ActionListener listener) {
        add(Box.createVerticalGlue());

        logoutButton = new JButton("Logout");
        logoutButton.setFont(Theme.FONT_BUTTON);
        logoutButton.setForeground(Theme.DANGER);
        logoutButton.setBackground(Theme.SURFACE);
        logoutButton.setFocusPainted(false);
        logoutButton.setBorderPainted(false);
        logoutButton.setContentAreaFilled(false);
        logoutButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        logoutButton.addActionListener(listener);
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        logoutButton.setBorder(BorderFactory.createEmptyBorder(Theme.PADDING_MEDIUM, Theme.PADDING_LARGE,
                Theme.PADDING_MEDIUM, Theme.PADDING_LARGE));

        logoutButton.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                logoutButton.setBackground(new Color(254, 242, 242)); // Red-50
                logoutButton.setContentAreaFilled(true);
            }

            public void mouseExited(MouseEvent evt) {
                logoutButton.setContentAreaFilled(false);
            }
        });

        add(logoutButton);
        add(Box.createVerticalStrut(Theme.PADDING_MEDIUM));
    }

    private JButton createMenuButton(String text, ActionListener listener) {
        JButton button = new JButton(text);
        button.setFont(Theme.FONT_BODY);
        button.setForeground(Theme.TEXT_PRIMARY);
        button.setBackground(Theme.SURFACE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setAlignmentX(Component.LEFT_ALIGNMENT);
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        button.setBorder(BorderFactory.createEmptyBorder(Theme.PADDING_MEDIUM, Theme.PADDING_LARGE,
                Theme.PADDING_MEDIUM, Theme.PADDING_LARGE));

        button.addActionListener(e -> {
            // Update selected state
            if (selectedButton != null) {
                selectedButton.setForeground(Theme.TEXT_PRIMARY);
                selectedButton.setContentAreaFilled(false);
            }
            selectedButton = button;
            button.setForeground(Theme.PRIMARY);
            button.setBackground(Theme.PRIMARY_PALE);
            button.setContentAreaFilled(true);
            listener.actionPerformed(e);
        });

        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                if (button != selectedButton) {
                    button.setBackground(Theme.SURFACE_DARK);
                    button.setContentAreaFilled(true);
                }
            }

            public void mouseExited(MouseEvent evt) {
                if (button != selectedButton) {
                    button.setContentAreaFilled(false);
                }
            }
        });

        return button;
    }

    public JButton getMenuButton(String text) {
        return menuButtons.get(text);
    }
}
