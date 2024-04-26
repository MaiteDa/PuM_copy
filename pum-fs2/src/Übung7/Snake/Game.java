package Übung7.Snake;
public class Game {
    // Delay constants for a nicer game feel, feel free to adjust
    private static final int GAME_START_DELAY = 1500;
    private static final int GAME_END_DELAY   = 1500;

    private static final int SECS_TO_NANO  = 1_000_000_000;

    private UI ui;
    private Controls controls;

    private final int size;
    private final boolean loopEdges;
    private boolean run = true;

    public Game(int size, boolean loopEdges){ // Manages the game loop
        this.controls  = new Controls(this);

        this.size = size;
        this.loopEdges = loopEdges;
        ui = new UI();
        // tell the UI to capture key presses and let controls handle them
        ui.addKeyListener(controls);
        gameloop();
        System.exit(0);
    }

    public void gameloop(){
        while(run){
            // Start a new round of the game
            Gameplay gameplay = new Gameplay(size, loopEdges);
            ui.setModel(gameplay);
            ui.repaint();
            delay(GAME_START_DELAY);
            /*
             * Keep track of the time between updates and renders
             * If we didn't do this, the game would run faster on better hardware and slower on other
             */
            long lastRenderTime = System.nanoTime();
            long lastUpdateTime = 0;

            while(run){
                /*
                 * The update loop for each frame
                 */
                long deltaTime  = System.nanoTime() - lastRenderTime;
                lastRenderTime += deltaTime;
                lastUpdateTime += deltaTime;
                /*
                 * The time between updates is dependent on the gameplay-speed
                 * If the speed is 5, the snake will move 5 times per second.
                 */
                long timePerUpdate = 1 * SECS_TO_NANO / gameplay.calculateSpeed();
                if(lastUpdateTime > timePerUpdate){
                    lastUpdateTime -= timePerUpdate;
                    Direction next = controls.getNextDirection();
                    boolean stillAlive = gameplay.update(next);
                    if(!stillAlive)
                        break;
                }

                ui.repaint();
            }
            delay(GAME_END_DELAY);
        }
    }

    private void delay(long millis){
        // Helper method : DRY
        try { Thread.sleep(millis); }
        catch (InterruptedException ignored) {}
    }

    public void exitGame(){ run = false; }
}
