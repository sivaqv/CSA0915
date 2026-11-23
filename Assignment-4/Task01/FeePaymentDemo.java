import javax.swing.*;import java.awt.*;import java.math.BigDecimal;
public class FeePaymentDemo {
 static class PaymentService {
  long validateAndConvertToPaise(String register,String amount) {
   if(register==null||!register.trim().matches("\\d{9}")) throw new IllegalArgumentException("Register number must contain 9 digits");
   if(amount==null||!amount.trim().matches("\\d{1,7}(\\.\\d{1,2})?")) throw new IllegalArgumentException("Enter a valid amount");
   BigDecimal value=new BigDecimal(amount.trim());
   if(value.signum()<=0||value.compareTo(new BigDecimal("500000"))>0) throw new IllegalArgumentException("Amount outside allowed range");
   return value.movePointRight(2).longValueExact();
  }
 }
 public static void main(String[]args) {
  SwingUtilities.invokeLater(()->{
   JFrame frame=new JFrame("Campus Fee Payment"); JTextField reg=new JTextField(12),amount=new JTextField(10); JLabel status=new JLabel("Enter details");
   JButton pay=new JButton("Validate payment"); JPanel panel=new JPanel(new GridLayout(0,2,6,6));
   panel.add(new JLabel("Register number"));panel.add(reg);panel.add(new JLabel("Amount (Rs)"));panel.add(amount);panel.add(pay);panel.add(status);
   pay.addActionListener(e->{try{long paise=new PaymentService().validateAndConvertToPaise(reg.getText(),amount.getText());status.setText("Valid: "+paise+" paise");}catch(Exception ex){status.setText(ex.getMessage());}});
   frame.add(panel);frame.setSize(390,180);frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);frame.setLocationRelativeTo(null);frame.setVisible(true);
  });
 }
}