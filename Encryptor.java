import java.io.*;

public class Encryptor
{
    public static Message encrypt(String srcPath, String dstPath, byte[] key) throws IOException
    {
        FileInputStream src;
        FileOutputStream dst;
        
        try
        {
            src = new FileInputStream(srcPath);
            dst = new FileOutputStream(dstPath);
        }
        catch(IOException e)
        {
            return Message.BAD_PATH;
        }
        
        int i = 0;
        while(src.available() > 0)
        {
            dst.write(src.read() ^ key[i]);
            i++;
            i %= key.length;
        }
        
        src.close();
        dst.close();
        
        return Message.SUCCESS;
    }
}
