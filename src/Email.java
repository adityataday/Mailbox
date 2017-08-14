import java.io.Serializable;
import java.util.GregorianCalendar;

public class Email implements Serializable{

    private String to;                      // The String literal which stores the “to” field.
    private String cc;                      // The String literal which stores the “cc” field.
    private String bcc;                     // The String literal which stores the “bcc” field.
    private String subject;                 // The String literal which stores the “subject” field.
    private String body;                    // The String literal which stores all of the text in the email’s body.
    private GregorianCalendar timestamp;   // Represents the time that this email was created.


    public Email(String to, String cc, String bcc, String subject, String body, GregorianCalendar timestamp) {
        this.to = to;
        this.cc = cc;
        this.bcc = bcc;
        this.subject = subject;
        this.body = body;
        this.timestamp = timestamp;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getBcc() {
        return bcc;
    }

    public void setBcc(String bcc) {
        this.bcc = bcc;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public GregorianCalendar getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(GregorianCalendar timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "\n" + "to = " + to + "\n" +
                "cc = " + cc + "\n" +
                "bcc = " + bcc + "\n" +
                "subject = " + subject + "\n" +
                "" + body + "\n";
    }
}
