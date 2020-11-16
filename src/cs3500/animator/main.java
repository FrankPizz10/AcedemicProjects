package cs3500.animator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import cs3500.animator.hw5.IModel;
import cs3500.animator.hw5.IShape;
import cs3500.animator.hw5.Model;
import cs3500.animator.hw5.Rectangle;
import cs3500.animator.util.AnimationBuilder;
import cs3500.animator.util.AnimationReader;
import cs3500.animator.view.IView;
import cs3500.animator.view.Commands;
import cs3500.animator.view.Commands.ViewBuilder;

public final class main {
    static final class WriteFile {
        private final String path;

        WriteFile(String filePath) {
            this.path = filePath;
        }

        public void writeToFile(String text) throws IOException {
            FileWriter write = new FileWriter(this.path);
            PrintWriter writer = new PrintWriter(write);

            writer.print(text);
            writer.close();
        }
    }


    public static void main(String[] args) {

        AnimationBuilder<IModel> builder = new Model.Builder();

        Commands commands = new Commands();
        commands.getArgs(args);

        FileReader fr;
        try {
            fr = new FileReader(commands.getInput());
        } catch(FileNotFoundException e) {
            throw new IllegalArgumentException("Invalid File!");
        }

        IModel model = AnimationReader.parseFile(fr, builder);
        commands.setModel(model);

        IView view = commands.getView();

        WriteFile fileWriter = new WriteFile(commands.getOutput());

        switch (commands.getViewType()) {
            case "text":
                try {
                    fileWriter.writeToFile(view.textView());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            case "visual":
                view.visualView();
                break;
            case "svg":
                view.svgView();
        }
    }
}
