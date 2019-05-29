package tasks;

import Classes.Task;
import Classes.Walker;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;

public class WalkToBank extends Task<ClientContext> {

    public Tile[] pathToBank; //= {new Tile(3255, 3287, 0), new Tile(3255, 3283, 0), new Tile(3255, 3279, 0), new Tile(3255, 3275, 0), new Tile(3255, 3271, 0), new Tile(3253, 3267, 0), new Tile(3249, 3267, 0), new Tile(3245, 3266, 0), new Tile(3242, 3263, 0), new Tile(3238, 3262, 0), new Tile(3234, 3262, 0), new Tile(3230, 3262, 0), new Tile(3226, 3262, 0), new Tile(3222, 3262, 0), new Tile(3218, 3262, 0), new Tile(3218, 3258, 0), new Tile(3218, 3254, 0), new Tile(3218, 3250, 0), new Tile(3218, 3246, 0), new Tile(3219, 3242, 0), new Tile(3222, 3239, 0), new Tile(3225, 3236, 0), new Tile(3229, 3234, 0), new Tile(3230, 3230, 0), new Tile(3231, 3226, 0), new Tile(3231, 3222, 0), new Tile(3228, 3219, 0), new Tile(3224, 3219, 0), new Tile(3220, 3219, 0), new Tile(3216, 3219, 0), new Tile(3215, 3223, 0), new Tile(3213, 3227, 0), new Tile(3209, 3227, 0), new Tile(3205, 3228, 0), new Tile(3206, 3229, 1), new Tile(3206, 3229, 2), new Tile(3206, 3225, 2), new Tile(3206, 3221, 2), new Tile(3210, 3220, 2)};
    private final Walker walker = new Walker(ctx);

    public WalkToBank(ClientContext ctx,Tile[] pathToBank) {
        super(ctx);
        this.pathToBank = pathToBank;
    }

    @Override
    public boolean activate() {
        if(shouldGoToBank() || shouldGoToLocation())
            inProgress = true;
        return inProgress;
    }

    @Override
    public void execute() {

        if (!ctx.players.local().inMotion() || ctx.movement.destination().equals(Tile.NIL) || ctx.movement.destination().distanceTo(ctx.players.local()) < 5) {
            if (ctx.inventory.isFull()) {
                System.out.println("Going to bank...");
                walker.walkPath(pathToBank);
                if(!shouldGoToBank())
                    inProgress = false;

            } else {
                System.out.println("Going to Location...");
                walker.walkPathReverse(pathToBank);
                if(!shouldGoToLocation())
                    inProgress = false;
            }
        }

    }

    public boolean shouldGoToBank(){
        return (ctx.inventory.isFull() && (pathToBank[pathToBank.length-1].distanceTo(ctx.players.local()) > 5));
    }
    public boolean shouldGoToLocation(){
        return (ctx.inventory.isEmpty() && (pathToBank[0].distanceTo(ctx.players.local()) > 5));
    }
}
