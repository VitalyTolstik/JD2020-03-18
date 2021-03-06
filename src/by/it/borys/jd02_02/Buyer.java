package by.it.borys.jd02_02;


import java.util.HashMap;
import java.util.Map;

public class Buyer extends Thread implements IBuyer, IUseBasket {

    public boolean isPensioner() {
        return pensioner;
    }

    private boolean pensioner = false;
    Basket basket= new Basket();

    public Buyer(int number) {
        super("Buyer № " + number + " ");
        Manager.buyerAddToShop();
    }

    public Buyer(int number, boolean pensioner) {
        super("Buyer № " + number + "(pens) ");
        this.pensioner =pensioner;
        Manager.buyerAddToShop();
    }
    @Override
    public void run() {
        enterToMarket();
        takeBacket();
        chooseGoods();
        putGoodsToBasket();
        goToQueue();
        goOut();
    }

    @Override
    public void enterToMarket() {
        System.out.println(this + "enter to shop");
    }

    @Override
    public void chooseGoods() {
        System.out.println(this + "started to choose goods");
        int timeout = Helper.getRandom(500, 2000);
        if (pensioner) timeout *= 1.5;
        Helper.sleep(timeout);
    }

    @Override
    public void takeBacket() {
            System.out.println(this + "takes the basket");
       }

    @Override
    public void putGoodsToBasket() {
            int random = Helper.getRandom(1, 4);
            for (int i = 0; i < random; i++) {
                HashMap<String, Integer> ranGood = Good.randomGood();
                for (Map.Entry<String, Integer> g : ranGood.entrySet()) {
                    basket.putToBasket(g.getKey(), g.getValue());
                    System.out.println(this + "put the " + g.getKey() + " for $" + g.getValue() + " to the basket");
                }
                int timeout = Helper.getRandom(500, 2000);
                if (pensioner) timeout *= 1.5;
                Helper.sleep(timeout);
            }
            System.out.println(this + "finished to choose goods");
    }



    @Override
    public void goToQueue() {
            synchronized (this) {
                QueueBuyers.add(this);
                try {
                    System.out.println(this + " added to queue");
                    wait();
                    System.out.println(this + " leaved the queue");
                } catch (InterruptedException e) {
                    throw new RuntimeException("Ошибка", e);
                }
            }
    }

    @Override
    public void goOut() {
            System.out.println(this + "leaved the shop");
            // Manager.buyersCount(false);
            Manager.buyerLeaveTheShop();
    }
    @Override
    public String toString() {
        return getName();
    }
}
