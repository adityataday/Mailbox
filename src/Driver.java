import java.io.*;
import java.util.Scanner;

public class Driver {

    static Mailbox mailbox;     // Stores the main mailbox that will used by the application. This mailbox should be instantiated in the main method.

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Scanner userInput = new Scanner(System.in);
        String choice = "";

        File file = new File("mailbox.obj");
        boolean exists = file.exists();

        if (exists) {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            mailbox = (Mailbox) objectInputStream.readObject();

        } else {
            System.out.println("Previous save not found, starting with an empty mailbox");
            mailbox = new Mailbox();
        }


        System.out.println();

        do {
            try {
                mailbox.printMailbox();
                System.out.println();
                System.out.printf("A – Add folder\n");
                System.out.printf("R – Remove folder\n");
                System.out.printf("C – Compose email\n");
                System.out.printf("F – Open folder\n");
                System.out.printf("I – Open Inbox\n");
                System.out.printf("T – Open Trash\n");
                System.out.printf("E – Empty Trash\n");
                System.out.printf("Q - Exit\n\n");

                System.out.printf("Select an Operation: ");
                choice = userInput.nextLine();

                if (choice.equalsIgnoreCase("A")) {
                    Folder folder = new Folder();
                    System.out.println("Enter folder name: ");
                    folder.setName(userInput.nextLine());
                    mailbox.addFolder(folder);

                    System.out.println("Folder added");

                } else if (choice.equalsIgnoreCase("R")) {
                    System.out.println("Enter the name: ");
                    mailbox.deleteFolder(userInput.nextLine());

                    System.out.println("Folder removed");


                } else if (choice.equalsIgnoreCase("C")) {
                    mailbox.composeEmail();

                } else if (choice.equalsIgnoreCase("F")) {

                    System.out.println("Enter the folder name: ");

                    for (int i = 0; i < mailbox.getFolders().size(); i++) {
                        if (mailbox.getFolders().get(i).getName().equalsIgnoreCase(userInput.nextLine())) {
                            menuOptions(mailbox.getFolders().get(i));
                        }
                    }
                } else if (choice.equalsIgnoreCase("I")) {
                    menuOptions(mailbox.getInbox());
                } else if (choice.equalsIgnoreCase("T")) {
                    menuOptions(mailbox.getTrash());
                } else if (choice.equalsIgnoreCase("E")) {
                    mailbox.clearTrash();
                }

                System.out.println();

            } catch (Exception e) {
                System.out.println(e.getMessage());

                System.out.println();
            }

        } while (!choice.equalsIgnoreCase("Q"));


        ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("mailbox.obj"));
        outputStream.writeObject(mailbox);
        outputStream.close();


        System.exit(0);
    }

    public static void menuOptions(Folder folder) {
        Scanner userInput = new Scanner(System.in);
        String choice;

        try {
            do {
                folder.printFolder();
                System.out.println();
                System.out.printf("M – Move email\n");
                System.out.printf("D – Delete email\n");
                System.out.printf("V – View email contents\n");
                System.out.printf("SA – Sort by subject ascending\n");
                System.out.printf("SD – Sort by subject descending\n");
                System.out.printf("DA – Sort by date ascending\n");
                System.out.printf("DD – Sort by date descending\n");
                System.out.printf("R – Return to main menu\n\n");

                System.out.printf("Select an Operation: ");
                choice = userInput.nextLine();

                if (choice.equalsIgnoreCase("M")) {
                    System.out.println("Enter the index number of the email: ");
                    int index = userInput.nextInt();
                    userInput.nextLine();
                    System.out.println("Enter the target folder name: ");
                    String targetFolderName = userInput.nextLine();
                    Folder targetFolder = new Folder();
                    targetFolder.setName(targetFolderName);
                    mailbox.moveEmail(folder.removeEmail(index), targetFolder);

                } else if (choice.equalsIgnoreCase("D")) {
                    System.out.println("Enter the index number of the email: ");
                    int index = userInput.nextInt();
                    mailbox.deleteEmail(folder.removeEmail(index));
                    userInput.nextLine();

                } else if (choice.equalsIgnoreCase("V")) {
                    System.out.println("Enter the index number of the email: ");
                    int index = userInput.nextInt();
                    System.out.println(folder.getEmails().get(index).toString());
                    userInput.nextLine();

                } else if (choice.equalsIgnoreCase("SA")) {
                    folder.sortBySubjectAscending();
                } else if (choice.equalsIgnoreCase("SD")) {
                    folder.sortBySubjectDescending();
                } else if (choice.equalsIgnoreCase("DA")) {
                    folder.sortByDateAscending();
                } else if (choice.equalsIgnoreCase("DD")) {
                    folder.sortByDateDescending();
                }

            } while (!choice.equalsIgnoreCase("r"));
        } catch (Exception e) {
            System.out.println(e.getMessage());

            System.out.println();
        }
    }
}
