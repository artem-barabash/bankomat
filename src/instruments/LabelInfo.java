package instruments;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class LabelInfo {
    public static void TextDisplayPopup(String strTitle, String strText,
                                        int iDelayInSeconds, int iX_Location, int iY_Location, int iMessageType) {
        final JOptionPane optionPane = new JOptionPane(strText,
                iMessageType, JOptionPane.DEFAULT_OPTION,
                null, new Object[]{}, null);
        final JDialog dialog = new JDialog();
        dialog.setTitle(strTitle);
        dialog.setModal(false);
        dialog.setContentPane(optionPane);
        dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        dialog.pack();
        dialog.setLocation(iX_Location, iY_Location);

        Timer timer = new Timer(iDelayInSeconds*1000, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                dialog.dispose();
            }
        });
        timer.setRepeats(false);

        timer.start();
        dialog.setVisible(true);
    }
}
