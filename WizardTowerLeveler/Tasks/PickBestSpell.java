package Tasks;

import Classes.Task;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Constants;
import Classes.AutoCastSpellSelector;

public class PickBestSpell extends Task<ClientContext> {
    public AutoCastSpellSelector spellSelector;

    public PickBestSpell(ClientContext ctx){
        super(ctx);
        spellSelector = new AutoCastSpellSelector(ctx);
    }

    @Override
    public boolean activate() {
        if(!spellSelector.usingBestSpell(ctx.skills.realLevel(Constants.SKILLS_MAGIC))){
            System.out.println(spellSelector.currentAutoCast);
            inProgress = true;
        }
        return inProgress;
    }

    @Override
    public void execute() {
        spellSelector.selectBestAutoCast(ctx.skills.realLevel(Constants.SKILLS_MAGIC));
        inProgress = false;
    }
}
