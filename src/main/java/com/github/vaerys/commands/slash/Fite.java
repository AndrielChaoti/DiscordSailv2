package com.github.vaerys.commands.slash;

import com.github.vaerys.commands.CommandObject;
import com.github.vaerys.templates.SlashCommand;

/**
 * Created by Vaerys on 13/03/2017.
 */
public class Fite extends SlashCommand {

    @Override
    public String execute(String args, CommandObject command) {
        return "(ง'̀-'́)ง";
    }

    @Override
    protected String[] names() {
        return new String[]{"Fite"};
    }
}
