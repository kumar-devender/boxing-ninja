package com.ninja.boxing.app.module.ui.component;

import java.util.function.Predicate;
import java.util.stream.Stream;

import com.ninja.boxing.app.module.constant.CommonConstant;
import com.ninja.boxing.app.module.reader.InputReader;
import com.ninja.boxing.app.module.utils.IntRange;
import com.ninja.boxing.app.module.utils.CommonLambda;


/**
 * @author DEVENDER
 *
 * @param <T>
 * 
 * Represent various type of menu
 */
@SuppressWarnings("rawtypes")
public class Menu<T extends Enum> implements Component {

    /**
     * Menu title
     */
    private final String title;

    /**
     * Menu Items
     */
    private final T[] items;

    /**
     * represent acceptable option selection range. 
     * eg if there are 3 menu item in menu then its range is 1 to 3
     */
    private final IntRange acceptableItems;
    
    /**
     * Draw warning message about invalid user option selection
     */
    private final Runnable redrawWithWarningMessage = () -> {
        redraw();
        printMenuFooter(true);
    };

    @SafeVarargs
    public Menu(final String title, final T... items) {
        if (items.length == 0) {
            throw new IllegalArgumentException("Menu item can not be less than one.");
        }
        this.title = title;
        this.items = items;
        this.acceptableItems = IntRange.of(1, items.length);
    }

    

    /* (non-Javadoc)
     * @see com.ninja.boxing.app.module.ui.component.Component#draw()
     * Draw menu items and menu title
     */
    @Override
    public void draw() {
        System.out.println(title);
        Stream.of(items).map(CommonLambda.ENUM_TO_STRING).forEach(System.out::println);
    }

    /**
     * @return
     * 
     * Choose item from menu items
     */
    public T chooseItem() {
        printMenuFooter(false);
        return items[readItemIndex()];
    }

    private int readItemIndex() {
        return InputReader.getInstance().readIntegerUntil(itemIsInAcceptableRange(), 
                redrawWithWarningMessage) - CommonConstant.MENU_ITEM_OFFSET;
    }

    private void printMenuFooter(final boolean hasToPrintWarning) {
        if (hasToPrintWarning) {
            System.out.println("Invalid Action. Please, type again.");
        }
        System.out.println("Select action number:");
    }

    private Predicate<String> itemIsInAcceptableRange() {
        return line -> acceptableItems.contains(Integer.parseInt(line));
    }


}
