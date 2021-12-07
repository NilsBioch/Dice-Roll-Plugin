package me.nilsschnitzel.dicerollplugin;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
public final class DiceRollPlugin extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        System.out.println("Dice Roll Plugin Started!");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(command.getName().equalsIgnoreCase("roll")){
            if(sender instanceof Player){
                Player player = (Player) sender;
                int dicePoints = 0;
                if(args.length > 0){
                    if(checkForNumbers(args[0])){
                        int dices = Integer.parseInt(args[0]);
                        for (int i = 0; i < dices; i++) {
                            int number = rollDice(1, 6, sender );
                            dicePoints = dicePoints + number;
                        }
                        Bukkit.broadcastMessage(player.getName() + " heeft " + dicePoints + " gegooid met " + args[0] + " dobbelstenen");
                    }else{
                        int dicePoint = rollDice(1, 6, sender );
                        Bukkit.broadcastMessage(player.getName() + " heeft " + dicePoint + " gegooid");
                    }
                }else{
                    int dicePoint = rollDice(1, 6, sender );
                    Bukkit.broadcastMessage(player.getName() + " heeft " + dicePoint + " gegooid");
                }

            }
        }

        return true;
    }

    public int rollDice(int min, int max, CommandSender sender ){
        Player player = (Player) sender;
        int number = (int)Math.floor(Math.random()*(max-min+1)+min);
        return number;
    }

    public boolean checkForNumbers(String arg){
        try {
            Integer.parseInt(arg);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        System.out.println("Dice Roll Plugin Stopped!");
    }
}
