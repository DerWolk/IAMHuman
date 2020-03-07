package de.bitfull.iamh;

import de.bitfull.iamh.pojo.ActionsItem;
import de.bitfull.iamh.pojo.HumanInput;
import de.bitfull.iamh.types.HumanType;
import de.bitfull.iamh.utils.HumanUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.event.InputEvent;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class IAMHuman<T extends WebDriver> {

    private final Supplier<T> createInstance;
    private final HumanInput input;
    WebDriver client;

    public IAMHuman(Supplier<T> createInstance, HumanInput input) {
        this.createInstance = createInstance;
        this.input = input;
    }

    public void work() {
        client = createInstance.get();
        client.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
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
        moveAndClickMouse(element);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (element != null) {
            if (HumanType.ActionType.TYPE.getType().equals(action.getAction())) {
                String value = action.getValue();
                value = checkFunction(value);
                element.sendKeys(value);
            } else if (HumanType.ActionType.SELECT.getType().equals(action.getAction())) {
                Select select = new Select(element);
                String value = action.getValue();
                value = checkFunction(value);
                select.selectByVisibleText(value);
            }
        }
    }

    private void moveAndClickMouse(WebElement element) {
        // mouseMovement
        Point coordinates = element.getLocation();
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.mouseMove(coordinates.getX(), coordinates.getY());
        robot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
    }

    String checkFunction(String value) {
        if (value.startsWith(">>") && value.contains("(") && value.endsWith(")")) {
            String sub = value.substring(2, value.indexOf('('));
            String functionParam = value.substring(value.indexOf('(') + 1, value.indexOf(')'));
            if (sub.equals("randompw")) {
                value = HumanUtils.generatePassword(Integer.parseInt(functionParam));
            } else if (sub.equals("randomnumber")) {
                Random rn = new Random();
                int di = functionParam.indexOf('-');
                int from = Integer.parseInt(functionParam.substring(0, di));
                int to = Integer.parseInt(functionParam.substring(di + 1));
                value = String.valueOf(rn.nextInt(to - from + 1) + from);
            }
        }
        return value;
    }
}
