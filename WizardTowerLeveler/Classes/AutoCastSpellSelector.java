package Classes;

import org.powerbot.script.Condition;
import org.powerbot.script.rt4.ClientContext;
import org.powerbot.script.rt4.Game;

import java.util.ArrayList;

public class AutoCastSpellSelector{
    private ClientContext ctx;
    public String currentAutoCast;  // 1 - 16 then 48-51
    public ArrayList<AutoCastSpell> list = new ArrayList<>();

    public AutoCastSpellSelector(ClientContext ctx) {
        this.ctx = ctx;
        list.add(new AutoCastSpell("wind strike" , 1 ,1));
        list.add(new AutoCastSpell("water strike", 5 ,2));
        list.add(new AutoCastSpell("earth strike", 9 ,3));
        list.add(new AutoCastSpell("fire strike" , 13,4));

        list.add(new AutoCastSpell("wind bolt"   , 17,5));
        list.add(new AutoCastSpell("water bolt"  , 23,6));
        list.add(new AutoCastSpell("earth bolt"  , 29,7));
        list.add(new AutoCastSpell("fire bolt"   , 35,8));

        list.add(new AutoCastSpell("wind blast"  , 41,9));
        list.add(new AutoCastSpell("water blast" , 47,10));
        list.add(new AutoCastSpell("earth blast" , 53,11));
        list.add(new AutoCastSpell("fire blast"  , 59,12));

        list.add(new AutoCastSpell("wind wave"   , 62,13));
        list.add(new AutoCastSpell("water wave"  , 65,14));
        list.add(new AutoCastSpell("earth wave"  , 70,15));
        list.add(new AutoCastSpell("fire wave"   , 75,16));

        list.add(new AutoCastSpell("wind surge"  , 81,48));
        list.add(new AutoCastSpell("water surge" , 85,49));
        list.add(new AutoCastSpell("earth surge" , 90,50));
        list.add(new AutoCastSpell("fire surge"  , 95,51));
    }
    public boolean usingBestSpell(int level){
        return getBestSpell(level).equalsIgnoreCase(currentAutoCast);
    }
    public boolean selectBestAutoCast(int level){
        return selectAutoCast(getBestSpell(level));
    }

    public String getBestSpell(int level){
        AutoCastSpell bestSpell = list.get(0);
        for (AutoCastSpell s : list) {
            if(s.level<=level)
                bestSpell = s;
        }
        return bestSpell.spellName;
    }

    public boolean selectAutoCast(String spellName){
        int spellIndex = 0;

        for (AutoCastSpell s : list) {
            if (s.spellName.equalsIgnoreCase(spellName))
                spellIndex = s.autoCastIndex;
        }

        if(spellName.toLowerCase() == currentAutoCast)
            return true;
        if (spellIndex != 0) {
            ctx.game.tab(Game.Tab.ATTACK);
            Condition.sleep(1000);
            ctx.widgets.widget(593).component(25).click();
            Condition.sleep(1000);
            ctx.widgets.widget(201).component(1).component(spellIndex).click();
            currentAutoCast = spellName.toLowerCase();
            return true;

        }
        return false;
    }

}
