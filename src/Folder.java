import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Folder implements Serializable {

    private ArrayList<Email> emails;            // Stores emails in this arrayList.
    private String name;                        // Stores the name of the folder.
    private String currentSortingMethod;        //S tores the current sorting method (however that you see fit) so that emails added can be properly sorted without having to first resort the folder.


    public Folder() {
        emails = new ArrayList<>();
    }

    public ArrayList<Email> getEmails() {
        return emails;
    }

    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    public void setCurrentSortingMethod(String currentSortingMethod) {
        this.currentSortingMethod = currentSortingMethod;
    }

    public void addEmail(Email email) throws Exception {
        emails.add(email);

        if (currentSortingMethod == null || currentSortingMethod.equalsIgnoreCase("DD"))
            sortByDateDescending();

        else if (currentSortingMethod.equalsIgnoreCase("SA"))
            sortBySubjectAscending();

        else if (currentSortingMethod.equalsIgnoreCase("SD"))
            sortBySubjectDescending();

        else if (currentSortingMethod.equalsIgnoreCase("SA"))
            sortByDateAscending();
    }

    public Email removeEmail(int index) {
        return emails.remove(index);
    }

    public ArrayList<Email> sortBySubjectAscending() throws Exception {
        if (emails.isEmpty())
            throw new Exception("There are no emails to sort. Try adding an email first");

        Collections.sort(emails, Comparator.comparing(Email::getSubject, Comparator.naturalOrder()));

        currentSortingMethod = "SA";

        return emails;
    }

    public ArrayList<Email> sortBySubjectDescending() throws Exception {
        if (emails.isEmpty())
            throw new Exception("There are no emails to sort. Try adding an email first");

        Collections.sort(emails, Comparator.comparing(Email::getSubject, Comparator.reverseOrder()));

        currentSortingMethod = "SD";

        return emails;
    }

    public ArrayList<Email> sortByDateAscending() throws Exception {
        if (emails.isEmpty())
            throw new Exception("There are no emails to sort. Try adding an email first");

        Collections.sort(emails, Comparator.comparing(Email::getTimestamp, Comparator.naturalOrder()));

        currentSortingMethod = "DA";

        return emails;
    }

    public ArrayList<Email> sortByDateDescending() throws Exception {
        if (emails.isEmpty())
            throw new Exception("There are no emails to sort. Try adding an email first");

        Collections.sort(emails, Comparator.comparing(Email::getTimestamp, Comparator.reverseOrder()));

        currentSortingMethod = "DD";

        return emails;
    }

    public void printFolder() {
        System.out.printf("%15s | %30s | %25s\n", "Index", "Time", "Subject");
        System.out.printf("-------------------------------------------------------------------------------------------\n");
        for (int i = 0; i < emails.size(); i++) {
            System.out.printf("%15s | %30s | %25s\n", i, emails.get(i).getTimestamp().getTime(), emails.get(i).getSubject());
        }

    }
}
