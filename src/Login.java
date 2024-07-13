import javax.swing.*;
import java.sql.*;
import java.awt.*;//used to import image claases because these are not present in swing
import java.awt.event.*;//for action listener
public class Login extends JFrame implements ActionListener {
    JButton login, signup, clear;//tino button ko globally define kr diya taki inko hum constructor ke bahar bhi use kr saku
    JTextField  cardText;
    JPasswordField pinText;//password field is used to hide the pin

    Login() {

        setSize(800, 700);//it is used to make the frame
        setLocation(350, 100);//used to set the location of frame where you want to open in your window , x represent from left side and y represents  from top, by default frame opens in the top left corner
        setTitle("Automated Teller Machine");
       /* ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("banking management system.iml/pic1.jpg"));


        Image i2=i1.getImage().getScaledInstance(100,100,Image.SCALE_DEFAULT);//image ko chota krne ke liye
        ImageIcon i3=new ImageIcon(i2);//i2 ko imageicon m convert krne ke liye because we cannot add image into label like we did in imageicon
        JLabel label=new JLabel(i3);   //image icon ko directly nhi daal sakte frma e pr isliye jlabel use krte h
        add(label);

        */
        setLayout(null);//bceause we want to use our own custome layout
        /*label.setBounds(70,10,100,100);//used to set the location of image in the frame

         */


        JLabel text = new JLabel("Welcome to ATM");
        text.setBounds(200, 0, 400, 80);
        text.setFont(new Font("Osward", Font.BOLD, 30));

        add(text);
        JLabel cardno = new JLabel("Card no:");
        cardno.setBounds(100, 100, 150, 50);
        text.setFont(new Font("Raleway", Font.BOLD, 30));
        add(cardno);
        JLabel pinno = new JLabel("Pin no:");
        pinno.setBounds(100, 160, 100, 30);
        text.setFont(new Font("Raleway", Font.BOLD, 20));
        add(pinno);
        cardText = new JTextField();
        cardText.setBounds(200, 110, 200, 30);
        add(cardText);
        pinText = new JPasswordField();
        pinText.setBounds(200, 160, 200, 30);
        add(pinText);
        login = new JButton("Sign in");
        login.setBounds(150, 200, 100, 30);
        login.addActionListener(this);
        add(login);

        clear = new JButton("Clear");
        clear.setBounds(350, 200, 100, 30);  // Corrected bounds for clear button
        clear.addActionListener(this);
        add(clear);

        signup = new JButton("Sign up");
        signup.setBounds(200, 250, 100, 30);  // Corrected bounds for signup button
        signup.addActionListener(this);
        add(signup);

    }

    public void actionPerformed(ActionEvent ae) {//interface ko implements krane ke liye uske ander jitne method hote h unhe override krna paadta h isliye kra
//button ke click krne pr kya action perform krna h bo yha define krte h,isi ke liye hum button ko globally define bhi krte h
        if (ae.getSource() == clear) {
            cardText.setText("");
            pinText.setText("");

        } else if (ae.getSource() == signup) {
            setVisible(false);//signup button dabaenge to ye bala page band ho jaega or next signupone(application form) bala page khul jaega
            new signupOne().setVisible(true);
        } else if (ae.getSource() == login) {
            con c = new con();
         //   String cardnumber = cardText.getText();
        //    String pinnumber = pinText.getText();
            String cardnumber = cardText.getText().trim();  // trim to remove leading/trailing spaces
            String pinnumber = new String(pinText.getPassword());// getPassword() returns char[], convert to String
            String query = "select * from login where cardnumber = '"+ cardnumber + "' and pin ='" + pinnumber + "'";//cardnumber and pin jo login table m h bo jo user jo daalega usse match hone chaiye
            try {
              ResultSet rs=  c.st.executeQuery(query);//ddl command ke liye executequery use krte h//ye query data nikalegi jise hum resultset m store krenge
                if(rs.next()){//iska mtlb agar data nikalna h to user succesfully login kr gya h to login page ko close krna h transaction page ko kholna h
                    setVisible(false);
                    new transaction(pinnumber).setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null,"Incorrect Card Number or Pin");
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
    }
        public static void main (String[]args){

        new Login().setVisible(true);
        }
    }
