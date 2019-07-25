import java.util.ArrayList;
import java.util.List;

public class Main {

    boolean capitalInput = false;
    boolean lowerInput = false;

    private boolean inputUpperCase = false;

    public static void main(String[] args) {
        //main halt
    }

    public void greet(String[] input) {
        generatePrintable(input);
    }

    public String generatePrintable(String[] input) {
        String printable = "";
        List<List<String>> separatedInput;

        separatedInput = separateInput(input);
        printable = buildPrintable(separatedInput);
        return printable;
    }

    private List<List<String>> separateInput(String[] input) {
        List<String> listOutputCapital = new ArrayList<String>();
        List<String> listOutputNormal = new ArrayList<String>();
        List<List<String>> listSeparatedInput = new ArrayList<List<String>>();

        if (isInputValid(input)) {
            for (int i = 0; i < input.length; i++) {
                if (checkInputIsCapitalCase(input[i])) {
                    setCapitalInput(true);
                    listOutputCapital.add(input[i]);

                } else if (!checkInputIsCapitalCase(input[i])) {
                    setLowerInput(true);
                    listOutputNormal.add(input[i]);
                }
            }
        } else {
            listOutputNormal.add("my friend");
            lowerInput = true;
        }

        if (!listOutputNormal.isEmpty()) {
            listSeparatedInput.add(0, listOutputNormal);
        }
        if (!listOutputCapital.isEmpty() && !listOutputNormal.isEmpty()) {
            listSeparatedInput.add(1, listOutputCapital);
        } else if (!listOutputCapital.isEmpty() && listOutputNormal.isEmpty()) {
            listSeparatedInput.add(0, listOutputCapital);
        }

        return listSeparatedInput;
    }

    private String buildPrintable(List<List<String>> separatedInput) {
        StringBuilder printable = new StringBuilder();
        String output = "";

        if (lowerInput && !capitalInput) {
            if (separatedInput.get(0) != null) {
                printable.append("Hello, ");
                printable.append(buildPrintableLowerCase(separatedInput.get(0)));
                printable.append(".");
            }
        } else if (!lowerInput && capitalInput) {
            if (separatedInput.get(0) != null) {
                printable.append("HELLO, ");
                printable.append(buildPrintableCapitalCase(separatedInput.get(0)));
                printable.append("!");
            }
        } else if (lowerInput) {
            if (separatedInput.get(0) != null) {
                printable.append("Hello, ");
                printable.append(buildPrintableLowerCase(separatedInput.get(0)));
                printable.append(". ");
            }
            if (separatedInput.get(1) != null) {
                printable.append("AND HELLO ");
                printable.append(buildPrintableLowerCase(separatedInput.get(1)));
                printable.append("!");
            }
        }

        output += printable.toString();
        return output;
    }

    String buildPrintableLowerCase(List<String> separatedInput) {
        StringBuilder stringBuilderNormal = new StringBuilder();
        for (int i = 0; i < separatedInput.size(); i++) {
            if (i == 0) {
                stringBuilderNormal.append(separatedInput.get(i));
            } else if (i > 0 && i < separatedInput.size() - 1) {
                stringBuilderNormal.append(", ");
                stringBuilderNormal.append(separatedInput.get(i));
            } else if (i > 0 && i == separatedInput.size() - 1) {
                stringBuilderNormal.append(" and ");
                stringBuilderNormal.append(separatedInput.get(i));
            }
        }
        return stringBuilderNormal.toString();
    }

    String buildPrintableCapitalCase(List<String> separatedInput) {
        StringBuilder stringBuilderCapital = new StringBuilder();
            for (int i = 0; i < separatedInput.size(); i++) {
                if (i == 0) {
                    stringBuilderCapital.append(separatedInput.get(i));
                } else if (i > 0 && i < separatedInput.size() - 1) {
                    stringBuilderCapital.append(", ");
                    stringBuilderCapital.append(separatedInput.get(i));
                } else if (i > 0 && i == separatedInput.size() - 1) {
                    stringBuilderCapital.append(" AND ");
                    stringBuilderCapital.append(separatedInput.get(i));
                }
            }
        return stringBuilderCapital.toString();
    }

    public void print(String printable) {
        System.out.println("Hello, " + printable + ".");
    }

    private boolean isInputValid(String[] input) {
        for (int i = 0; i < input.length; i++) {
            if (input[i] == null) {
                return false;
            }
        }
        return true;
    }

    private boolean checkInputIsCapitalCase(String input) {
         return input.equals(input.toUpperCase());
    }

    public boolean isCapitalInput() {
        return capitalInput;
    }
    public void setCapitalInput(boolean capitalInput) {
        this.capitalInput = capitalInput;
    }
    public boolean isLowerInput() {
        return lowerInput;
    }
    public void setLowerInput(boolean lowerInput) {
        this.lowerInput = lowerInput;
    }
    public boolean isInputUpperCase() {
        return inputUpperCase;
    }
    public void setInputUpperCase(boolean inputUpperCase) {
        this.inputUpperCase = inputUpperCase;
    }
}
