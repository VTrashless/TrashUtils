package tv.trashless.trashutils.gui;

import de.rapha149.signgui.SignGUI;
import de.rapha149.signgui.SignGUIBuilder;
import de.rapha149.signgui.exception.SignGUIVersionException;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import tv.trashless.trashutils.utils.FoodLevel;
import tv.trashless.trashutils.utils.Health;
import tv.trashless.trashutils.utils.Phrases;

import java.util.Collections;

public class Signs {
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

    public static SignGUI INPUT_NEW_MAX_HEALTH() {
        return INPUT()
               .setHandler((player, result) -> {
                   String input = result.getLineWithoutColor(2);

                   if (!input.isEmpty()) {
                       double newMaxHealth = Double.parseDouble(input);
                       Health.setMax(player, newMaxHealth);
                   }

                   return Collections.emptyList();
               })
               .build();
    }

    public static SignGUI INPUT_NEW_MAX_FOOD_LEVEL() {
        return INPUT()
                .setHandler((player, result) -> {
                    String input = result.getLineWithoutColor(2);

                    if (!input.isEmpty()) {
                        int newMaxFoodLevel = Integer.parseInt(input);
                        FoodLevel.setMax(player, newMaxFoodLevel);
                    }

                    return Collections.emptyList();
                })
                .build();
    }
}
