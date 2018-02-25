package com.github.vaerys.commands.dmCommands;

import com.github.vaerys.commands.CommandObject;
import com.github.vaerys.commands.general.RemindMe;
import com.github.vaerys.enums.SAILType;
import com.github.vaerys.handlers.GuildHandler;
import com.github.vaerys.main.Utility;
import com.github.vaerys.masterobjects.UserObject;
import com.github.vaerys.objects.ProfileObject;
import com.github.vaerys.objects.SplitFirstObject;
import com.github.vaerys.templates.DMCommand;
import sx.blah.discord.handle.obj.Permissions;

/**
 * Created by Vaerys on 27/02/2017.
 */
public class ReminderDM extends DMCommand {

    protected static final String[] NAMES = new RemindMe().names;
    protected static final String USAGE = "[Quote...]";
    protected static final SAILType COMMAND_TYPE = SAILType.GENERAL;
    protected static final Permissions[] PERMISSIONS = new Permissions[0];
    protected static final boolean REQUIRES_ARGS = true;
    protected static final boolean DO_ADMIN_LOGGING = false;

    @Override
    public String execute(String args, CommandObject command) {
        UserObject user = command.user;
        String quote = args;
        boolean adminEdit = false;
        if (isSubtype(command, "SetUserQuote")) {
            if (GuildHandler.testForPerms(command, Permissions.MANAGE_MESSAGES)) {
                SplitFirstObject userCall = new SplitFirstObject(quote);
                user = Utility.getUser(command, userCall.getFirstWord(), false, true);
                if (user == null) return "> Could not find user.";
                quote = userCall.getRest();
                adminEdit = true;
            } else {
                return command.user.notAllowed;
            }
        }
        int maxLength = 140;
        if (user.isPatron) {
            maxLength += 140;
        }
        ProfileObject u = user.getProfile(command.guild);
        quote = Utility.removeFun(quote);
        if (quote == null || quote.isEmpty()) return "> You can't have an empty quote.";
        for (String s : quote.split(" ")) {
            if (!Utility.checkURL(s)) {
                return "> Cannot add quote. Malicious link found.";
            }
        }
        if (adminEdit) {
            if (quote.length() > maxLength) {
                return "> Quote is too long...\n(must be under " + maxLength + " chars)";
            }
            u.setQuote(quote);
            return "> " + user.displayName + "'s Quote Edited.";
        } else {
            if (quote.length() > maxLength) {
                return "> Your Quote is too long...\n(must be under " + maxLength + " chars)";
            }
            u.setQuote(quote);
            return "> Quote Edited.";
        }
    }

    @Override
    protected String[] names() {
        return NAMES;
    }

    @Override
    public String description(CommandObject command) {
        String response = "Allows you to set your quote. Limit 140 chars (or 280 if you are a patron)";
        if (GuildHandler.testForPerms(command, Permissions.MANAGE_MESSAGES)) {
            response += "\n\n**" + command.guild.config.getPrefixCommand() + names()[1] + " [@User] [Quote...]**\n" +
                    "**Desc:** Edits a user's quote.\n" +
                    "**Permissions:** " + Permissions.MANAGE_MESSAGES + ".\n";
        }
        return response;

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
