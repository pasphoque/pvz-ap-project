package enums;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;

public class CommandParser {

    public Command parseCommand(String input) {
        input = input.trim();
        for (Command command : Command.values()) {
            if (command == Command.UNKNOWN) {
                continue;
            }

            Matcher matcher = command.getMatcher(input);
            if (matcher.matches()) {
                return command;
            }
        }
        return Command.UNKNOWN;
    }

    public Map<String, String> extractArguments(Command command, String input) {
        Map<String, String> args = new HashMap<>();

        if (command == Command.UNKNOWN) {
            return args;
        }

        Matcher matcher = command.getMatcher(input.trim());
        if (matcher.matches()) {
            if (command == Command.REGISTER) {
                args.put("username", matcher.group("username"));
                args.put("password", matcher.group("password"));
                args.put("passwordConfirm", matcher.group("passwordConfirm"));
                args.put("nickname", matcher.group("nickname"));
                args.put("email", matcher.group("email"));
                args.put("gender", matcher.group("gender"));
            } else if (command == Command.LOGIN) {
                args.put("username", matcher.group("username"));
                args.put("password", matcher.group("password"));
                args.put("stayLoggedIn", matcher.group("stayLoggedIn") != null ? "true" : "false");
            } else if (command == Command.MENU_ENTER) {
                args.put("menuName", matcher.group("menuName"));
            }
        }
        return args;
    }
}
