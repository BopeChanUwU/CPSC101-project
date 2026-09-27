package score4.view.gameboy;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.function.BiConsumer;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import score4.model.player.ClassicEvaluator;
import score4.model.player.Evaluator;
import score4.model.player.PracticalEvaluator;

/**
 * This file is part of a Score4 game
 *
 * <p> Implements an AISelectionPanel shown before an AI vs AI game starts,
 * letting the user pick which evaluator (scoring heuristic) each colour
 * uses, so different evaluators can be compared against each other
 * directly from the GUI.
 *
 * @author Tristen Sandhu
 * @version 1
 */
public class AISelectionPanel extends JPanel {

    private static final String CLASSIC = "Classic";
    private static final String PRACTICAL = "Practical";

    private final JComboBox<String> whiteChoice = new JComboBox<>(new String[]{CLASSIC, PRACTICAL});
    private final JComboBox<String> blackChoice = new JComboBox<>(new String[]{CLASSIC, PRACTICAL});

    /**
     * constructs an AISelectionPanel
     * @param onStart BiConsumer invoked with the chosen (white, black) evaluators when Start is clicked
     * @param onBack Runnable invoked when Back is clicked, to return to the main menu
     */
    public AISelectionPanel(BiConsumer<Evaluator, Evaluator> onStart, Runnable onBack) {

        setPreferredSize(new Dimension(576, 720));
        setBackground(new Color(73, 71, 134));
        setLayout(new GridLayout(6, 1));

        JLabel title = new JLabel("<html><H1>AI vs AI setup</H1></html>");
        title.setForeground(Color.WHITE);
        add(title);

        add(labeledRow("White's algorithm:", whiteChoice));
        add(labeledRow("Black's algorithm:", blackChoice));

        add(new JLabel());

        JButton start = new JButton("Start");
        add(start);
        start.addActionListener(e -> onStart.accept(evaluatorFor(whiteChoice), evaluatorFor(blackChoice)));

        JButton back = new JButton("Back");
        add(back);
        back.addActionListener(e -> onBack.run());

        setVisible(true);
    }

    /**
     * builds a single labelled dropdown row
     * @param text String the row's label
     * @param combo JComboBox<String> the dropdown for that row
     * @return JPanel the assembled row
     */
    private JPanel labeledRow(String text, JComboBox<String> combo) {

        JPanel row = new JPanel(new BorderLayout());
        row.setBackground(new Color(73, 71, 134));

        JLabel label = new JLabel("  " + text);
        label.setForeground(Color.WHITE);

        row.add(label, BorderLayout.WEST);
        row.add(combo, BorderLayout.EAST);
        return row;
    }

    /**
     * maps a dropdown's current selection to the evaluator it names
     * @param choice JComboBox<String> the dropdown to read
     * @return Evaluator the chosen evaluator
     */
    private Evaluator evaluatorFor(JComboBox<String> choice) {

        return PRACTICAL.equals(choice.getSelectedItem()) ? new PracticalEvaluator() : new ClassicEvaluator();
    }
}
