import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Mailbox implements Serializable {


    private Folder inbox;                   // Stores the inbox, which is a special folder which should never be allowed to be deleted or renamed. All new emails go here.
    private Folder trash;                   // Stores the trash, which is a special folder which should never be allowed to be deleted or renamed. When an email is deleted, it is moved here.
    private ArrayList<Folder> folders;      // Stores all of the custom folders contained in the mailbox.


    public Mailbox() {
        inbox = new Folder();
        trash = new Folder();
        folders = new ArrayList<>();
    }

    public Folder getInbox() {
        return inbox;
    }

    public void setInbox(Folder inbox) {
        this.inbox = inbox;
    }

    public Folder getTrash() {
        return trash;
    }

    public void setTrash(Folder trash) {
        this.trash = trash;
    }

    public ArrayList<Folder> getFolders() {
        return folders;
    }

    public void setFolders(ArrayList<Folder> folders) {
        this.folders = folders;
    }


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
        boolean correctFolder = false;

        if (folders.isEmpty())
            throw new Exception("Are are no custom folder so delete function cannot be implemented");

        if (name.equalsIgnoreCase("inbox") || name.equalsIgnoreCase("trash"))
            throw new Exception("Inbox and Trash cannot be deleted");

        for (Folder item : folders) {
            if (item.getName().equalsIgnoreCase(name)) {
                folders.remove(item);
                correctFolder = true;
                break;
            }
        }

        if (!correctFolder)
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
        Date date = new Date();
        timestamp.setTime(date);

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

        for (int i = 0; i < folders.size(); i++) {
            if (folders.get(i).getName().equalsIgnoreCase(target.getName())) {
                folders.get(i).addEmail(email);
                folderFound = true;

            } else if (target.getName().equalsIgnoreCase("Trash")) {
                trash.addEmail(email);
                folderFound = true;
            } else if (target.getName().equalsIgnoreCase("Inbox")) {
                inbox.addEmail(email);
                folderFound = true;
            }
            if (folderFound) {
                System.out.printf("\"%s\" moved to %s", email.getSubject(), target.getName());
                break;
            }
        }


        if (folderFound) {
            System.out.println("Folder not found moving to inbox");
            inbox.addEmail(email);
        }
    }

    public Folder getFolder(String name) {
        for (Folder item : folders) {
            if (item.getName().equalsIgnoreCase(name))
                return item;
        }
        return null;
    }


    public void printMailbox() {
        System.out.printf("Mailbox:\n--------\nInbox\nTrash\n");
        if (folders.isEmpty()) {

        } else {
            for (Folder item : folders) {
                System.out.println(item.getName());
            }
        }
    }
}
