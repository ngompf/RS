package Tasks;

import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Npc;

public class AttackMulti extends Attack{
    public AttackMulti(ClientContext ctx, String[] npcName) {
        super(ctx, npcName);
    }
    public boolean canAttack(){
        return !target.valid();
    }
    public Npc newTarget(String[] npcName){
        System.out.println("Attacking...");
        return ctx.npcs.select().name(npcName).nearest().poll();
    }
}
