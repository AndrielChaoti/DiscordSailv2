package com.github.vaerys.commands.servers;

import com.github.vaerys.commands.CommandObject;
import com.github.vaerys.enums.ChannelSetting;
import com.github.vaerys.enums.SAILType;
import com.github.vaerys.templates.Command;
import sx.blah.discord.handle.obj.Permissions;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Vaerys on 31/01/2017.
 */
public class AddServer extends Command {
    @Override
    public String execute(String args, CommandObject command) {
        ArrayList<String> splitArgs = new ArrayList<>(Arrays.asList(args.split(" ")));
        String port = "N/a";
        if (splitArgs.size() > 2) {
            port = splitArgs.get(2);
        }
        if (splitArgs.size() < 2) {
            return "> Cannot create server, Missing IP";
        }
        return command.guild.servers.addServer(command.user.longID, splitArgs.get(0), splitArgs.get(1), port);
    }

    protected static final String[] NAMES = new String[]{"AddServer"};
    @Override
    protected String[] names() {
        return NAMES;
    }

    @Override
    public String description(CommandObject command) {
        return "Adds a server to the guild's server list.";
    }

    protected static final String USAGE = "[Server Name] [IP] (Port)";
    @Override
    protected String usage() {
        return USAGE;
    }

    protected static final SAILType COMMAND_TYPE = SAILType.SERVERS;
    @Override
    protected SAILType type() {
        return COMMAND_TYPE;
    }

    protected static final ChannelSetting CHANNEL_SETTING = ChannelSetting.SERVERS;
    @Override
    protected ChannelSetting channel() {
        return CHANNEL_SETTING;
    }

    protected static final Permissions[] PERMISSIONS = new Permissions[0];
    @Override
    protected Permissions[] perms() {
        return PERMISSIONS;
    }

    protected static final boolean REQUIRES_ARGS = true;
    @Override
    protected boolean requiresArgs() {
        return REQUIRES_ARGS;
    }

    protected static final boolean DO_ADMIN_LOGGING = false;
    @Override
    protected boolean doAdminLogging() {
        return DO_ADMIN_LOGGING;
    }

    @Override
    public void init() {

    }
}
