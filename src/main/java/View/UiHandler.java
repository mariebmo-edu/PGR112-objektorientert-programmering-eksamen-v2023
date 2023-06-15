package View;

import java.util.ArrayList;

public class UiHandler<T> {

    public static void PrintMenuList(ArrayList<String> menuList) {
        int index = 1;
        for (Object menuItem : menuList) {
            System.out.println(index + ". " + menuItem);
            index++;
        }

        System.out.println("Q. Quit");
    }
}
