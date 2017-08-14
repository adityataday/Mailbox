import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Mailbox {

    private Folder inbox;                   // Stores the inbox, which is a special folder which should never be allowed to be deleted or renamed. All new emails go here.
    private Folder trash;                   // Stores the trash, which is a special folder which should never be allowed to be deleted or renamed. When an email is deleted, it is moved here.
    private ArrayList<Folder> folders;      // Stores all of the custom folders contained in the mailbox.
    public static Mailbox mailbox;          // Stores the main mailbox that will used by the application. This mailbox should be instantiated in the main method.


    public void addFolder(Folder folder) throws Exception {
        if (folders.isEmpty())
            folders.add(folder);
        else {
            for (Folder item : folders) {
                if (item.getName().equalsIgnoreCase(folder.getName()))
                    throw new Exception("Folder name already exist");
                else
                    folders.add(folder);
            }
        }
    }

    public void deleteFolder(String name) throws Exception {
        int originalSize = folders.size();

        if (folders.isEmpty())
            throw new Exception("Are are no custom folder so delete function cannot be implemented");

        if (name.equalsIgnoreCase("inbox") || name.equalsIgnoreCase("trash"))
            throw new Exception("Inbox and Trash cannot be deleted");

        for (Folder item : folders) {
            if (item.getName().equalsIgnoreCase(name))
                folders.remove(item);
        }

        if (folders.size() == originalSize)
            throw new Exception("Folder not found");
    }

    public void composeEmail() throws Exception {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter recipient (To): ");
        String to = input.nextLine();

        System.out.println("Enter carbon copy recipients (CC): ");
        String cc = input.nextLine();

        System.out.println("Enter blind carbon copy recipients (BCC): ");
        String bcc = input.nextLine();

        System.out.println("Enter subject line: ");
        String subject = input.nextLine();

        System.out.println("Enter body: ");
        String body = input.nextLine();

        GregorianCalendar timestamp = new GregorianCalendar();
        timestamp.setTimeInMillis(System.currentTimeMillis());

        Email email = new Email(to, cc, bcc, subject, body, timestamp);

        inbox.addEmail(email);

        System.out.println("Email successfully added to Inbox");

    }

    public void deleteEmail(Email email) throws Exception {
        trash.addEmail(email);
    }

    public void clearTrash() {
        trash.getEmails().clear();
    }

    public void moveEmail(Email email, Folder target) throws Exception {
        boolean folderFound = false;

        for (Folder item : folders) {
            if (item.getName().equalsIgnoreCase(target.getName())) {
                item.addEmail(email);
                folderFound = true;
            }

        }

        if (!folderFound)
            inbox.addEmail(email);
    }

    public Folder getFolder(String name) {
        for (Folder item : folders) {
            if (item.getName().equalsIgnoreCase(name))
            return item;
        }
        return null;
    }


}
