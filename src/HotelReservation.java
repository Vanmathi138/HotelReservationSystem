import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HotelReservation {

    private List<Hotel> hotels;

    public HotelReservation() {
        hotels = new ArrayList<>();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public String findCheapestHotel(String input) {
        String[] parts = input.split(":");
        String customerType = parts[0].trim();
        String[] dateStrings = parts[1].split(",");

        List<LocalDate> dates = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMMyyyy(EEE)", Locale.ENGLISH);

        for (String dateStr : dateStrings) {
            String cleanedDate = dateStr.trim().replaceAll("\\(.*\\)", "");
            dates.add(LocalDate.parse(cleanedDate, DateTimeFormatter.ofPattern("ddMMMyyyy", Locale.ENGLISH)));
        }

        int minCost = Integer.MAX_VALUE;
        Hotel cheapestHotel = null;

        for (Hotel hotel : hotels) {
            int totalCost = 0;
            for (LocalDate date : dates) {
                boolean isWeekend = date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
                totalCost += hotel.getRate(customerType, isWeekend);
            }

            if (totalCost < minCost || (totalCost == minCost && hotel.getRating() > cheapestHotel.getRating())) {
                minCost = totalCost;
                cheapestHotel = hotel;
            }
        }

        return cheapestHotel.getName();
    }

    public static void main(String[] args) {
        System.out.println("Welcome to Hotel Reservation Program");

        HotelReservation reservation = new HotelReservation();

        // Add hotels
        Map<String, Integer> lakewoodWeekday = Map.of("Regular", 110, "Rewards", 80);
        Map<String, Integer> lakewoodWeekend = Map.of("Regular", 90, "Rewards", 80);
        reservation.addHotel(new Hotel("Lakewood", 3, lakewoodWeekday, lakewoodWeekend));

        Map<String, Integer> bridgewoodWeekday = Map.of("Regular", 160, "Rewards", 110);
        Map<String, Integer> bridgewoodWeekend = Map.of("Regular", 60, "Rewards", 50);
        reservation.addHotel(new Hotel("Bridgewood", 4, bridgewoodWeekday, bridgewoodWeekend));

        Map<String, Integer> ridgewoodWeekday = Map.of("Regular", 220, "Rewards", 100);
        Map<String, Integer> ridgewoodWeekend = Map.of("Regular", 150, "Rewards", 40);
        reservation.addHotel(new Hotel("Ridgewood", 5, ridgewoodWeekday, ridgewoodWeekend));

        String input1 = "Regular: 16Mar2020(mon), 17Mar2020(tues), 18Mar2020(wed)";
        String input2 = "Rewards: 26Mar2009(thur), 27Mar2009(fri), 28Mar2009(sat)";

        System.out.println("Cheapest Hotel: " + reservation.findCheapestHotel(input1)); // Lakewood
        System.out.println("Cheapest Hotel: " + reservation.findCheapestHotel(input2)); // Ridgewood
    }
}
