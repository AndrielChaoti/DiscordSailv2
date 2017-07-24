package Commands.General;

import Commands.CommandObject;
import Handlers.DailyHandler;
import Interfaces.Command;
import Main.Globals;
import Main.Utility;
import Objects.SplitFirstObject;
import sx.blah.discord.handle.obj.Permissions;

import java.time.DayOfWeek;
import java.util.Arrays;

public class NewDailyMessage implements Command {

    @Override
    public String execute(String args, CommandObject command) {
        try {
            SplitFirstObject day = new SplitFirstObject(args);
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(day.getFirstWord().toUpperCase());
            if (day.getRest() != null) {
                DailyHandler.addToQueue(command, day.getRest(), dayOfWeek);
                return "> Request Sent.";
            } else {
                return Utility.getCommandInfo(this, command);
            }
        }catch (IllegalArgumentException e){
            return "> Not a valid Day of the week.";
        }
    }

    public static void checkIsEnabled(boolean enabled) {
        boolean commandFound = false;
        for (Command c : Globals.getCommands()) {
            if (Arrays.equals(c.names(), new NewDailyMessage().names())) {
                commandFound = true;
            }
        }
        if (!commandFound && enabled) {
            Globals.getCommands().add(new NewDailyMessage());
        } else if (commandFound && !enabled) {
            for (Command c : Globals.getCommands()) {
                if (Arrays.equals(c.names(), new NewDailyMessage().names())) {
                    Globals.getCommands().remove(c);
                    return;
                }
            }
        } else {
            return;
        }
    }

    @Override
    public String[] names() {
        return new String[]{"RequestDailyMessage","RequestDailyMsg","ReqDailyMsg"};
    }

    @Override
    public String description() {
        return "Allows you to request a new Daily message to be added.";
    }

    @Override
    public String usage() {
        return "[DayOfWeek] [Message]";
    }

    @Override
    public String type() {
        return TYPE_GENERAL;
    }

    @Override
    public String channel() {
        return CHANNEL_BOT_COMMANDS;
    }

    @Override
    public Permissions[] perms() {
        return new Permissions[0];
    }

    @Override
    public boolean requiresArgs() {
        return true;
    }

    @Override
    public boolean doAdminLogging() {
        return false;
    }

    @Override
    public String dualDescription() {
        return null;
    }

    @Override
    public String dualUsage() {
        return null;
    }

    @Override
    public String dualType() {
        return null;
    }

    @Override
    public Permissions[] dualPerms() {
        return new Permissions[0];
    }
}