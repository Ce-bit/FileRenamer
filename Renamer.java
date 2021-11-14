import java.io.File;
import java.io.IOException;

public class Renamer {
    public static void main(String[] argv) throws IOException {
        String path_to_folder = "\\filesToRename";
        File my_folder = new File(path_to_folder);
        File[] array_file = my_folder.listFiles();
        for (int i = 0; i < array_file.length; i++) {
            if (array_file[i].isFile()) {
                File my_file = new File(path_to_folder + "\\" + array_file[i].getName());
                String long_file_name = array_file[i].getName();
                String[] my_token = long_file_name.split("\\s");
                String new_file = my_token[1];
                System.out.println(long_file_name);
                System.out.print(new_file);
                my_file.renameTo(new File(path_to_folder + "\\" + new_file + ".pdf"));
            }
        }
    }
}