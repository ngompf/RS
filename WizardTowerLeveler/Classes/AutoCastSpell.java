package Classes;
import org.powerbot.script.rt4.Magic;

public class AutoCastSpell{
    public String spellName;
    public int level;
    public int autoCastIndex;
    public AutoCastSpell(String spellName,int level, int autoCastIndex) {
        this.spellName = spellName;
        this.level = level;
        this.autoCastIndex = autoCastIndex;
    }
}
