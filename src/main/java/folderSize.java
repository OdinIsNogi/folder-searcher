import java.io.File;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class folderSize extends RecursiveTask<Long> {
    private final File file;

    public folderSize(File file) {
        this.file = file;
    }

    @Override
    protected Long compute() {
        if (file.isFile()) {
            return file.length();
        }
        long sum = 0;
        List<folderSize> subTasks = new LinkedList<>();
        File[] files = file.listFiles();

        for (File file : files) {
            folderSize task = new folderSize(file);
            task.fork(); // запустим асинхронно
            subTasks.add(task);
        }

        for (folderSize task : subTasks) {
            sum += task.join(); // дождёмся выполнения задачи и прибавим результат
        }

        return sum;
    }

}

