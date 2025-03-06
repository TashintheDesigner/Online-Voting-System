import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

public class OnlineVotingSystem {
    private JFrame frame;
    private JRadioButton bnp, jamat, ncp, ab;
    private JButton voteButton, resultButton;
    private JTextField voterIdField;
    private JLabel messageLabel;
    private HashMap<String, Integer> votes;
    private HashSet<Integer> votedIds;
    private JLabel bnpLogo, jamatLogo, ncpLogo, abLogo;

    public OnlineVotingSystem() {
        frame = new JFrame("Online Voting System");
        frame.setSize(400, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(9, 2));

        votes = new HashMap<>();
        votedIds = new HashSet<>();
        votes.put("BNP", 0);
        votes.put("Jamat e Islami", 0);
        votes.put("National Citizen Party", 0);
        votes.put("AB Party", 0);

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

        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                castVote();
            }
        });

        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                showResults();
            }
        });

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

    private void castVote() {
        try {
            int voterId = Integer.parseInt(voterIdField.getText().trim());
            if (voterId < 1 || voterId > 10) {
                messageLabel.setText("Invalid ID! Enter a number between 1 and 10.");
                return;
            }
            if (votedIds.contains(voterId)) {
                messageLabel.setText("You have already voted!");
                return;
            }

            String selectedParty = null;
            if (bnp.isSelected()) selectedParty = "BNP";
            else if (jamat.isSelected()) selectedParty = "Jamat e Islami";
            else if (ncp.isSelected()) selectedParty = "National Citizen Party";
            else if (ab.isSelected()) selectedParty = "AB Party";

            if (selectedParty != null) {
                votes.put(selectedParty, votes.get(selectedParty) + 1);
                votedIds.add(voterId);
                messageLabel.setText("Vote Cast Successfully!");
            } else {
                messageLabel.setText("Please select a party to vote!");
            }
        } catch (NumberFormatException ex) {
            messageLabel.setText("Invalid input! Enter a valid number.");
        }
    }

    private void showResults() {
        StringBuilder result = new StringBuilder("Voting Results:\n");
        String winner = null;
        int maxVotes = 0;

        for (Map.Entry<String, Integer> entry : votes.entrySet()) {
            result.append(entry.getKey()).append(": ").append(entry.getValue()).append(" votes\n");
            if (entry.getValue() > maxVotes) {
                maxVotes = entry.getValue();
                winner = entry.getKey();
            }
        }

        if (winner != null) {
            result.append("\nWinner: ").append(winner);
        }

        JOptionPane.showMessageDialog(frame, result.toString(), "Election Results", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new OnlineVotingSystem();
    }
}
