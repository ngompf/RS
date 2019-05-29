package Classes;

import org.powerbot.script.ClientAccessor;
import org.powerbot.script.ClientContext;

public abstract class Task <C extends ClientContext> extends ClientAccessor<C> {
    public boolean inProgress;
    public Task(C ctx){
        super(ctx);
        inProgress = false;
    }
    public abstract boolean activate();
    public abstract void execute();

}
