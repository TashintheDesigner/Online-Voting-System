import javax.swing.*;
import java.awt.*;

public class OnlineVotingSystem {
    private JFrame frame;
    private JRadioButton bnp, jamat, ncp, ab;
    private JButton voteButton, resultButton;
    private JTextField voterIdField;
    private JLabel messageLabel;
    private JLabel bnpLogo, jamatLogo, ncpLogo, abLogo;

    public OnlineVotingSystem() {
        frame = new JFrame("Online Voting System");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(9, 2));

        JLabel voterIdLabel = new JLabel("Enter Voter ID (1-10):");
        voterIdField = new JTextField();
        messageLabel = new JLabel("");

        bnp = new JRadioButton("BNP");
        jamat = new JRadioButton("Jamat e Islami");
        ncp = new JRadioButton("National Citizen Party");
        ab = new JRadioButton("AB Party");

        bnpLogo = new JLabel(resizeIcon(new ImageIcon("bnp_logo.png"), 60, 60));
        jamatLogo = new JLabel(resizeIcon(new ImageIcon("jamat_logo.png"), 60, 60));
        ncpLogo = new JLabel(resizeIcon(new ImageIcon("ncp_logo.png"), 60, 60));
        abLogo = new JLabel(resizeIcon(new ImageIcon("ab_logo.png"), 60, 60));

        ButtonGroup group = new ButtonGroup();
        group.add(bnp);
        group.add(jamat);
        group.add(ncp);
        group.add(ab);

        voteButton = new JButton("Cast Vote");
        resultButton = new JButton("Show Results");

        frame.add(voterIdLabel);
        frame.add(voterIdField);

        frame.add(bnpLogo);
        frame.add(bnp);
        frame.add(jamatLogo);
        frame.add(jamat);
        frame.add(ncpLogo);
        frame.add(ncp);
        frame.add(abLogo);
        frame.add(ab);

        frame.add(voteButton);
        frame.add(resultButton);
        frame.add(messageLabel);

        frame.setVisible(true);
    }

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }

    public static void main(String[] args) {
        new OnlineVotingSystem();
    }
}
