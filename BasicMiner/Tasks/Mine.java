package Tasks;

import Classes.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.GameObject;

public class Mine extends Task<ClientContext> {
    public GameObject target;
    public int[] oreName;
    public Mine(ClientContext ctx, int[] oreName){
        super(ctx);
        this.oreName = oreName;
        target = null;
    }

    @Override
    public boolean activate() {
        if(isOre(oreName)) {
            inProgress = true;
            target = newTarget(oreName);
        }
        return inProgress;
    }

    @Override
    public void execute() {
        inProgress = mine(target);
    }
    public boolean mine (GameObject target){
        if(!target.valid())
            return false;
        if(!(ctx.players.local().inMotion() || !(ctx.players.local().animation() == -1))) {
            if (!target.inViewport())
                ctx.camera.turnTo(target);
            if (target.interact("Mine"))
                Condition.sleep(1000);
        }
        return true;
    }

    public GameObject newTarget(int[] oreName){
        System.out.println("Mining...");
        return ctx.objects.select(10).id(oreName).nearest().poll();
    }
    public boolean isOre(int[] oreName){
        return !ctx.objects.select(10).id(oreName).isEmpty();
    }
}
