package sns.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Sendmail {
    private String from;  //������ ��� �����ּ�
    private String to;    //�޴� ��� �����ּ�
    private String title; //���� ����
    private String body;  //���� ����
    private String id;    //���� ���̵�
    private String pw;    //���� ��й�ȣ

    public void setTo(String to)     { this.to = to;     }
    public void setFrom(String from) { this.from = from; }
    public void setMail(String title, String body) {
        this.title = title;
        this.body  = body;
    }
    public void setAccount(String id, String pw) {
        this.id = id;
        this.pw = pw;
    }

    // max �ڸ��� �����ڵ带 �����Ѵ�.
    public String AuthCode(int max) {
        String datas = "01234567890abcdefghijklmnopqrstuvwxyz";
        String code = "";
        for (int i = 0; i < max; i++) {
            int rand = (int)(Math.random() * datas.length());
            code += datas.charAt(rand);
        }
        return code;
    }

    // ������ �߼��Ѵ�.
    public boolean sendMail() {
        try {
            System.out.println("���� �߼��� �����մϴ�.");

            Properties clsProp = new Properties();
            clsProp.put("mail.smtp.host", "smtp.naver.com");
            clsProp.put("mail.smtp.port", "465");
            clsProp.put("mail.smtp.auth", "true");
            clsProp.put("mail.smtp.ssl.enable", "true"); 
            clsProp.put("mail.smtp.ssl.trust", "smtp.naver.com");

            Session clsSession = Session.getInstance(clsProp, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(id, pw);
                }
            });

            Message clsMessage = new MimeMessage(clsSession);
            clsMessage.setFrom(new InternetAddress(from));
            clsMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            clsMessage.setSubject(title);
            clsMessage.setContent(body, "text/html;charset=UTF-8");

            Transport.send(clsMessage);
            System.out.println("���� �߼��� ���������� �Ϸ�Ǿ����ϴ�.");

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("���� �߼ۿ� �����Ͽ����ϴ�: " + e.getMessage());
            return false;
        }
    }
}
