package ejercicios;

// Interfaz Command
interface Command {
    void execute();
    void undo();
}

// Clase Luz
class Light {
    public void turnOn() {
        System.out.println("Luz encendida.");
    }

    public void turnOff() {
        System.out.println("Luz apagada.");
    }
}

// Clase Ventilador
class Fan {
    public void turnOn() {
        System.out.println("Ventilador encendido.");
    }

    public void turnOff() {
        System.out.println("Ventilador apagado.");
    }
}

// Comandos concretos
class LightOnCommand implements Command {
    private Light light;

    public LightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.turnOn();
    }

    @Override
    public void undo() {
        light.turnOff();
    }
}

// Clase ControlRemoto
class RemoteControl {
    private Command lastCommand;

    public void setCommand(Command command) {
        lastCommand = command;
    }

    public void pressButton() {
        lastCommand.execute();
    }

    public void pressUndo() {
        lastCommand.undo();
    }
}

// Main
public class CommandPatternDemo {
    public static void main(String[] args) {
        Light light = new Light();
        Command lightOn = new LightOnCommand(light);

        RemoteControl remote = new RemoteControl();
        remote.setCommand(lightOn);
        remote.pressButton(); // Encender luz
        remote.pressUndo();   // Apagar luz
    }
}
