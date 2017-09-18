package com.martin.knazovic.Auth.Me;

import jdk.nashorn.internal.objects.annotations.Getter;
import org.spongepowered.api.Game;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.entity.DestructEntityEvent;
import org.spongepowered.api.event.network.ClientConnectionEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;
//import org.spongepowered.api.text.format.TextColors;
//import org.spongepowered.api.text.format.TextStyles;

import javax.inject.Inject;
import java.util.logging.Level;
import java.util.logging.Logger;

@Plugin(id = "auth_me", name = "AuthMe", version = "1.0")
public class AuthMe {

    @Inject
    Game game;

    @Inject
    Logger logger;

    @Listener
    public void onJoin(ClientConnectionEvent.Join e) {
        //treba pridat message aby sa hrac zaregistroval - inak disconnect

        logger.log(Level.INFO, "Auth.Me plugin is loaded.");

        CommandSpec AuthMeCmd = CommandSpec.builder()
                .description(Text.of("AuthMe Command"))
                .arguments(
                        GenericArguments.onlyOne(GenericArguments.string(Text.of("password"))),
                        GenericArguments.onlyOne(GenericArguments.string(Text.of("passwordRepeat")))
                )
                .executor(new AuthMeExecutor())
                .build();
        game.getCommandManager().register(this, AuthMeCmd, "register");
    }

    /*@Listener
    public void onDeath(DestructEntityEvent.Death e, @Getter("getTargetEntity") Player p) {
        p.sendMessage(Text.of("You Died!"));
    } */
}
