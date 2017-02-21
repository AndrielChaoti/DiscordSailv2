package Commands.DMCommands;

import Commands.Command;
import Commands.CommandObject;
import Commands.DMCommand;
import Commands.DMCommandObject;
import Main.Utility;
import sx.blah.discord.handle.obj.IGuild;
import sx.blah.discord.handle.obj.Permissions;
import sx.blah.discord.util.EmbedBuilder;

import java.util.ArrayList;

/**
 * Created by Vaerys on 17/02/2017.
 */
public class GetGuildList implements DMCommand {
    @Override
    public String execute(String args, DMCommandObject command) {
        ArrayList<String> guilds = new ArrayList<>();
        for (IGuild g: command.client.getGuilds()){
            guilds.add(g.getName() +": " + g.getID());
        }
        EmbedBuilder builder = new EmbedBuilder();
        Utility.listFormatterEmbed("List Of Guilds", builder, guilds, false);
        Utility.sendDMEmbed("",builder.build(),command.authorID);
        return null;
    }

    @Override
    public String[] names() {
        return new String[]{"GetGuildList"};
    }

    @Override
    public String description() {
        return "Gives a list of all the Guilds the bot is connected to.";
    }

    @Override
    public String usage() {
        return null;
    }

    @Override
    public String type() {
        return TYPE_CREATOR;
    }

    @Override
    public boolean requiresArgs() {
        return false;
    }
}