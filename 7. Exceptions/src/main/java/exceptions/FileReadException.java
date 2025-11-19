package exceptions;

public class FileReadException extends Exception
{
    private final String filePath;

    public String getFilePath() {
        return filePath;
    }

    public FileReadException(String message, String filePath) {
        super(message);
        this.filePath = filePath;
    }

    public FileReadException(String message, String filePath, Throwable cause) {
        super(message, cause);
        this.filePath = filePath;
    }
}
