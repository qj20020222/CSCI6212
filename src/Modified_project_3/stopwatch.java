package Modified_project_3;


public class stopwatch {
    private final long start;
    public stopwatch(){
        start = System.nanoTime();
    }
    public double elapsedTime(){
        long now = System.nanoTime();
        return (now-start);
    }
}
