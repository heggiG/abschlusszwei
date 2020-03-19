package cardgame.ui;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import cardgame.card.BuildingCard;
import cardgame.card.DrawableCard;
import cardgame.core.CardUtility;
import cardgame.core.GameState;
import cardgame.core.GameSystem;
import cardgame.exceptions.GameException;
import cardgame.exceptions.InputException;
import edu.kit.informatik.Terminal;

/**
 * Enum that handles the user input
 * 
 * @author Florian Heck
 * @version 1.0
 */
public enum Commands {

    /**
     * The start command
     */ // 64 cards and 63 commas
    START("start ((" + Commands.DRAWABLES + "|,){127})") {
        @Override
        public void run(Matcher match, GameSystem gs) throws InputException {
            if (!gs.getCurrentState().isExpected(this)) {
                throw new InputException("unexpected command for current game state");
            }
            try {
                initialInput = CardUtility.getStartInput(match.group(1));
                gs.newGame(initialInput);
                Terminal.printLine("OK"); //TODO keine karten mehr übrig
            } catch (InputException e) {
                Terminal.printError(e.getMessage());
            }
        }
    },

    /**
     * The draw command
     */
    DRAW("draw") {
        @Override
        public void run(Matcher match, GameSystem gs) throws InputException {
            if (!gs.getCurrentState().isExpected(this)) {
                throw new InputException("unexpected command for current game state");
            }
            try {
                Terminal.printLine(gs.draw().getType());
            } catch (GameException e) {
                Terminal.printError(e.getMessage());
            }
        }
    },

    /**
     * The list-resources command
     */
    LISTRESOURCES("list-resources") {
        @Override
        public void run(Matcher match, GameSystem gs) throws InputException {
            Iterator<DrawableCard> iter = gs.listResources();
            if (iter == null) {
                Terminal.printLine("EMPTY");
            } else {
                while (iter.hasNext()) {
                    Terminal.printLine(iter.next().getType());
                }
            }
        }
    },

    /**
     * The build command
     */
    BUILD("build (" + Commands.BUILDABLES + ")") {
        @Override
        public void run(Matcher match, GameSystem gs) throws InputException {
            if (!gs.getCurrentState().isExpected(this)) {
                throw new InputException("unexpected command for current game state");
            } else {
                try {
                    BuildingCard toBuild = CardUtility.getFromString(match.group(1));
                    if (gs.build(toBuild)) {
                        if (gs.getCurrentState() == GameState.SCAVENGE || gs.getCurrentState() == GameState.ENDEAVOR) {
                            Terminal.printLine("OK");
                        } else if (gs.getCurrentState() == GameState.END) {
                            Terminal.printLine("win");
                        }
                    } else {
                        Terminal.printError("can't build card");
                    }
                } catch (InputException e) {
                    Terminal.printError(e.getMessage());
                } catch (GameException e) {
                    Terminal.printError(e.getMessage());
                }
            }
        }
    },

    /**
     * The list-buildings command
     */
    LISTBUILDINGS("list-buildings") {
        @Override
        public void run(Matcher match, GameSystem gs) {
            Iterator<BuildingCard> iter = gs.listBuildings();
            if (iter == null) {
                Terminal.printLine("EMPTY");
            } else {
                while (iter.hasNext()) {
                    Terminal.printLine(iter.next().getType());
                }
            }
        }
    },

    /**
     * The build? command
     */
    BUILDABLE("build\\?") {
        @Override
        public void run(Matcher match, GameSystem gs) {
            if (gs.buildable().isEmpty()) {
                Terminal.printLine("EMPTY");
            } else {
                for (BuildingCard bc : gs.buildable()) {
                    Terminal.printLine(bc);
                }
            }
        }
    },

    /**
     * The rolldx command
     */
    ROLLDX("rollD(\\+?0*[468]) ([+-]?\\d+)") {
        @Override
        public void run(Matcher match, GameSystem gs) throws InputException {
            if (!gs.getCurrentState().isExpected(this)) {
                throw new InputException("unexpected command for current game state");
            }
            int dieSize = Integer.parseInt(match.group(1));
            int thrown = Integer.parseInt(match.group(2));
            try {
                if (gs.getCurrentState() == GameState.ENCOUNTER) {
                    if (gs.dieThrow(dieSize, thrown)) {
                        Terminal.printLine("survived");
                    } else {
                        Terminal.printLine("lose");
                    }
                } else if (gs.getCurrentState() == GameState.ENDEAVOR) {
                    if (gs.dieThrow(dieSize, thrown)) {
                        Terminal.printLine("win");
                    } else {
                        Terminal.printLine("lose");
                    }
                }
            } catch (GameException e) {
                Terminal.printError(e.getMessage());
            }
        }
    },

    /**
     * The reset command
     */
    RESET("reset") {
        @Override
        public void run(Matcher match, GameSystem gs) {
            gs.newGame(initialInput);
            Terminal.printLine("OK");
        }
    },

    /**
     * The quit command
     */
    QUIT("quit") {
        @Override
        public void run(Matcher match, GameSystem gs) {
            quit();
        }
    };

    private static final String BUILDABLES = "axe|club|shack|fireplace|sailingraft|hangglider|steamboat|ballon";
    private static final String DRAWABLES = "wood|metal|plastic|spider|snake|tiger|thunderstorm";
    private static DrawableCard[] initialInput;
    private Pattern pattern;
    private boolean running;

    private Commands(String regex) {
        pattern = Pattern.compile(regex);
        running = true;
    }

    /**
     * Finds the fitting command and executes it
     * 
     * @param gamesys The gamesystem to operate on
     * @param input   the user input
     * @return The found command
     * @throws InputException If the input dosen't fit the expected one
     */
    public static Commands runFitting(GameSystem gamesys, String input) throws InputException {
        for (Commands comm : Commands.values()) {
            Matcher match = comm.pattern.matcher(input);
            if (match.matches()) {
                comm.run(match, gamesys);
                return comm;
            }
        }
        return null;
    }

    /**
     * Executes the command
     * 
     * @param match The regex Matcher
     * @param gs    The gamesystem to operate on
     * @throws InputException if the input dosen't match the expected
     */
    public abstract void run(Matcher match, GameSystem gs) throws InputException;

    /**
     * 
     * @return Whether the interaction is still running
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Quits the user interaction
     */
    protected void quit() {
        running = false;
    }
}
