import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ProcessaArquivos {

    public boolean analisaArquivo(Path arquivo) {
        try {
            java.util.List<String> linhas = Files.readAllLines(arquivo);
            for (int i = 0; i < linhas.size(); i++) {
                System.out.println(linhas.get(i));
            }
            System.out.println();
            return true;
        } catch (IOException e) {
            System.err.println("Erro ao ler o arquivo " + arquivo.getFileName() + ": " + e.getMessage());
            return false;
        }
    }
}
