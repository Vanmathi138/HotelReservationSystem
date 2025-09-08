import java.util.Map;

public class Hotel {
    private String name;
    private int rating;
    private Map<String, Integer> weekdayRates;
    private Map<String, Integer> weekendRates;
    public Hotel(String name, int rating, Map<String, Integer> weekdayRates, Map<String, Integer> weekendRates) {
        this.name = name;
        this.rating = rating;
        this.weekdayRates = weekdayRates;
        this.weekendRates = weekendRates;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public int getRate(String customerType, boolean isWeekend) {
        if (isWeekend) {
            return weekendRates.get(customerType);
        } else {
            return weekdayRates.get(customerType);
        }
    }
}

