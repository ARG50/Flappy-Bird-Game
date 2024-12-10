


public class GameManager {
    private static GameManager instance = new GameManager();
    private boolean running = true;

    private GameManager() {}

    public static GameManager getInstance() {
        return instance;
    }

    public boolean isRunning() {
        return running;
    }

    public void stopGame() {
        running = false;
        System.out.println("Game stopped.");
    }

}

