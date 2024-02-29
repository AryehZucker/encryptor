import javax.swing.*;

public class KeyInputPanel extends JPanel
{
    private JTextField keyField;

    public KeyInputPanel()
    {
        JLabel title = new JLabel("Key: ");
        add(title);
        
        keyField = new JTextField(20);
        add(keyField);
        
        JButton generateBtn = new JButton("Generate");
        generateBtn.addActionListener(event -> generateRandomKey());
        add(generateBtn);
    }
    
    private void generateRandomKey()
    {
        StringBuilder key = new StringBuilder();
        
        for(int i=0; i<8; i++)
        {
            key.append(String.format("%x", (int)(Math.random() * 0x100)));
        }
        
        keyField.setText(key.toString());
    }
    
    public String getKey()
    {
        return keyField.getText();
    }
}
