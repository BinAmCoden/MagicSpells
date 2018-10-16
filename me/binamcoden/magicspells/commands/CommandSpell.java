package me.binamcoden.magicspells.commands;

import me.binamcoden.magicspells.MagicSpells;
import me.binamcoden.magicspells.spells.HealingTotem;
import me.binamcoden.magicspells.spells.Spread;
import me.binamcoden.magicspells.spells.FireNova;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandSpell implements CommandExecutor {

    private String prefix = MagicSpells.getInstance().prefix;

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (sender instanceof Player) {
            Player player = (Player) sender;
            if (args.length > MagicSpells.MAX_ARGUMENT_LENGTH || args.length < MagicSpells.MAX_ARGUMENT_LENGTH) {
                player.sendMessage(prefix + "§cUsage: §7/spell <1,2,3>");
            } else {
                try {
                    int spell = Integer.valueOf(args[0]);
                    switch (spell) {
                        case MagicSpells.FIRE_NOVA:
                            new FireNova(player).conjure();
                            break;
                        case MagicSpells.SPREAD:
                            new Spread(player).conjure();
                            break;
                        case MagicSpells.HEALING_TOTEM:
                            new HealingTotem(player).conjure();
                            break;
                    }
                } catch (NumberFormatException expetion) {
                    player.sendMessage(prefix + "§cPlease enter a valid number");
                }
            }
        }
        return false;
    }
}
