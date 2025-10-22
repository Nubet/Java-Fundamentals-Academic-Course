import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class FileIO
{
    private final Path path;

    public FileIO(String filePath) throws IOException {
        this.path = Path.of(filePath);
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
    }

    public List<String> read() throws IOException {
        return Files.readAllLines(path);
    }

    public void write(List<String> list) throws IOException {
        Files.write(path, list);
    }
}

