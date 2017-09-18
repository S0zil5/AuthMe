package com.martin.knazovic.Auth.Me;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.text.format.TextColors;

public class AuthMeExecutor implements CommandExecutor {

    @Override
    public CommandResult execute(CommandSource src, CommandContext args ) throws CommandException {
        if (!(src instanceof Player)) { //sprav po databazi ak bude uz zaregistrovany nech napise login
            src.sendMessage(Text.of(TextColors.BLUE, "Hello Console"));
            return CommandResult.success();
        }
        Player p= (Player)src;
        p.sendMessage(Text.of(TextColors.RED, "Please register with /register!"));
        String nick = p.getName();
        String password = args.getOne("password").get().toString();
        String passwordR = args.getOne("passwordRepeat").get().toString();
        if (password.equals(passwordR)) {
            p.sendMessage(Text.of(TextColors.BLUE, "Your nick is " + nick));
            p.sendMessage(Text.of(TextColors.BLUE, "Your password is " + password));
            // pridaj database query
        } else {
            p.sendMessage(Text.of(TextColors.RED, "Passwords doesn't match!"));
        }
        return CommandResult.success();
    }

}
