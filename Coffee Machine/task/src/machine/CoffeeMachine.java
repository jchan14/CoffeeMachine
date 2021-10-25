package machine;

import java.util.ArrayList;
import java.util.Scanner;

class Coffee {
    private String name;
    private double cost;
    private int water;
    private int milk;
    private int coffeeBean;

    public Coffee(String name, double cost, int water, int milk, int coffeeBean) {
        this.cost = cost;
        this.name = name;
        this.water = water;
        this.milk = milk;
        this.coffeeBean = coffeeBean;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getMilk() {
        return milk;
    }

    public void setMilk(int milk) {
        this.milk = milk;
    }

    public int getCoffeeBean() {
        return coffeeBean;
    }

    public void setCoffeeBean(int coffeeBean) {
        this.coffeeBean = coffeeBean;
    }
}

public class CoffeeMachine {
    private enum machineStatus {
        MAIN, BUY, FILL, FILLWATER, FILLMILK, FILLCOFFEEBEAN, FILLCUP, TAKE
    }

    private double money;
    private int water;
    private int milk;
    private int coffeeBean;
    private int cup;
    private ArrayList<Coffee> coffees;
    private machineStatus status;
//    private Coffee c;

    public static void main(String[] args) {
        Coffee espresso = new Coffee("Espresso", 4, 250, 0, 16);
        Coffee latte = new Coffee("Latte", 7, 350, 75, 20);
        Coffee cappuccino = new Coffee("Cappuccino", 6, 200, 100, 12);
        ArrayList<Coffee> coffees = new ArrayList<>();
        coffees.add(espresso);
        coffees.add(latte);
        coffees.add(cappuccino);
        CoffeeMachine coffeeMachine = new CoffeeMachine(550, 400, 540, 120, 9, coffees);

        Scanner scanner = new Scanner(System.in);
        coffeeMachine.command("");
        while (true) {
            coffeeMachine.command(scanner.nextLine());
        }
    }

    public CoffeeMachine(double money, int water, int milk, int coffeeBean, int cup, ArrayList<Coffee> coffees) {
        this.money = money;
        this.water = water;
        this.milk = milk;
        this.coffeeBean = coffeeBean;
        this.cup = cup;
        this.coffees = coffees;
        this.status = machineStatus.MAIN;
    }

    public void displayStat() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", this.water);
        System.out.printf("%d ml of milk\n", this.milk);
        System.out.printf("%d g of coffee beans\n", this.coffeeBean);
        System.out.printf("%d disposable cups\n", this.cup);
        System.out.printf("$%.0f of money\n", this.money);
        System.out.println();
    }

    private void command(String command) {
        command = command.toLowerCase();
        switch (this.status) {
            case MAIN:
                choiceMain(command);
                break;
            case BUY:
                choiceBuy(command);
                break;
            case FILL:
            case FILLWATER:
            case FILLMILK:
            case FILLCOFFEEBEAN:
            case FILLCUP:
                choiceFill(command);
                break;
        }
    }

    public void choiceMain(String choice) {
        boolean isValidChoice = (choice.equals("") ||
                choice.equals("buy") ||
                choice.equals("fill") ||
                choice.equals("take") ||
                choice.equals("remaining") ||
                choice.equals("back") ||
                choice.equals("exit")) ? true : false;
        if (!isValidChoice) {
            System.out.println("Invalid menu entry.  Please try again.");
        } else {
            switch (choice) {
                case "":
                    System.out.println("Write action (buy, fill, take, remaining, exit):");
                    break;
                case "buy":
                    status = machineStatus.BUY;
                    choiceBuy("");
                    break;
                case "fill":
                    status = machineStatus.FILL;
                    choiceFill("");
                    break;
                case "take":
                    choiceTake();
                    choiceMain("");
                    break;
                case "remaining":
                    displayStat();
                    choiceMain("");
                    break;
                case "exit":
                    System.out.println("Have a nice day!");
                    System.exit(0);
                default:
                    System.out.println("... not sure i understand.  Please try again.");
            }
        }
    }

    private void choiceBuy(String choice) {
        try {
            switch (choice) {
                case "back":
                    System.out.println();
                    status = machineStatus.MAIN;
                    int i = 3;
                    choiceMain("");
                    break;
                case "":
                    System.out.println("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
                    break;
                case "1":
                    buyCoffee(getCoffeeByName("Espresso"));
                    break;
                case "2":
                    buyCoffee(getCoffeeByName("Latte"));
                    break;
                case "3":
                    buyCoffee(getCoffeeByName("Cappuccino"));
                    break;
                default:
                    System.out.println("Invalid input.  Please try again.");
                    break;
            }
        } catch (Exception e) {
            System.out.println("Invalid entry.  Please try again");
        }
    }

//    public void choiceMain() {
//        Scanner scanner = new Scanner(System.in);
//        boolean isValidChoice = false;
//        String choice;
//
//        while (true) {
//            do {
//                System.out.println("Write action (buy, fill, take, remaining, exit):");
//                choice = scanner.nextLine().toLowerCase();
//                isValidChoice = (choice.equals("buy") || choice.equals("fill") || choice.equals("take") || choice.equals("remaining")) || choice.equals("exit") ? true : false;
//                if (!isValidChoice) {
//                    System.out.println("Invalid entry.  Please try again.");
//                }
//            } while (!isValidChoice);
//
//            switch (choice) {
//                case "menu":
//                case "buy":
//                    choiceBuy();
//                    break;
//                case "fill":
//                    choiceFill();
//                    break;
//                case "take":
//                    choiceTake();
//                    break;
//                case "remaining":
//                    displayStat();
//                    break;
//                case "exit":
//                    System.out.println("Have a nice day!");
//                    System.exit(0);
//                default:
//                    System.out.println("... not sure i understand.  Please try again.");
//            }
//        }
//    }

//    private void choiceBuy(String command) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
//        Coffee c;
//        String input = scanner.nextLine().toLowerCase();
//        if (input.equals("back")) {
//            //do nothing and exit
//        } else {
//            try {
//                int choice = Integer.parseInt(input);
//                switch (choice) {
//                    case 1:
//                        buyCoffee(getCoffeeByName("Espresso"));
//                        break;
//                    case 2:
//                        buyCoffee(getCoffeeByName("Latte"));
//                        break;
//                    case 3:
//                        buyCoffee(getCoffeeByName("Cappuccino"));
//                        break;
//                }
//            } catch (Exception e) {
//                System.out.println("Invalid entry.  Exiting...");
//            }
//        }
//    }

    private void choiceFill(String command) {
        if (command.equals("back")) {
            System.out.println();
            this.status = machineStatus.MAIN;
            choiceMain("");
        } else {
            try {
                switch (this.status) {
                    case FILL:
                        System.out.println("\nWrite how many ml of water you want to add: ");
                        status = machineStatus.FILLWATER;
                        break;
                    case FILLWATER:
                        addWater(Integer.parseInt(command));
                        System.out.println("Write how many ml of milk you want to add: ");
                        status = machineStatus.FILLMILK;
                        break;
                    case FILLMILK:
                        addMilk(Integer.parseInt(command));
                        System.out.println("Write how many grams of coffee beans you want to add: ");
                        status = machineStatus.FILLCOFFEEBEAN;
                        break;
                    case FILLCOFFEEBEAN:
                        addCoffeeBean(Integer.parseInt(command));
                        System.out.println("Write how many disposable cups of coffee you want to add: ");
                        status = machineStatus.FILLCUP;
                        break;
                    case FILLCUP:
                        addCup(Integer.parseInt(command));
                        status = machineStatus.MAIN;
                        System.out.println();
                        choiceMain("");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Cannot process your command.  Please try again");
            }
        }
    }

//    private void choiceFill(String command) {
//        Scanner scanner = new Scanner(System.in);
//        System.out.println("\nWrite how many ml of water you want to add: ");
//        addWater(scanner.nextInt());
//        System.out.println("Write how many ml of milk you want to add: ");
//        addMilk(scanner.nextInt());
//        System.out.println("Write how many grams of coffee beans you want to add: ");
//        addCoffeeBean(scanner.nextInt());
//        System.out.println("Write how many disposable cups of coffee you want to add: ");
//        addCup(scanner.nextInt());
//        System.out.println();
//    }

    private void choiceTake() {
        System.out.printf("\nI gave you %.0f\n\n", getMoney());
        clearMoney(0);
    }

    private Coffee getCoffeeByName(String name) {
        for (Coffee c : coffees) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    public double getMoney() {
        return money;
    }

    public void clearMoney(double money) {
        this.money = 0;
    }

    public void addMoney(double money) {
        this.money += money;
    }

    public int getWater() {
        return water;
    }

    public void addWater(int water) {
        this.water += water;
    }

    public void minusWater(int water) {
        this.water -= water;
    }

    public int getMilk() {
        return milk;
    }

    public void addMilk(int milk) {
        this.milk += milk;
    }

    public void minusMilk(int milk) {
        this.milk -= milk;
    }

    public int getCoffeeBean() {
        return coffeeBean;
    }

    public void addCoffeeBean(int coffeeBean) {
        this.coffeeBean += coffeeBean;
    }

    public void minusCoffeeBean(int coffeeBean) {
        this.coffeeBean -= coffeeBean;
    }

    public int getCup() {
        return cup;
    }

    public void addCup(int cup) {
        this.cup += cup;
    }

    public void minusCup(int cup) {
        this.cup -= cup;
    }

    private void buyCoffee(Coffee coffee) {
        int cupPerWater = this.getWater() / (coffee.getWater() == 0 ? 1 : coffee.getWater());
        int cupPerMilk = this.getMilk() / (coffee.getMilk() == 0 ? 1 : coffee.getMilk());
        int cupPerCoffeeBean = this.getCoffeeBean() / (coffee.getCoffeeBean() == 0 ? 1 : coffee.getCoffeeBean());
        int minServing = Math.min(cupPerCoffeeBean, Math.min(cupPerMilk, cupPerWater));
        int maxServing = Math.max(cupPerCoffeeBean, Math.max(cupPerMilk, cupPerMilk));

        if (cupPerWater < 1) {
            System.out.println("Sorry, not enough water!\n");
        } else if (cupPerMilk < 1) {
            System.out.println("Sorry, not enough milk!\n");
        } else if (cupPerCoffeeBean < 1) {
            System.out.println("Sorry, not enough coffee bean!\n");
        } else if (this.getCup() < 1) {
            System.out.println("Sorry, not enough cup!\n");
        } else {
            System.out.println("I have enough resources, making you a coffee!\n");
            minusWater(coffee.getWater());
            minusMilk(coffee.getMilk());
            minusCoffeeBean(coffee.getCoffeeBean());
            minusCup(1);
            addMoney(coffee.getCost());
        }
        status = machineStatus.MAIN;
        choiceMain("");
    }

//    private void buyCoffee(Coffee coffee) {
//        int cupPerWater = this.getWater() / (coffee.getWater() == 0 ? 1 : coffee.getWater());
//        if (cupPerWater < 0) {
//            System.out.println("\nSorry, not enough water!");
//        }
//        int cupPerMilk = this.getMilk() / (coffee.getMilk() == 0 ? 1 : coffee.getMilk());
//        if (cupPerMilk < 0) {
//            System.out.println("\nSorry, not enough milk!");
//        }
//        int cupPerCoffeeBean = this.getCoffeeBean() / (coffee.getCoffeeBean() == 0 ? 1 : coffee.getCoffeeBean());
//        if (cupPerCoffeeBean < 0) {
//            System.out.println("\nSorry, not enough coffee bean!");
//        }
//
//        int minServing = Math.min(cupPerCoffeeBean, Math.min(cupPerMilk, cupPerWater));
//        int maxServing = Math.max(cupPerCoffeeBean, Math.max(cupPerMilk, cupPerMilk));
//
//        if (minServing < 1) {
////            System.out.println("No, I can make only " + minServing + " cup(s) of coffee");
//        } else if (this.getCup() < 1) {
//            System.out.println("\nSorry, not enough cup!");
//        } else if (minServing == cup) {
////            System.out.println("Yes, I can make that amount of coffee");
//        } else if (minServing > cup) {
////            System.out.println("Yes, I can make that amount of coffee (and even " + (minServing - cup) + " more than that)");
//        } else {
//            System.out.println("I have enough resources, making you a coffee!");
//            minusWater(coffee.getWater());
//            minusMilk(coffee.getMilk());
//            minusCoffeeBean(coffee.getCoffeeBean());
//            minusCup(1);
//            addMoney(coffee.getCost());
//        }
//    }

    public void exercise3() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write how many ml of water the coffee machine has: ");
        int inputWater = Integer.parseInt(scanner.nextLine());
        System.out.println("Write how many ml of milk the coffee machine has: ");
        int inputMilk = Integer.parseInt(scanner.nextLine());
        System.out.println("Write how many grams of coffee beans the coffee machine has: ");
        int inputCoffeeBean = scanner.nextInt();
        System.out.println("Write how many cups of coffee you will need: ");
        int cup = scanner.nextInt();

        int cupPerWater = inputWater / water;
        int cupPerMilk = inputMilk / milk;
        int cupPerCoffeeBean = inputCoffeeBean / coffeeBean;

        int minServing = Math.min(cupPerCoffeeBean, Math.min(cupPerMilk, cupPerWater));
        int maxServing = Math.max(cupPerCoffeeBean, Math.max(cupPerMilk, cupPerMilk));

        if (minServing < cup) {
            System.out.println("No, I can make only " + minServing + " cup(s) of coffee");
        } else if (minServing == cup) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (minServing > cup) {
            System.out.println("Yes, I can make that amount of coffee (and even " + (minServing - cup) + " more than that)");
        }
    }

    public void exercise2() {
        System.out.println("Write how many cups of coffee you will need: ");
        Scanner scanner = new Scanner(System.in);
        int cup = scanner.nextInt();
        System.out.println(String.format("For %d cups of coffee you will need:", cup));
        System.out.println(cup * water + " ml of water");
        System.out.println(cup * milk + " ml of milk");
        System.out.println(cup * coffeeBean + " g of coffe beans");
    }

    public void exercise1() {
        System.out.println("Starting to make a coffee\n" +
                "Grinding coffee beans\n" +
                "Boiling water\n" +
                "Mixing boiled water with crushed coffee beans\n" +
                "Pouring coffee into the cup\n" +
                "Pouring some milk into the cup\n" +
                "Coffee is ready!\n"
        );
    }
}
