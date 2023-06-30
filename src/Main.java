import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class Main {
    private void readFileWithBufferedStream(File file) {
        try (FileInputStream myFile = new FileInputStream(file)) {
            BufferedInputStream bufferedInputStream = new BufferedInputStream(myFile);
            boolean eof = false;
            while (!eof) {
                int inByteValue = bufferedInputStream.read();
                if (inByteValue == -1) eof = true;
            }
        } catch (IOException e) {
            System.out.println("Could not read the stream...");
            e.printStackTrace();
        }
    }

    private void readFileWithInputStream(File file) {
        try (FileInputStream myFile = new FileInputStream(file)) {
            boolean eof = false;
            while (!eof) {
                int inByteValue = myFile.read();
                if (inByteValue == -1) eof = true;
            }
        } catch (IOException e) {
            System.out.println("Could not read the stream...");
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        File file = new File(Objects.requireNonNull(Main.class.getResource("anyFile.txt")).getPath());
        Main main = new Main();

        System.out.println("30mb 파일 테스트");
        System.out.println("InputStream 메소드 시작");
        main.readFileWithInputStream(file);
        System.out.println("BufferedStream 메소드 시작");
        main.readFileWithBufferedStream(file);
        System.out.println("종료.");

    }
}