package Scripts;


import Classes.Task;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.Tile;
import org.powerbot.script.rt4.ClientContext;
import Tasks.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Script.Manifest(name = "BasicMiner", description = "test", properties = "author=annon; topic=999; client=4;")
public class BasicMiner extends PollingScript<ClientContext> {

    List<Task> taskList = new ArrayList<Task>();
    public Task currentTask = null;
    public final int[] COPPER_ORE_ID = {11161, 10943};
    public final int[] TIN_ORE_ID = {11360, 11361};
    public final int[] CLAY_ORE_ID = {11362, 11363};
    public final int[] IRON_ORE_ID = {11364, 11365};
    public final int[] SILVER_ORE_ID = {11368, 11369};
    public final int[] COAL_ORE_ID = {11366, 11367};
    public final Tile[] BARBARIAN_PATH = {new Tile(3084, 3423, 0), new Tile(3086, 3426, 0), new Tile(3088, 3429, 0), new Tile(3088, 3432, 0), new Tile(3088, 3435, 0), new Tile(3088, 3438, 0), new Tile(3088, 3441, 0), new Tile(3088, 3444, 0), new Tile(3086, 3447, 0), new Tile(3086, 3450, 0), new Tile(3086, 3453, 0), new Tile(3086, 3456, 0), new Tile(3086, 3459, 0), new Tile(3086, 3462, 0), new Tile(3083, 3464, 0), new Tile(3080, 3467, 0), new Tile(3079, 3470, 0), new Tile(3079, 3473, 0), new Tile(3080, 3476, 0), new Tile(3080, 3479, 0), new Tile(3080, 3482, 0), new Tile(3083, 3483, 0), new Tile(3086, 3485, 0), new Tile(3089, 3488, 0), new Tile(3092, 3490, 0),new Tile(3094, 3491, 0)};
    public final Tile[] VARROCK_EAST_PATH = {new Tile(3285, 3365, 0), new Tile(3287, 3368, 0), new Tile(3287, 3371, 0), new Tile(3289, 3374, 0), new Tile(3292, 3377, 0), new Tile(3293, 3380, 0), new Tile(3292, 3383, 0), new Tile(3292, 3386, 0), new Tile(3292, 3389, 0), new Tile(3292, 3392, 0), new Tile(3292, 3395, 0), new Tile(3292, 3398, 0), new Tile(3292, 3401, 0), new Tile(3292, 3404, 0), new Tile(3292, 3407, 0), new Tile(3292, 3410, 0), new Tile(3290, 3413, 0), new Tile(3288, 3416, 0), new Tile(3288, 3419, 0), new Tile(3285, 3421, 0), new Tile(3282, 3422, 0), new Tile(3279, 3424, 0), new Tile(3276, 3425, 0), new Tile(3273, 3426, 0), new Tile(3270, 3426, 0), new Tile(3267, 3426, 0), new Tile(3264, 3426, 0), new Tile(3261, 3426, 0), new Tile(3258, 3428, 0), new Tile(3255, 3427, 0), new Tile(3253, 3424, 0), new Tile(3253, 3421, 0)};
    public final Tile[] VARROCK_WEST_PATH = {new Tile(3177, 3367, 0), new Tile(3180, 3370, 0), new Tile(3183, 3372, 0), new Tile(3182, 3375, 0), new Tile(3182, 3378, 0), new Tile(3179, 3381, 0), new Tile(3177, 3384, 0), new Tile(3174, 3386, 0), new Tile(3172, 3389, 0), new Tile(3170, 3392, 0), new Tile(3170, 3395, 0), new Tile(3170, 3398, 0), new Tile(3170, 3401, 0), new Tile(3170, 3404, 0), new Tile(3170, 3407, 0), new Tile(3170, 3410, 0), new Tile(3170, 3413, 0), new Tile(3171, 3416, 0), new Tile(3171, 3419, 0), new Tile(3170, 3422, 0), new Tile(3171, 3425, 0), new Tile(3174, 3428, 0), new Tile(3177, 3428, 0), new Tile(3180, 3428, 0), new Tile(3182, 3431, 0), new Tile(3182, 3434, 0), new Tile(3185, 3436, 0)};
    public final Tile[] LUMBRIDGE_PATH = {new Tile(3226, 3147, 0), new Tile(3229, 3149, 0), new Tile(3231, 3152, 0), new Tile(3231, 3155, 0), new Tile(3231, 3158, 0), new Tile(3233, 3161, 0), new Tile(3235, 3164, 0), new Tile(3237, 3167, 0), new Tile(3237, 3170, 0), new Tile(3237, 3173, 0), new Tile(3237, 3176, 0), new Tile(3237, 3179, 0), new Tile(3237, 3182, 0), new Tile(3239, 3185, 0), new Tile(3242, 3188, 0), new Tile(3244, 3191, 0), new Tile(3242, 3194, 0), new Tile(3241, 3197, 0), new Tile(3239, 3200, 0), new Tile(3237, 3203, 0), new Tile(3236, 3206, 0), new Tile(3236, 3209, 0), new Tile(3236, 3212, 0), new Tile(3235, 3215, 0), new Tile(3232, 3218, 0), new Tile(3229, 3218, 0), new Tile(3226, 3218, 0), new Tile(3223, 3218, 0), new Tile(3220, 3218, 0), new Tile(3217, 3218, 0), new Tile(3215, 3215, 0), new Tile(3215, 3212, 0), new Tile(3212, 3210, 0), new Tile(3209, 3210, 0), new Tile(3206, 3210, 0), new Tile(3205, 3209, 1), new Tile(3205, 3209, 2), new Tile(3205, 3212, 2), new Tile(3206, 3215, 2), new Tile(3206, 3218, 2), new Tile(3209, 3220, 2)};

    @Override
    public void start() {
        System.out.println("Start");
        String[] oreOptions = new String[0];
        Tile[] path = new Tile[0];
        int[] ore = new int[0];

        String[] locationOptions = {"Lumbridge","Varrock East","Varrock West","Barbarian Village"};
        String locationChoice = ""+(String) JOptionPane.showInputDialog(null,"Location", "BasicMiner", JOptionPane.PLAIN_MESSAGE,null,locationOptions,locationOptions[0]);

        if(locationChoice.equals("Lumbridge")){
            oreOptions = new String[]{"Copper ore","Tin ore"};
            path = LUMBRIDGE_PATH;
        }
        if(locationChoice.equals("Varrock East")){
            oreOptions = new String[]{"Copper ore","Tin ore","Iron ore"};
            path = VARROCK_EAST_PATH;
        }
        if(locationChoice.equals("Varrock West")){
            oreOptions = new String[]{"Copper ore","Tin ore","Clay ore","Iron ore","Silver ore","Coal ore"};
            path = VARROCK_WEST_PATH;
        }
        if(locationChoice.equals("Barbarian Village")){
            oreOptions = new String[]{"Tin ore","Coal ore"};
            path = BARBARIAN_PATH;
        }
        String oreChoice = ""+(String) JOptionPane.showInputDialog(null,"Ore", "BasicMiner", JOptionPane.PLAIN_MESSAGE,null,oreOptions,oreOptions[0]);

        if(oreChoice.equals("Copper ore")){
            ore = COPPER_ORE_ID;
        }
        if(oreChoice.equals("Tin ore")){
            ore = TIN_ORE_ID;
        }
        if(oreChoice.equals("Clay ore")){
            ore = CLAY_ORE_ID;
        }
        if(oreChoice.equals("Iron ore")){
            ore = IRON_ORE_ID;
        }
        if(oreChoice.equals("Silver ore")){
            ore = SILVER_ORE_ID;
        }
        if(oreChoice.equals("Coal ore")){
            ore = COAL_ORE_ID;
        }

        String[] miningOptions = {"Bank Ores","Drop Ores"};
        String miningChoice = ""+(String) JOptionPane.showInputDialog(null,"Location", "BasicMiner", JOptionPane.PLAIN_MESSAGE,null,miningOptions,miningOptions[0]);
        if(miningChoice.equals("Bank Ores")){
            taskList.add(new Bank(ctx));
        }
        if(miningChoice.equals("Drop Ores")){
            taskList.add(new Drop(ctx,new String[]{oreChoice}));
        }


        taskList.add(new WalkToBank(ctx,path));
        taskList.add(new Mine(ctx, ore));
    }

    @Override
    public void poll() {
        if (currentTask == null || !currentTask.inProgress) {
            currentTask = newTask(taskList);
        } else
            currentTask.execute();
    }

    @Override
    public void stop() {
        System.out.println("Stopped");
    }

    public Task newTask(List<Task> taskList) {
        for (Task task : taskList) {
            if (task.activate()) {
                return task;
            }
        }
        return null;
    }
}