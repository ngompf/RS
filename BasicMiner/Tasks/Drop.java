package Tasks;

import Classes.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;
import org.powerbot.script.rt4.Item;

public class Drop extends Task<ClientContext> {
    public Item target;
    public String[] itemName;
    public Drop(ClientContext ctx,String[] itemName){
        super(ctx);
        this.itemName = itemName;
        target = null;
    }

    @Override
    public boolean activate() {
        if(isItem(itemName)) {
            ctx.game.tab(Game.Tab.INVENTORY);
            inProgress = true;
            target = newTarget(itemName);
        }
        return inProgress;
    }

    @Override
    public void execute() { inProgress = drop(target); }
    public boolean drop(Item target){
        if(!target.valid())
            return false;
        if(!(ctx.players.local().inMotion() || !(ctx.players.local().animation() == -1))) {
            if (target.interact("Drop"))
                Condition.sleep(1000);
        }
        return true;
    }
    public Item newTarget(String[] itemName){
        System.out.println("Dropping...");
        return ctx.inventory.select().name(itemName).poll();
    }
    public boolean isItem(String[] itemName){
        return !ctx.inventory.select().name(itemName).isEmpty();
    }

}
