package doko;

import picocli.CommandLine;

public final class Main {
    public static void main(String[] args) {
        new CommandLine(new Command()).execute(args);
    }    
}
