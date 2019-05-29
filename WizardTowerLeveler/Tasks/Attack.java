package Tasks;

import Classes.Task;
import org.powerbot.script.Condition;
import org.powerbot.script.rt4.BasicQuery;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

public class Attack extends Task<ClientContext> {
    public Npc target;
    public String[] npcName;
    public Attack(ClientContext ctx,String[] npcName){
        super(ctx);
        this.npcName = npcName;
        target = null;
    }
    @Override
    public boolean activate() {
        if (!ctx.players.local().interacting().equals(target) && isTargets(npcName)) {
            inProgress = true;
            target = newTarget(npcName);
            System.out.println(target);
        }
        return inProgress;
    }
    @Override
    public  void execute(){
        inProgress = attack(target);
    }
    public boolean attack(final Npc target){
        System.out.println(target.animation());
        if(canAttack()) {
            return false;
        }
        if(!ctx.players.local().interacting().equals(target) && !ctx.players.local().inMotion() && ctx.players.local().animation() == -1 && target.animation() == -1) {
                if (!target.inViewport())
                ctx.camera.turnTo(target);
            else {
                if (target.interact("Attack"))
                    Condition.sleep(500);
            }
        }
        return true;
    }
    public Npc newTarget(String[] npcName){
        System.out.println("Attacking...");
        BasicQuery<Npc> n = ctx.npcs.select().name(npcName).nearest();
        Npc p = n.poll();
        while(p.healthBarVisible())
            p = n.poll();
        return p;
    }
    public boolean canAttack(){
        return !target.valid() || (target.interacting().valid() && !ctx.players.local().interacting().equals(target));
    }
    public boolean isTargets(String[] npcName){
        return !ctx.npcs.select().name(npcName).isEmpty();
    }
}