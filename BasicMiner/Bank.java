package tasks;

import Classes.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;

public class Bank extends Task<ClientContext> {
    public Bank (ClientContext ctx){
        super(ctx);
    }
    @Override
    public boolean activate() {
        if(ctx.inventory.isFull() && !ctx.players.local().inMotion() && (ctx.bank.nearest().tile().distanceTo(ctx.players.local())<4))
            inProgress = true;
        return inProgress;
    }
    @Override
    public  void execute(){
        if(ctx.inventory.isFull()) {
            if (ctx.bank.opened()) {
                if (ctx.bank.depositInventory())
                    Condition.sleep(2000);
            } else {
                if (!ctx.bank.inViewport())
                    ctx.camera.turnTo(ctx.bank.nearest());
                else {
                    if (ctx.bank.open())
                        Condition.sleep(2000);
                }
            }
        }
        if(ctx.inventory.isEmpty()){
            ctx.bank.close();
            Condition.sleep(2000);
            inProgress = false;
        }

    }
}
