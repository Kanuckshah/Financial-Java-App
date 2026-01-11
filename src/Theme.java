import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * Modern emerald green financial theme with premium styling
 */
public class Theme {
    // Emerald Green Color Palette
    public static final Color PRIMARY = new Color(16, 185, 129); // Emerald-500
    public static final Color PRIMARY_DARK = new Color(5, 150, 105); // Emerald-600
    public static final Color PRIMARY_LIGHT = new Color(52, 211, 153); // Emerald-400
    public static final Color PRIMARY_PALE = new Color(209, 250, 229); // Emerald-100

    // Neutral Colors
    public static final Color BACKGROUND = new Color(249, 250, 251); // Gray-50
    public static final Color SURFACE = Color.WHITE;
    public static final Color SURFACE_DARK = new Color(243, 244, 246); // Gray-100

    // Text Colors
    public static final Color TEXT_PRIMARY = new Color(17, 24, 39); // Gray-900
    public static final Color TEXT_SECONDARY = new Color(107, 114, 128); // Gray-500
    public static final Color TEXT_ON_PRIMARY = Color.WHITE;

    // Accent Colors
    public static final Color SUCCESS = new Color(34, 197, 94); // Green-500
    public static final Color WARNING = new Color(251, 146, 60); // Orange-400
    public static final Color DANGER = new Color(239, 68, 68); // Red-500
    public static final Color INFO = new Color(59, 130, 246); // Blue-500

    // Shadows
    public static final Color SHADOW_LIGHT = new Color(0, 0, 0, 10);
    public static final Color SHADOW_MEDIUM = new Color(0, 0, 0, 20);
    public static final Color SHADOW_DARK = new Color(0, 0, 0, 30);

    // Fonts
    public static final Font FONT_TITLE = new Font("Segoe UI", Font.BOLD, 28);
    public static final Font FONT_HEADING = new Font("Segoe UI", Font.BOLD, 20);
    public static final Font FONT_SUBHEADING = new Font("Segoe UI", Font.BOLD, 16);
    public static final Font FONT_BODY = new Font("Segoe UI", Font.PLAIN, 14);
    public static final Font FONT_SMALL = new Font("Segoe UI", Font.PLAIN, 12);
    public static final Font FONT_BUTTON = new Font("Segoe UI", Font.BOLD, 14);

    // Spacing
    public static final int PADDING_SMALL = 8;
    public static final int PADDING_MEDIUM = 16;
    public static final int PADDING_LARGE = 24;
    public static final int PADDING_XL = 32;

    // Border Radius
    public static final int RADIUS_SMALL = 6;
    public static final int RADIUS_MEDIUM = 12;
    public static final int RADIUS_LARGE = 16;

    /**
     * Create a modern rounded border
     */
    public static Border createRoundedBorder(Color color, int radius) {
        return BorderFactory.createCompoundBorder(
                new RoundedBorder(radius, color),
                BorderFactory.createEmptyBorder(PADDING_MEDIUM, PADDING_MEDIUM, PADDING_MEDIUM, PADDING_MEDIUM));
    }

    /**
     * Create a card-style border with shadow effect
     */
    public static Border createCardBorder() {
        return BorderFactory.createCompoundBorder(
                new ShadowBorder(RADIUS_MEDIUM),
                BorderFactory.createEmptyBorder(PADDING_LARGE, PADDING_LARGE, PADDING_LARGE, PADDING_LARGE));
    }

    /**
     * Style a button with primary theme
     */
    public static void styleButton(JButton button, boolean isPrimary) {
        button.setFont(FONT_BUTTON);
        button.setFocusPainted(false);
        button.setBorderPainted(true);
        button.setOpaque(true);
        button.setContentAreaFilled(true);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (isPrimary) {
            button.setBackground(PRIMARY);
            button.setForeground(TEXT_ON_PRIMARY);
        } else {
            button.setBackground(SURFACE_DARK);
            button.setForeground(TEXT_PRIMARY);
        }

        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(isPrimary ? PRIMARY : new Color(209, 213, 219), 1, true),
                BorderFactory.createEmptyBorder(12, 24, 12, 24)));

        // Hover effect
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                if (isPrimary) {
                    button.setBackground(PRIMARY_DARK);
                } else {
                    button.setBackground(new Color(229, 231, 235)); // Gray-200
                }
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                if (isPrimary) {
                    button.setBackground(PRIMARY);
                } else {
                    button.setBackground(SURFACE_DARK);
                }
            }
        });
    }

    /**
     * Style a text field with modern look
     */
    public static void styleTextField(JTextField field) {
        field.setFont(FONT_BODY);
        field.setForeground(TEXT_PRIMARY);
        field.setBackground(SURFACE);
        field.setCaretColor(PRIMARY);
        field.setOpaque(true);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true), // Gray-300
                BorderFactory.createEmptyBorder(10, 12, 10, 12)));

        // Focus effect
        field.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(PRIMARY, 2, true),
                        BorderFactory.createEmptyBorder(9, 11, 9, 11))); // Adjust padding for thicker border
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                field.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(209, 213, 219), 1, true),
                        BorderFactory.createEmptyBorder(10, 12, 10, 12)));
            }
        });
    }

    /**
     * Custom rounded border
     */
    static class RoundedBorder extends AbstractBorder {
        private int radius;
        private Color color;
        private int thickness;

        RoundedBorder(int radius, Color color) {
            this(radius, color, 1);
        }

        RoundedBorder(int radius, Color color, int thickness) {
            this.radius = radius;
            this.color = color;
            this.thickness = thickness;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(color);
            g2.setStroke(new BasicStroke(thickness));
            g2.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(thickness, thickness, thickness, thickness);
        }
    }

    /**
     * Custom shadow border for card effect
     */
    static class ShadowBorder extends AbstractBorder {
        private int radius;

        ShadowBorder(int radius) {
            this.radius = radius;
        }

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Draw shadow layers
            for (int i = 4; i >= 0; i--) {
                int alpha = 5 + (i * 3);
                g2.setColor(new Color(0, 0, 0, alpha));
                g2.fillRoundRect(x + i, y + i, width - (i * 2), height - (i * 2), radius, radius);
            }

            // Draw white background
            g2.setColor(SURFACE);
            g2.fillRoundRect(x, y, width, height, radius, radius);
            g2.dispose();
        }

        @Override
        public Insets getBorderInsets(Component c) {
            return new Insets(4, 4, 4, 4);
        }
    }

    /**
     * Create a gradient panel
     */
    public static JPanel createGradientPanel(Color color1, Color color2) {
        return new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                int w = getWidth();
                int h = getHeight();
                GradientPaint gp = new GradientPaint(0, 0, color1, 0, h, color2);
                g2.setPaint(gp);
                g2.fillRect(0, 0, w, h);
            }
        };
    }
}
