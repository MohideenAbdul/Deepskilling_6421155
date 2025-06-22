import java.util.ArrayList;
import java.util.List;

public class ObserverPatternExample {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp = new MobileApp("MobileApp1");
        Observer webApp = new WebApp("WebApp1");

        stockMarket.register(mobileApp);
        stockMarket.register(webApp);

        stockMarket.setPrice("AAPL", 180.50);
        stockMarket.setPrice("GOOGL", 2750.00);

        stockMarket.deregister(mobileApp);

        stockMarket.setPrice("AAPL", 181.00);
    }
}

interface Stock {
    void register(Observer observer);
    void deregister(Observer observer);
    void notifyObservers(String stockSymbol, double price);
}

class StockMarket implements Stock {
    private List<Observer> observers = new ArrayList<>();
    private String stockSymbol;
    private double price;

    @Override
    public void register(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String stockSymbol, double price) {
        for (Observer observer : observers) {
            observer.update(stockSymbol, price);
        }
    }

    public void setPrice(String stockSymbol, double price) {
        this.stockSymbol = stockSymbol;
        this.price = price;
        System.out.println("Stock " + stockSymbol + " updated to $" + price);
        notifyObservers(stockSymbol, price);
    }
}

interface Observer {
    void update(String stockSymbol, double price);
}

class MobileApp implements Observer {
    private String name;

    public MobileApp(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println(name + " received update: " + stockSymbol + " is now $" + price);
    }
}

class WebApp implements Observer {
    private String name;

    public WebApp(String name) {
        this.name = name;
    }

    @Override
    public void update(String stockSymbol, double price) {
        System.out.println(name + " received update: " + stockSymbol + " is now $" + price);
    }
}
