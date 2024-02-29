import java.io.IOException;
import javax.swing.*;

public class Main
{
    private static PathInputPanel srcFilePanel, dstFilePanel;
    private static KeyInputPanel keyInputPanel;
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Encrypt Files");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        
        srcFilePanel = new PathInputPanel("File: ");
        dstFilePanel = new PathInputPanel("Dest (opt): ");
        
        keyInputPanel = new KeyInputPanel();
                
        JButton encryptBtn = new JButton("Encrypt");
        encryptBtn.addActionListener(event -> execute());
        
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        panel.add(srcFilePanel);
        panel.add(dstFilePanel);
        panel.add(keyInputPanel);
        panel.add(encryptBtn);
                
        frame.getContentPane().add(panel);
        frame.pack();
        frame.setVisible(true);
    }
    
    private static void execute()
    {
        String src = srcFilePanel.getPath(),
               dst = dstFilePanel.getPath();
                
        if(src == null)
        {
            return;
        }
        
        if(dst == null)
        {
            if(src.endsWith(".crypt"))
            {
                dst = src.substring(0, src.lastIndexOf(".crypt"));
            }
            else
            {
                dst = src + ".crypt";
            }
            if(JOptionPane.showConfirmDialog(null, "Dest set to: " + dst) != JOptionPane.YES_OPTION)
                return;
        }
        
        String keyStr = keyInputPanel.getKey();
        if(keyStr.length() % 2 == 1)
        {
            JOptionPane.showMessageDialog(null, "Bad key: must be an even number of hexadecimal digits.");
            return;
        }
        if(keyStr.length() == 0)
            return;
        
        byte[] key = new byte[keyStr.length()/2];
        try
        {
            for(int i=0; i<key.length; i++)
            {
                key[i] = (byte)Integer.parseInt(keyStr.substring(2*i, 2*i+2), 16);
            }
        }
        catch(NumberFormatException e)
        {
            JOptionPane.showMessageDialog(null, "Bad key: must be an even number of hexadecimal digits.");
            return;
        }
        
        try
        {
            switch(Encryptor.encrypt(src, dst, key))
            {
                case BAD_PATH:
                    JOptionPane.showMessageDialog(null, "Encryption failed.");
                    break;
            }
        }
        catch(IOException e)
        {
            JOptionPane.showMessageDialog(null, "IOException: " + e.getMessage());
        }
    }
}
