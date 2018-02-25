package com.github.vaerys.commands.general;

import com.github.vaerys.commands.CommandObject;
import com.github.vaerys.enums.ChannelSetting;
import com.github.vaerys.enums.SAILType;
import com.github.vaerys.main.Globals;
import com.github.vaerys.objects.TimedEvent;
import com.github.vaerys.templates.Command;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by Vaerys on 30/01/2017.
 */
public class Hello extends Command {

    protected static final String[] NAMES = new String[]{"Hello", "Hi", "Greetings"};
    protected static final String USAGE = null;
    protected static final SAILType COMMAND_TYPE = SAILType.GENERAL;
    protected static final ChannelSetting CHANNEL_SETTING = null;
    protected static final Permissions[] PERMISSIONS = new Permissions[0];
    protected static final boolean REQUIRES_ARGS = false;
    protected static final boolean DO_ADMIN_LOGGING = false;

    @Override
    public String execute(String args, CommandObject command) {
        String message = "> Hello <user>.";
        TimedEvent event = Globals.getCurrentEvent();
        if (event != null && event.getHelloMessage() != null) message = event.getHelloMessage();
        if (command.user.longID == 153159020528533505L) {
            message = message.replace("<user>", "Mother");
        }
        return message.replace("<user>", command.user.displayName);
    }

    @Override
    protected String[] names() {
        return new String[]{"Hello", "Hi", "Greetings"};
    }

    @Override
    public String description(CommandObject command) {
        return "Says Hello.";
    }

    @Override
    protected String usage() {
        return null;
    }

    @Override
    protected SAILType type() {
        return SAILType.GENERAL;
    }

    @Override
    protected ChannelSetting channel() {
        return null;
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
