package com.github.vaerys.commands.roleSelect;

import java.util.ArrayList;
import com.github.vaerys.commands.CommandObject;
import com.github.vaerys.handlers.RequestHandler;
import com.github.vaerys.main.Utility;
import com.github.vaerys.objects.XEmbedBuilder;
import com.github.vaerys.enums.ChannelSetting;
import com.github.vaerys.templates.Command;
import com.github.vaerys.enums.SAILType;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by Vaerys on 31/01/2017.
 */
public class ListModifs extends Command {

    public static XEmbedBuilder getList(CommandObject command) {
        String title = "> Here are the **Modifier** roles you can choose from:\n";
        ArrayList<String> list = new ArrayList<>();
        XEmbedBuilder builder = new XEmbedBuilder(command);
        for (long l : command.guild.config.getModifierRoleIDs()) {
            IRole role = command.guild.getRoleByID(l);
            list.add(role.getName());
        }
        Utility.listFormatterEmbed(title, builder, list, true);
        builder.appendField(spacer, Utility.getCommandInfo(new ModifierRoles(), command), false);
        return builder;
    }

    @Override
    public String execute(String args, CommandObject command) {
        if (command.guild.config.getModifierRoleIDs().size() == 0) return "> No Modifier roles are set up right now. Come back later.";
        RequestHandler.sendEmbedMessage("",getList(command),command.channel.get());
        return null;
    }

    @Override
    protected String[] names() {
        return new String[]{"ListModifiers", "Modifiers", "Modifs"};
    }

    @Override
    public String description(CommandObject command) {
        return "Shows the list of modifier roles you can choose from.";
    }

    @Override
    protected String usage() {
        return null;
    }

    @Override
    protected SAILType type() {
        return SAILType.ROLE_SELECT;
    }

    @Override
    protected ChannelSetting channel() {
        return ChannelSetting.BOT_COMMANDS;
    }

    @Override
    protected Permissions[] perms() {
        return new Permissions[0];
    }

    @Override
    protected boolean requiresArgs() {
        return false;
    }

    @Override
    protected boolean doAdminLogging() {
        return false;
    }

    @Override
    public void init() {

    }
}
