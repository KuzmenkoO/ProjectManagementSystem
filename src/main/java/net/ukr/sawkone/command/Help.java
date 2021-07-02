package net.ukr.sawkone.command;

import net.ukr.sawkone.view.View;

public class Help implements Command {

    private View view;

    public Help(View view) {
        this.view = view;
    }

    @Override
    public void process() {
        view.write("help - show a list of commands");
        view.write("exit - exit from an application");
        view.write("queries - display a list of default queries");
        view.write("company - show menu company");
        view.write("projects - show menu project");
        view.write("developers - show menu developer");
        view.write("customers - show menu customers");
    }

    @Override
    public String commandName() {
        return "help";
    }
}
