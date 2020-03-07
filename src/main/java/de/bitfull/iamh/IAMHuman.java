package de.bitfull.iamh;

import de.bitfull.iamh.pojo.ActionsItem;
import de.bitfull.iamh.pojo.HumanInput;
import de.bitfull.iamh.types.HumanType;
import de.bitfull.iamh.utils.HumanUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.function.Supplier;

public class IAMHuman<T extends WebDriver> {

    private final Supplier<T> createInstance;
    private final HumanInput input;

    public IAMHuman(Supplier<T> createInstance, HumanInput input) {
        this.createInstance = createInstance;
        this.input = input;
    }

    public void work() {
        WebDriver client = createInstance.get();
        try {
            client.get(input.getTargetUrl());
            input.getActions().stream().forEach(a -> interpretAndExecute(a, client));
        } finally {
            client.close();
        }
    }

    private void interpretAndExecute(ActionsItem action, WebDriver client) {
        WebElement element = null;
        if (HumanType.SelectType.XPATH.getType().equals(action.getSelectType())) {
            element = client.findElement(By.xpath(action.getSelector()));
        }
        if (element != null) {
            if (HumanType.ActionType.TYPE.getType().equals(action.getAction())) {
                String value = action.getValue();
                value = checkFunction(value);
                element.sendKeys(value);
            }
        }
    }

    String checkFunction(String value) {
        if (value.startsWith(">>") && value.contains("(") && value.endsWith(")")) {
            String sub = value.substring(2, value.indexOf('('));
            if (sub.equals("randompw")) {
                value = HumanUtils.generatePassword(8);
            }
        }
        return value;
    }
}
