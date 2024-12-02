package experiencia;

// Interface Command
interface Command {
    void execute();
}

// Receptor
class Television {
    public void turnOn() {
        System.out.println("Televisión encendida.");
    }

    public void turnOff() {
        System.out.println("Televisión apagada.");
    }

    public void increaseVolume() {
        System.out.println("Volumen aumentado.");
    }

    public void decreaseVolume() {
        System.out.println("Volumen disminuido.");
    }

    public void changeChannel(int channel) {
        System.out.println("Canal cambiado a: " + channel);
    }
}

// Comandos concretos
class TurnOnCommand implements Command {
    private Television tv;

    public TurnOnCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOn();
    }
}

class TurnOffCommand implements Command {
    private Television tv;

    public TurnOffCommand(Television tv) {
        this.tv = tv;
    }

    @Override
    public void execute() {
        tv.turnOff();
    }
}

// Main
public class CommandPattern {
    public static void main(String[] args) {
        Television tv = new Television();

        Command turnOn = new TurnOnCommand(tv);
        Command turnOff = new TurnOffCommand(tv);

        turnOn.execute();
        turnOff.execute();
    }
}
