package com.github.vaerys.commands.roleSelect;

import com.github.vaerys.commands.CommandObject;
import com.github.vaerys.enums.ChannelSetting;
import com.github.vaerys.enums.SAILType;
import com.github.vaerys.handlers.RequestHandler;
import com.github.vaerys.main.Utility;
import com.github.vaerys.objects.XEmbedBuilder;
import com.github.vaerys.templates.Command;
import sx.blah.discord.handle.obj.IRole;
import sx.blah.discord.handle.obj.Permissions;

import java.util.ArrayList;

/**
 * Created by Vaerys on 31/01/2017.
 */
public class ListRoles extends Command {

    protected static final String[] NAMES = new String[]{"ListRoles", "Roles", "RoleList"};
    protected static final String USAGE = null;
    protected static final SAILType COMMAND_TYPE = SAILType.ROLE_SELECT;
    protected static final ChannelSetting CHANNEL_SETTING = ChannelSetting.BOT_COMMANDS;
    protected static final Permissions[] PERMISSIONS = new Permissions[0];
    protected static final boolean REQUIRES_ARGS = false;
    protected static final boolean DO_ADMIN_LOGGING = false;

    public static XEmbedBuilder getList(CommandObject command) {
        String title = "> Here are the **Cosmetic** roles you can choose from:\n";
        ArrayList<String> list = new ArrayList<>();
        XEmbedBuilder builder = new XEmbedBuilder(command);
        for (long l : command.guild.config.getCosmeticRoleIDs()) {
            IRole role = command.guild.getRoleByID(l);
            list.add(role.getName());
        }
        Utility.listFormatterEmbed(title, builder, list, true);
        builder.appendField(spacer, Utility.getCommandInfo(new CosmeticRoles(), command), false);
        return builder;
    }

    @Override
    public String execute(String args, CommandObject command) {
        if (command.guild.config.getCosmeticRoleIDs().size() == 0)
            return "> No Cosmetic roles are set up right now. Come back later.";
        RequestHandler.sendEmbedMessage("", getList(command), command.channel.get());
        return null;
    }

    @Override
    protected String[] names() {
        return NAMES;
    }

    @Override
    public String description(CommandObject command) {
        return "Shows the list of cosmetic roles you can choose from.";
    }

    @Override
    protected String usage() {
        return USAGE;
    }

    @Override
    protected SAILType type() {
        return COMMAND_TYPE;
    }

    @Override
    protected ChannelSetting channel() {
        return CHANNEL_SETTING;
    }

    @Override
    protected Permissions[] perms() {
        return PERMISSIONS;
    }

    @Override
    protected boolean requiresArgs() {
        return REQUIRES_ARGS;
    }

    @Override
    protected boolean doAdminLogging() {
        return DO_ADMIN_LOGGING;
    }

    @Override
    public void init() {

    }
}
