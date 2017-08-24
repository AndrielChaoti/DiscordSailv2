package com.github.vaerys.commands.pixels;

import com.github.vaerys.commands.CommandObject;
import com.github.vaerys.handlers.XpHandler;
import com.github.vaerys.interfaces.Command;
import com.github.vaerys.main.Utility;
import com.github.vaerys.masterobjects.UserObject;
import com.github.vaerys.objects.ProfileObject;
import com.github.vaerys.objects.XEmbedBuilder;
import sx.blah.discord.handle.obj.IUser;
import sx.blah.discord.handle.obj.Permissions;

import java.text.NumberFormat;
import java.util.ArrayList;

public class Rank implements Command {

    @Override
    public String execute(String args, CommandObject command) {
        UserObject user = command.user;
        String error = "> Cannot get rank stats for this user.";
        if (args != null && !args.isEmpty()) {
            UserObject userObject = Utility.getUser(command, args, true);
            if (userObject != null) {
                user = userObject;
            } else {
                return "> Could not find user.";
            }
        }
        if (user.isPrivateProfile(command.guild) && user.longID != command.user.longID) {
            return "> User has set their profile to private.";
        } else if (user.isPrivateProfile(command.guild) && user.longID == command.user.longID) {
            return "> You cannot see your ranking as you have set your profile to private.";
        }
        //grab a copy of the list
        ArrayList<ProfileObject> users = (ArrayList<ProfileObject>) command.guild.users.getProfiles().clone();
        //sort profiles by Xp in ascending order (lowest Xp to highest XP).
        Utility.sortUserObjects(users, true);
        //test to see if said user actually has rank stats.
        if (XpHandler.rank(command.guild.users, command.guild.get(), user.stringID) == -1) {
            return error;
        }
        //build the Array of stats
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getID().equals(user.stringID)) {
                ArrayList<ProfileObject> ranks = new ArrayList();
                ArrayList<String> response = new ArrayList();

                int addedTop = 0;
                int addedBottom = 0;
                int posTop = 0;
                int posBottom = 0;
                //add profiles above
                while (addedTop < 3 && i + posTop < users.size()) {
                    if (!user.stringID.equals(users.get(i + posTop).getID()) && XpHandler.rank(command.guild.users, command.guild.get(), users.get(i + posTop).getID()) != -1) {
                        addedTop++;
                        ranks.add(users.get(i + posTop));
                    }
                    posTop++;
                }
                //add center user
                ranks.add(users.get(i));
                //add user below
                while (addedBottom < 3 && i + posBottom > 0) {
                    if (!user.stringID.equals(users.get(i + posBottom).getID()) && XpHandler.rank(command.guild.users, command.guild.get(), users.get(i + posBottom).getID()) != -1) {
                        addedBottom++;
                        ranks.add(users.get(i + posBottom));
                    }
                    posBottom--;
                }
                //sort ranked profiles
                Utility.sortUserObjects(ranks, false);
                //format rank stats
                for (ProfileObject r : ranks) {
                    IUser ranked = command.guild.get().getUserByID(r.getID());
                    String rankPos = "**" + XpHandler.rank(command.guild.users, command.guild.get(), r.getID()) + "** - ";
                    String toFormat = ranked.getDisplayName(command.guild.get())
                            + "\n " + indent + "`Level: " + r.getCurrentLevel() + ", Pixels: " + NumberFormat.getInstance().format(r.getXP()) + "`";
                    if (r.getID().equals(user.stringID)) {
                        response.add(rankPos + spacer + "**" + toFormat + "**");
                    } else {
                        response.add(rankPos + toFormat);
                    }
                }
                XEmbedBuilder builder = new XEmbedBuilder();
                builder.withTitle("Rank stats for: " + user.displayName);
                builder.withDesc(Utility.listFormatter(response, false));
                builder.withColor(command.client.color);
                Utility.sendEmbedMessage("", builder, command.channel.get());
                return null;
            }
        }
        return error;
    }

    @Override
    public String[] names() {
        return new String[]{"Rank"};
    }

    @Override
    public String description() {
        return "Gives you some information about your rank";
    }

    @Override
    public String usage() {
        return "(@User)";
    }

    @Override
    public String type() {
        return TYPE_PIXEL;
    }

    @Override
    public String channel() {
        return CHANNEL_PIXELS;
    }

    @Override
    public Permissions[] perms() {
        return new Permissions[0];
    }

    @Override
    public boolean requiresArgs() {
        return false;
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