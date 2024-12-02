import java.util.Scanner;

public class Program {
    private static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        boolean exit = false;

        // Television instance to manipulate
        Television television = new Television();

        while(!exit) {
            System.out.println(menu());
            int option = readInteger("?> ");

            switch (option) {
                case 1 -> showStatus(television);
                case 2 -> showChannelList(television);
                case 3 -> tunePosition(television);
                case 4 -> toggleFavorite(television);
                case 5 -> addChannel(television);
                case 6 -> removeChannel(television);
                case 7 -> swapChannels(television);
                case 8 -> findChannel(television);
                case 9 -> applyFactorySettings(television);
                case 0 -> exit = true;
                default -> System.out.println("[Invalid option. Try again...]");
            }

        }

        System.out.println("TV turned off. Bye!");
        sc.close();
    }

    private static void showStatus(Television tv) {
        System.out.println(tv);
    }

    private static void showChannelList(Television tv) {
        System.out.println("-> List of current channels:");
        System.out.println(tv.channelList());
    }

    private static void tunePosition(Television tv) {
        int pos = readInteger("Position?: ");
        boolean result = tv.tunePosition(pos);

        System.out.println(result ? "[OK]" : "[Error]");
    }

    private static void toggleFavorite(Television tv) {
        boolean result = tv.toggleFavorite();

        System.out.println(result ? "[OK]" : "[Error]");
    }

    private static void addChannel(Television tv) {
        String name = readString("Channel name?: ");
        int freq = readInteger("Frequency?: ");

        Channel channel = new Channel(name, freq);

        boolean result = tv.addChannel(channel);

        System.out.println(result ? "[OK]" : "[Error]");
    }

    private static void removeChannel(Television tv) {
        int pos = readInteger("Position?: ");
        boolean result = tv.removeChannel(pos);

        System.out.println(result ? "[OK]" : "[Error]");
    }

    private static void swapChannels(Television tv) {
        int pos1 = readInteger("Position #1?: ");
        int pos2 = readInteger("Position #2?: ");

        boolean result = tv.swapChannels(pos1, pos2);

        System.out.println(result ? "[OK]" : "[Error]");
    }

    private static void findChannel(Television tv) {
        String query = readString("Search query?: ");

        int foundPos = tv.findChannelPosition(query);

        System.out.println(foundPos > -1 ? "-> Channel found at position " + foundPos : "-> Channel not found!");
    }

    private static void applyFactorySettings(Television tv) {
        char answer = readChar("Are you sure (s/n)? ", 's', 'n');
        if(answer == 's') {
            tv.factorySettings();
            System.out.println("[OK]");
        }
    }

    private static String menu() {
        StringBuilder sb = new StringBuilder();

        sb.append("-- TV Menu --").append("\n");
        sb.append("1. Status").append("\n");
        sb.append("2. Channel list").append("\n");
        sb.append("3. Tune position").append("\n");
        sb.append("4. Toggle favorite").append("\n");
        sb.append("5. Add channel").append("\n");
        sb.append("6. Remove channel").append("\n");
        sb.append("7. Swap channels").append("\n");
        sb.append("8. Find channel").append("\n");
        sb.append("9. Apply factory settings").append("\n");
        sb.append("0. Turn off").append("\n");
        sb.append("-- ").append("\n");

        return sb.toString();
    }

    private static int readInteger(String prompt) {
        boolean ok;
        int value = 0;
        do {
            System.out.print(prompt);
            String line = sc.nextLine();
            try {
                value = Integer.parseInt(line);
                ok = true;
            } catch(Exception e) {
                System.out.println("[Expecting a number. Try again...]");
                ok = false;
            }
        } while(!ok);

        return value;
    }

    private static String readString(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    private static char readChar(String prompt, char... validChars) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if (input.length() == 1) {
                char inputChar = Character.toLowerCase(input.charAt(0));
                for (char validChar : validChars) {
                    if (Character.toLowerCase(validChar) == inputChar) {
                        return inputChar;
                    }
                }
            }

            System.out.println("Invalid input. Please enter one of the following characters: "
                    + String.valueOf(validChars));
        }
    }
}
