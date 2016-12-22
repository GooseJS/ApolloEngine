package input;

public class Keybinds
{

    public static void registerBasicKeybinds(InputFieldContainer editorIn)
    {
        // TODO: Have these be read from a config file
        editorIn.registerCommand("a", (currentLine, editor) -> editor.typeCharacter('a'));
        editorIn.registerCommand("b", (currentLine, editor) -> editor.typeCharacter('b'));
        editorIn.registerCommand("c", (currentLine, editor) -> editor.typeCharacter('c'));
        editorIn.registerCommand("d", (currentLine, editor) -> editor.typeCharacter('d'));
        editorIn.registerCommand("e", (currentLine, editor) -> editor.typeCharacter('e'));
        editorIn.registerCommand("f", (currentLine, editor) -> editor.typeCharacter('f'));
        editorIn.registerCommand("g", (currentLine, editor) -> editor.typeCharacter('g'));
        editorIn.registerCommand("h", (currentLine, editor) -> editor.typeCharacter('h'));
        editorIn.registerCommand("i", (currentLine, editor) -> editor.typeCharacter('i'));
        editorIn.registerCommand("j", (currentLine, editor) -> editor.typeCharacter('j'));
        editorIn.registerCommand("k", (currentLine, editor) -> editor.typeCharacter('k'));
        editorIn.registerCommand("l", (currentLine, editor) -> editor.typeCharacter('l'));
        editorIn.registerCommand("m", (currentLine, editor) -> editor.typeCharacter('m'));
        editorIn.registerCommand("n", (currentLine, editor) -> editor.typeCharacter('n'));
        editorIn.registerCommand("o", (currentLine, editor) -> editor.typeCharacter('o'));
        editorIn.registerCommand("p", (currentLine, editor) -> editor.typeCharacter('p'));
        editorIn.registerCommand("q", (currentLine, editor) -> editor.typeCharacter('q'));
        editorIn.registerCommand("r", (currentLine, editor) -> editor.typeCharacter('r'));
        editorIn.registerCommand("s", (currentLine, editor) -> editor.typeCharacter('s'));
        editorIn.registerCommand("t", (currentLine, editor) -> editor.typeCharacter('t'));
        editorIn.registerCommand("u", (currentLine, editor) -> editor.typeCharacter('u'));
        editorIn.registerCommand("v", (currentLine, editor) -> editor.typeCharacter('v'));
        editorIn.registerCommand("w", (currentLine, editor) -> editor.typeCharacter('w'));
        editorIn.registerCommand("x", (currentLine, editor) -> editor.typeCharacter('x'));
        editorIn.registerCommand("y", (currentLine, editor) -> editor.typeCharacter('y'));
        editorIn.registerCommand("z", (currentLine, editor) -> editor.typeCharacter('z'));

        editorIn.registerCommand("A", "SHIFT", (currentLine, editor) -> editor.typeCharacter('A'));
        editorIn.registerCommand("B", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('B'));
        editorIn.registerCommand("C", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('C'));
        editorIn.registerCommand("D", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('D'));
        editorIn.registerCommand("E", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('E'));
        editorIn.registerCommand("F", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('F'));
        editorIn.registerCommand("G", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('G'));
        editorIn.registerCommand("H", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('H'));
        editorIn.registerCommand("I", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('I'));
        editorIn.registerCommand("J", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('J'));
        editorIn.registerCommand("K", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('K'));
        editorIn.registerCommand("L", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('L'));
        editorIn.registerCommand("M", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('M'));
        editorIn.registerCommand("N", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('N'));
        editorIn.registerCommand("O", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('O'));
        editorIn.registerCommand("P", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('P'));
        editorIn.registerCommand("Q", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('Q'));
        editorIn.registerCommand("R", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('R'));
        editorIn.registerCommand("S", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('S'));
        editorIn.registerCommand("T", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('T'));
        editorIn.registerCommand("U", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('U'));
        editorIn.registerCommand("V", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('V'));
        editorIn.registerCommand("W", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('W'));
        editorIn.registerCommand("X", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('X'));
        editorIn.registerCommand("Y", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('Y'));
        editorIn.registerCommand("Z", "SHIFT",  (currentLine, editor) -> editor.typeCharacter('Z'));

        editorIn.registerCommand("A", (currentLine, editor) -> editor.typeCharacter('A'));
        editorIn.registerCommand("B", (currentLine, editor) -> editor.typeCharacter('B'));
        editorIn.registerCommand("C", (currentLine, editor) -> editor.typeCharacter('C'));
        editorIn.registerCommand("D", (currentLine, editor) -> editor.typeCharacter('D'));
        editorIn.registerCommand("E", (currentLine, editor) -> editor.typeCharacter('E'));
        editorIn.registerCommand("F", (currentLine, editor) -> editor.typeCharacter('F'));
        editorIn.registerCommand("G", (currentLine, editor) -> editor.typeCharacter('G'));
        editorIn.registerCommand("H", (currentLine, editor) -> editor.typeCharacter('H'));
        editorIn.registerCommand("I", (currentLine, editor) -> editor.typeCharacter('I'));
        editorIn.registerCommand("J", (currentLine, editor) -> editor.typeCharacter('J'));
        editorIn.registerCommand("K", (currentLine, editor) -> editor.typeCharacter('K'));
        editorIn.registerCommand("L", (currentLine, editor) -> editor.typeCharacter('L'));
        editorIn.registerCommand("M", (currentLine, editor) -> editor.typeCharacter('M'));
        editorIn.registerCommand("N", (currentLine, editor) -> editor.typeCharacter('N'));
        editorIn.registerCommand("O", (currentLine, editor) -> editor.typeCharacter('O'));
        editorIn.registerCommand("P", (currentLine, editor) -> editor.typeCharacter('P'));
        editorIn.registerCommand("Q", (currentLine, editor) -> editor.typeCharacter('Q'));
        editorIn.registerCommand("R", (currentLine, editor) -> editor.typeCharacter('R'));
        editorIn.registerCommand("S", (currentLine, editor) -> editor.typeCharacter('S'));
        editorIn.registerCommand("T", (currentLine, editor) -> editor.typeCharacter('T'));
        editorIn.registerCommand("U", (currentLine, editor) -> editor.typeCharacter('U'));
        editorIn.registerCommand("V", (currentLine, editor) -> editor.typeCharacter('V'));
        editorIn.registerCommand("W", (currentLine, editor) -> editor.typeCharacter('W'));
        editorIn.registerCommand("X", (currentLine, editor) -> editor.typeCharacter('X'));
        editorIn.registerCommand("Y", (currentLine, editor) -> editor.typeCharacter('Y'));
        editorIn.registerCommand("Z", (currentLine, editor) -> editor.typeCharacter('Z'));

        editorIn.registerCommand(" ", (currentLine, editor) -> editor.typeCharacter(' '));
        editorIn.registerCommand(" ", "SHIFT", (currentLine, editor) -> editor.typeCharacter(' '));
        editorIn.registerCommand(" ", "CONTROL", (currentLine, editor) -> editor.typeCharacter(' '));
        editorIn.registerCommand(" ", "ALT", (currentLine, editor) -> editor.typeCharacter(' '));
        editorIn.registerCommand(" ", "SUPER", (currentLine, editor) -> editor.typeCharacter(' '));

        editorIn.registerCommandWithAllMods("1", (currentLine, editor) -> editor.typeCharacter('1'));
        editorIn.registerCommandWithAllMods("2", (currentLine, editor) -> editor.typeCharacter('2'));
        editorIn.registerCommandWithAllMods("3", (currentLine, editor) -> editor.typeCharacter('3'));
        editorIn.registerCommandWithAllMods("4", (currentLine, editor) -> editor.typeCharacter('4'));
        editorIn.registerCommandWithAllMods("5", (currentLine, editor) -> editor.typeCharacter('5'));
        editorIn.registerCommandWithAllMods("6", (currentLine, editor) -> editor.typeCharacter('6'));
        editorIn.registerCommandWithAllMods("7", (currentLine, editor) -> editor.typeCharacter('7'));
        editorIn.registerCommandWithAllMods("8", (currentLine, editor) -> editor.typeCharacter('8'));
        editorIn.registerCommandWithAllMods("9", (currentLine, editor) -> editor.typeCharacter('9'));
        editorIn.registerCommandWithAllMods("0", (currentLine, editor) -> editor.typeCharacter('0'));

        editorIn.registerCommandWithAllMods("`", (currentLine, editor) -> editor.typeCharacter('`'));
        editorIn.registerCommandWithAllMods("~", (currentLine, editor) -> editor.typeCharacter('~'));

        editorIn.registerCommandWithAllMods("!", (currentLine, editor) -> editor.typeCharacter('!'));
        editorIn.registerCommandWithAllMods("@", (currentLine, editor) -> editor.typeCharacter('@'));
        editorIn.registerCommandWithAllMods("#", (currentLine, editor) -> editor.typeCharacter('#'));
        editorIn.registerCommandWithAllMods("$", (currentLine, editor) -> editor.typeCharacter('$'));
        editorIn.registerCommandWithAllMods("%", (currentLine, editor) -> editor.typeCharacter('%'));
        editorIn.registerCommandWithAllMods("^", (currentLine, editor) -> editor.typeCharacter('^'));
        editorIn.registerCommandWithAllMods("&", (currentLine, editor) -> editor.typeCharacter('&'));
        editorIn.registerCommandWithAllMods("*", (currentLine, editor) -> editor.typeCharacter('*'));
        editorIn.registerCommandWithAllMods("(", (currentLine, editor) -> editor.typeCharacter('('));
        editorIn.registerCommandWithAllMods(")", (currentLine, editor) -> editor.typeCharacter(')'));
        editorIn.registerCommandWithAllMods("-", (currentLine, editor) -> editor.typeCharacter('-'));
        editorIn.registerCommandWithAllMods("_", (currentLine, editor) -> editor.typeCharacter('_'));
        editorIn.registerCommandWithAllMods("=", (currentLine, editor) -> editor.typeCharacter('='));
        editorIn.registerCommandWithAllMods("+", (currentLine, editor) -> editor.typeCharacter('+'));

        editorIn.registerCommandWithAllMods("[", (currentLine, editor) -> editor.typeCharacter('['));
        editorIn.registerCommandWithAllMods("{", (currentLine, editor) -> editor.typeCharacter('{'));
        editorIn.registerCommandWithAllMods("]", (currentLine, editor) -> editor.typeCharacter(']'));
        editorIn.registerCommandWithAllMods("}", (currentLine, editor) -> editor.typeCharacter('}'));
        editorIn.registerCommandWithAllMods("\\", (currentLine, editor) -> editor.typeCharacter('\\'));
        editorIn.registerCommandWithAllMods("|", (currentLine, editor) -> editor.typeCharacter('|'));

        editorIn.registerCommandWithAllMods(";", (currentLine, editor) -> editor.typeCharacter(';'));
        editorIn.registerCommandWithAllMods(":", (currentLine, editor) -> editor.typeCharacter(':'));
        editorIn.registerCommandWithAllMods("'", (currentLine, editor) -> editor.typeCharacter('\''));
        editorIn.registerCommandWithAllMods("\"", (currentLine, editor) -> editor.typeCharacter('"'));

        editorIn.registerCommandWithAllMods(",", (currentLine, editor) -> editor.typeCharacter(','));
        editorIn.registerCommandWithAllMods("<", (currentLine, editor) -> editor.typeCharacter('<'));
        editorIn.registerCommandWithAllMods(".", (currentLine, editor) -> editor.typeCharacter('.'));
        editorIn.registerCommandWithAllMods(">", (currentLine, editor) -> editor.typeCharacter('>'));
        editorIn.registerCommandWithAllMods("/", (currentLine, editor) -> editor.typeCharacter('/'));
        editorIn.registerCommandWithAllMods("?", (currentLine, editor) -> editor.typeCharacter('?'));

        editorIn.registerCommandWithAllMods("KEY_BACKSPACE", (currentLine, editor) -> editor.backspace());
        editorIn.registerCommandWithAllMods("KEY_TAB", (currentLine, editor) -> editor.tab());
        editorIn.registerCommandWithAllMods("KEY_ENTER", (currentLine, editor) -> editor.newLine());

        editorIn.registerCommandWithAllMods("KEY_UP", (currentLine, editor) -> editor.cursorUp());
        editorIn.registerCommandWithAllMods("KEY_DOWN", (currentLine, editor) -> editor.cursorDown());
        editorIn.registerCommandWithAllMods("KEY_LEFT", (currentLine, editor) -> editor.cursorBack());
        editorIn.registerCommandWithAllMods("KEY_RIGHT", (currentLine, editor) -> editor.cursorForward());
    }

}