package tv.trashless.trashutils.gui;

import de.rapha149.signgui.SignGUI;
import de.rapha149.signgui.SignGUIBuilder;
import de.rapha149.signgui.exception.SignGUIVersionException;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import tv.trashless.trashutils.utils.Phrases;

public class SignBuilders {
    public static SignGUIBuilder INPUT() {
        try {
            return SignGUI.builder()
                    .setLines(Phrases.SIGN_SEPARATOR, Phrases.VALUE_BELOW, null, Phrases.SIGN_SEPARATOR)
                    .setType(Material.OAK_SIGN)
                    .setColor(DyeColor.BLACK);
        } catch (SignGUIVersionException e) {
            throw new RuntimeException(e);
        }
    }
}
