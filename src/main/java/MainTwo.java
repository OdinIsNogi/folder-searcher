import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class MainTwo {
    public static void main(String[] args) {

        String path = "G:\\Games";
        File file = new File(path);
        long start = System.currentTimeMillis();
        folderSize size = new folderSize(file);
        ForkJoinPool pool = new ForkJoinPool();
        long finalSize = pool.invoke(size);
        System.out.println(finalSize);
        long duration = (System.currentTimeMillis() - start);
        System.out.println(duration);

    }
}