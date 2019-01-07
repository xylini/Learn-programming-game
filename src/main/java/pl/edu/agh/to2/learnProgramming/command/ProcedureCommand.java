package pl.edu.agh.to2.learnProgramming.command;

import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import pl.edu.agh.to2.learnProgramming.model.CommandType;
import pl.edu.agh.to2.learnProgramming.model.Loop;
import pl.edu.agh.to2.learnProgramming.model.Turtle;

import java.util.List;

public class ProcedureCommand implements MoveCommand {
    private Turtle turtle;

    private String name;

    private ImageView img;

    private List<Command> levelCommands;

    private List<Command> procedureCommands;

    private List<Loop> loops;

    private int loopCounter;

    public ProcedureCommand(String name, List<Command> procedureCommands, List<Loop> loops) {
        this.loops = loops;
        this.name = name;
        this.procedureCommands = procedureCommands;
        this.img = new ImageView(CommandType.PROCEDURE.getPath());
        img.setFitHeight(40);
        img.setFitWidth(40);
        Tooltip.install(img, new Tooltip(name));
    }

    @Override
    public void execute() {
        int loopCounter = -1;
        int currCounter = 0;
        for (Command command : procedureCommands) {
            command.setLevelCommands(levelCommands);
            command.setLoopCounter(loopCounter);
            if (command.isLoop()) {
                LoopCommand loopCommand = (LoopCommand) command;
                loopCommand.setCurrCounter(currCounter);
                loopCommand.execute();
                loopCounter = loopCommand.getLoopCounter();
                currCounter = loopCommand.getCurrCounter();
            } else {
                MoveCommand moveCommand = (MoveCommand) command;
                moveCommand.setTurtle(turtle);
                moveCommand.prepare();
            }
        }
    }

    @Override
    public void prepare() {

    }

    @Override
    public void setLevelCommands(List<Command> levelCommands) {
        this.levelCommands = levelCommands;
    }

    @Override
    public void setLoopCounter(int loopCounter) {
        this.loopCounter = loopCounter;
    }

    @Override
    public void setTurtle(Turtle turtle) {
        this.turtle = turtle;
    }

    @Override
    public boolean isLoop() {
        return false;
    }

    @Override
    public Node getImage() {
        return img;
    }
}
