
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormPanel extends JPanel {
    private Map<String, JComponent> fieldComponents;
    private JPanel formPanel;
    private JPanel buttonPanel;
    private GridBagConstraints gbc;
    private int currentRow;

    public FormPanel(String title) {
        this.fieldComponents = new HashMap<>();
        this.currentRow = 0;

        setLayout(new BorderLayout());
        setBackground(Theme.BACKGROUND);
        setBorder(BorderFactory.createEmptyBorder(Theme.PADDING_XL, Theme.PADDING_XL, Theme.PADDING_XL,
                Theme.PADDING_XL));

        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Theme.BACKGROUND);

        gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        if (title != null && !title.isEmpty()) {
            JLabel titleLabel = new JLabel(title);
            titleLabel.setFont(Theme.FONT_TITLE);
            titleLabel.setForeground(Theme.TEXT_PRIMARY);
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gbc.gridx = 0;
            gbc.gridy = currentRow++;
            gbc.gridwidth = 2;
            formPanel.add(titleLabel, gbc);
            gbc.gridwidth = 1;
        }

        add(formPanel, BorderLayout.CENTER);
    }

    public FormPanel addField(FormField field) {
        gbc.gridy = currentRow++;
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;

        JLabel label = new JLabel(field.getLabel());
        label.setFont(Theme.FONT_BODY);
        label.setForeground(Theme.TEXT_PRIMARY);
        formPanel.add(label, gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        JComponent component = createFieldComponent(field);
        fieldComponents.put(field.getName(), component);
        formPanel.add(component, gbc);

        return this;
    }

    public FormPanel addFields(List<FormField> fields) {
        for (FormField field : fields) {
            addField(field);
        }
        return this;
    }

    private JComponent createFieldComponent(FormField field) {
        JComponent component;

        switch (field.getType()) {
            case PASSWORD:
                component = new JPasswordField(field.getColumns());
                break;
            case NUMBER:
                component = new JTextField(field.getColumns());
                break;
            case DATE:
                component = new JTextField(field.getColumns());
                break;
            case TEXT:
            default:
                component = new JTextField(field.getColumns());
                break;
        }

        if (field.getDefaultValue() != null && !field.getDefaultValue().isEmpty()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText(field.getDefaultValue());
            }
        }

        if (field.getTooltip() != null && !field.getTooltip().isEmpty()) {
            component.setToolTipText(field.getTooltip());
        }

        // Apply modern styling to text fields
        if (component instanceof JTextField) {
            Theme.styleTextField((JTextField) component);
        }

        return component;
    }

    public FormPanel addButtons(String... buttonLabels) {
        buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, Theme.PADDING_MEDIUM, 0));
        buttonPanel.setBackground(Theme.BACKGROUND);

        gbc.gridy = currentRow++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.weightx = 0;
        formPanel.add(buttonPanel, gbc);

        return this;
    }

    public FormPanel addButton(String label, ActionListener listener) {
        if (buttonPanel == null) {
            addButtons();
        }

        JButton button = new JButton(label);
        button.addActionListener(listener);

        // Style as primary or secondary based on label
        boolean isPrimary = label.equalsIgnoreCase("Save") || label.equalsIgnoreCase("Login")
                || label.equalsIgnoreCase("Register");
        Theme.styleButton(button, isPrimary);

        buttonPanel.add(button);

        return this;
    }

    public String getFieldValue(String fieldName) {
        JComponent component = fieldComponents.get(fieldName);
        if (component instanceof JTextField) {
            return ((JTextField) component).getText().trim();
        } else if (component instanceof JPasswordField) {
            return new String(((JPasswordField) component).getPassword());
        }
        return "";
    }

    public void setFieldValue(String fieldName, String value) {
        JComponent component = fieldComponents.get(fieldName);
        if (component instanceof JTextField) {
            ((JTextField) component).setText(value);
        } else if (component instanceof JPasswordField) {
            ((JPasswordField) component).setText(value);
        }
    }

    public void clearFields() {
        for (JComponent component : fieldComponents.values()) {
            if (component instanceof JTextField) {
                ((JTextField) component).setText("");
            } else if (component instanceof JPasswordField) {
                ((JPasswordField) component).setText("");
            }
        }
    }

    public boolean validateRequired(List<FormField> fields) {
        for (FormField field : fields) {
            if (field.isRequired()) {
                String value = getFieldValue(field.getName());
                if (value == null || value.isEmpty()) {
                    return false;
                }
            }
        }
        return true;
    }

    public JComponent getField(String fieldName) {
        return fieldComponents.get(fieldName);
    }
}
