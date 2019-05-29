package Scripts;

import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.*;
import Tasks.AttackMulti;
import Classes.AutoCastSpellSelector;
import Tasks.PickBestSpell;
import Classes.Task;

import java.util.ArrayList;


@Script.Manifest(name = "DemonTipper", description = "test", properties = "author=annon; topic=999; client=4;")
public class WizardTowerLeveler extends PollingScript<ClientContext> {

    ArrayList<Task> taskList = new ArrayList<Task>();
    public Task currentTask = null;

    public final String[] NPCTOATTACK = {"Lesser demon"};
    public AutoCastSpellSelector spellSelector = new AutoCastSpellSelector(ctx);

    @Override
    public void start(){
        System.out.println("Start");
        taskList.add(new PickBestSpell(ctx));
        taskList.add(new AttackMulti(ctx,NPCTOATTACK));
    }

    @Override
    public void poll() {
        ctx.components.select().text("Click here to continue").visible().poll().click();
        if(currentTask == null || !currentTask.inProgress) {
            currentTask = newTask(taskList);
        }
        else
            currentTask.execute();
    }
    @Override
    public void stop(){
        System.out.println("Stopped");
    }
    public Task newTask(ArrayList<Task> taskList){
        for (Task task: taskList){
            if(task.activate()){
                return task;
            }
        }
        return null;
    }
}