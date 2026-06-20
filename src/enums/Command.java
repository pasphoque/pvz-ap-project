package enums;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum Command {
    REGISTER("(?i)^\\S*register\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)\\s+(?<passwordConfirm>\\S+)\\s+-n\\s+(?<nickname>\\S+)\\s+-e\\s+(?<email>\\S+)\\s+-g\\s+(?<gender>\\S+)\\S*$"),
    LOGIN("(?i)^\\S*login\\s+-u\\s+(?<username>\\S+)\\s+-p\\s+(?<password>\\S+)(?<stayLoggedIn>\\s+-stay-logged-in)?\\S*$"),
    PICK_QUESTION("^\\S*pick\\s+question\\s+-q\\s+(?<questionNumber>\\d+)\\s+-a\\s+(?<answer>\\S+)\\s+-c\\s+(?<answerConfirm>\\S+)$"),
    FORGET_PASSWORD("^\\S*forget\\s+password\\s+-u\\s+(?<username>\\S+)\\s+-e\\s+(?<email>\\S+)\\s+-a\\s+(?<answer>\\S+)$"),
    MENU_ENTER("(?i)^\\S*menu\\s+enter\\s+(?<menuName>.+)\\S*$"),
    MENU_EXIT("(?i)^\\S*menu\\s+exit\\S*$"),
    MENU_LOGOUT("(?i)^\\S*menu\\s+logout\\S*$"),
    ADVANCE_TIME("(?i)^\\S*advance\\s+time\\s+-t\\s+(?<ticks>\\d+)\\s+ticks\\S*$"),
    SHOW_CURRENT_MENU("(?i)^\\S*menu\\s+show\\S+current\\S*$"),
    PUT_PLANT("(?i)^\\S*put\\s+(?<plantName>[a-zA-Z]+)\\s+(?<x>\\d+)\\s+(?<y>\\d+)\\S*$"),
    SPAWN_ZOMBIE("(?i)^\\S*spawn\\s+(?<zombieName>[a-zA-Z]+)\\s+(?<x>\\d+)\\s+(?<y>\\d+)\\S*$"),
    UNKNOWN("");

    private final Pattern pattern;

    Command(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    public Matcher getMatcher(String input) {
        return pattern.matcher(input);
    }
}