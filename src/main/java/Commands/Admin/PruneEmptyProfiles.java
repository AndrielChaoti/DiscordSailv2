package Commands.Admin;

import Commands.CommandObject;
import Interfaces.Command;
import Objects.UserTypeObject;
import sx.blah.discord.handle.obj.Permissions;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.ListIterator;

public class PruneEmptyProfiles implements Command {

    @Override
    public String execute(String args, CommandObject command) {
        ArrayList<UserTypeObject> profiles = command.guildUsers.getUsers();
        UserTypeObject defaultProfile = new UserTypeObject(null);
        long profileCount = 0;
        ListIterator listIterator = profiles.listIterator();
        while (listIterator.hasNext()) {
            UserTypeObject profile = (UserTypeObject) listIterator.next();
            boolean noXP = profile.getXP() == 0;
            boolean noGender = defaultProfile.getGender().equals(profile.getGender());
            boolean noQuote = defaultProfile.getQuote().equals(profile.getQuote());
            boolean noSettings = profile.getSettings().size() == 0;
            boolean noLinks = profile.getLinks().size() == 0;
            if (noXP && noGender && noQuote && noSettings && noLinks) {
                listIterator.remove();
                profileCount++;
            }
        }
        return "> " + NumberFormat.getInstance().format(profileCount) + " empty profiles pruned.";
    }

    @Override
    public String[] names() {
        return new String[]{"PruneEmptyProfiles"};
    }

    @Override
    public String description() {
        return "Prunes all of the empty users on the server.";
    }

    @Override
    public String usage() {
        return null;
    }

    @Override
    public String type() {
        return TYPE_ADMIN;
    }

    @Override
    public String channel() {
        return null;
    }

    @Override
    public Permissions[] perms() {
        return new Permissions[]{Permissions.MANAGE_SERVER};
    }

    @Override
    public boolean requiresArgs() {
        return false;
    }

    @Override
    public boolean doAdminLogging() {
        return true;
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