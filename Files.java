import java.io.File;

public interface Files {
    public void copyFile(File srcFile, File destFile);

    public String correctedName(File srcFile);
}
