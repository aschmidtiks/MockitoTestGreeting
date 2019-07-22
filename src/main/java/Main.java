public class Main {

    public static void main(String[] args) {

    }

    public void greet(String input) {
        generatePrintable(input);
    }

    public String generatePrintable(String input) {
        String printable = "Hello, ";
        if (input == null) {
            printable += "my friend";
        } else {
            printable += input;
        }

        return printable;
    }

    public void print(String printable) {
        System.out.println("Hello, " + printable + ".");
    }
}
