import java.util.Scanner;

public class CoffeeMachine {
    static final int oneCup = 1;

    static final int espressoWater = 250;
    static final int espressoCoffee = 16;
    static final int espressoCost = 4;

    static final int latteWater = 350;
    static final int latteCoffee = 20;
    static final int latteCost = 7;
    static final int latteMilk = 75;

    static final int cappuccinoWater = 200;
    static final int cappuccinoCoffee = 12;
    static final int cappuccinoCost = 6;
    static final int cappuccinoMilk = 100;

    static int money = 550;
    static int water = 400;
    static int milk = 540;
    static int coffee = 120;
    static int cups = 9;

    public static States state = States.CHOOSE_ACTION;

    enum States {
        CHOOSE_ACTION, CHOOSE_COFFEE, START_FILLING, FILLING_WATER, FILLING_MILK, FILLING_BEANS, FILLING_CUPS
    }

    public static void getInput(String input) {

        switch(state) {
            case CHOOSE_ACTION: chooseAction(input);
                break;

            case FILLING_WATER: water += Integer.parseInt(input);
                System.out.println("Write how many ml of milk you want to add:");
                state = States.FILLING_MILK;
                break;

            case FILLING_MILK: milk += Integer.parseInt(input);
                System.out.println("Write how many grams of coffee beans you want to add:");
                state = States.FILLING_BEANS;
                break;

            case FILLING_BEANS: coffee += Integer.parseInt(input);
                System.out.println("Write how many disposable cups of coffee you want to add:");
                state = States.FILLING_CUPS;
                break;

            case FILLING_CUPS: cups += Integer.parseInt(input);
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                state = States.CHOOSE_ACTION;
                break;

            case CHOOSE_COFFEE: chooseCoffee(input);
                break;
        }
    }


    public static void chooseAction(String input) {
        switch (input) {
            case "buy": System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                state = States.CHOOSE_COFFEE;
                break;

            case "fill": System.out.println("Write how many ml of water you want to add:");
                state = States.FILLING_WATER;
                break;

            case "take": System.out.println("I give you $" + money);
                money = 0;
                state = States.CHOOSE_ACTION;
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                break;

            case "remaining": printState();
                state = States.CHOOSE_ACTION;
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                break;

            case "exit": System.exit(0);
                break;
        }
    }

    public static void chooseCoffee(String type) {
        if(type.equals("back")) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            state = States.CHOOSE_ACTION;
            return;
        }
        switch(type){
            case "1":
                if(water - espressoWater >= 0 && coffee - espressoCoffee >= 0 && cups - oneCup >= 0) {
                    money += espressoCost;
                    water -= espressoWater;
                    coffee -= espressoCoffee;
                    cups -= oneCup;
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Sorry, can't make a cup of coffee!");
                }
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                state = States.CHOOSE_ACTION;
                break;

            case "2":
                if(water - latteWater >= 0 && coffee - latteCoffee >= 0 && milk - latteMilk >= 0 && cups - oneCup >= 0) {
                    money += latteCost;
                    water -= latteWater;
                    coffee -= latteCoffee;
                    milk -= latteMilk;
                    cups -= oneCup;
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Sorry, can't make a cup of coffee!");
                }
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                state = States.CHOOSE_ACTION;
                break;

            case "3":
                if(water - cappuccinoWater >= 0 && coffee - cappuccinoCoffee >= 0 && milk - cappuccinoMilk >= 0 && cups - oneCup >= 0) {
                    money += cappuccinoCost;
                    water -= cappuccinoWater;
                    coffee -= cappuccinoCoffee;
                    milk -= cappuccinoMilk;
                    cups -= oneCup;
                    System.out.println("I have enough resources, making you a coffee!");
                } else {
                    System.out.println("Sorry, can't make a cup of coffee!");
                }
                System.out.println("Write action (buy, fill, take, remaining, exit): ");
                state = States.CHOOSE_ACTION;
                break;
        }
    }


    public static void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(coffee + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println(money + "$ of money");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write action (buy, fill, take, remaining, exit): ");
        while(scanner.hasNextLine()) {
            getInput(scanner.next());
        }
    }
}