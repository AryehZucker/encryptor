import javax.swing.*;

public class PathInputPanel extends JPanel
{
    private JFileChooser fileChooser;
    private String path;
    
    public PathInputPanel(String title)
    {
        fileChooser = new JFileChooser();
        
        JLabel titleLabel = new JLabel(title);
        add(titleLabel);
        
        JLabel fileName = new JLabel("");
        add(fileName);
        
        JButton browseBtn = new JButton("Browse");
        browseBtn.addActionListener(event -> {
            if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                path = fileChooser.getSelectedFile().getPath();
                fileName.setText(fileChooser.getSelectedFile().getName());
            }
        });
        add(browseBtn);
    }
    
    public String getPath()
    {
        return path;
    }
}
