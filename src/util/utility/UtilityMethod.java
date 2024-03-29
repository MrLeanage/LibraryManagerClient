package util.utility;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Locale;

public class UtilityMethod {
    public static String addPrefix(String prefix, String id){
        return prefix +id;
    }
    public static int seperateID(String strID){
        String[] part = strID.split("(?<=\\D)(?=\\d)");
        //part[0] gives Prefix, part [1] gives numeric part
        //System.out.println(part[0]);
        //System.out.println(part[1]);
        return Integer.parseInt(part[1]);
    }
    public static int insertItemAvailableQuantity(int unitPerBlock, int Blocks){
        return unitPerBlock * Blocks;
    }
    public static String setItemAvailabilityStatus(int availableQuantity, int minQuantity){
        String returnVal = null;
        if(availableQuantity > minQuantity){
            returnVal =  "Available";
        }else if(availableQuantity > 0){
            returnVal = "Low Stock";
        }else{
            returnVal = "Out of Stock";
        }
        return returnVal;
    }
    public static ImageView convertInputStreamToImage(InputStream inputStream) throws IOException {
        ImageView imageView = new ImageView();
        OutputStream outputStream = new FileOutputStream(new File("photo.jpg"));
        byte[] content = new byte[1024];
        int size = 0;
        while ((size = inputStream.read(content)) != -1){
            outputStream.write(content, 0, size);
        }
        outputStream.close();
        inputStream.close();
        Image image = new Image("file:photo.jpg");
        imageView.setImage(image);
        return imageView;
    }
    public static InputStream convertImageToInputStream(ImageView imageView) throws IOException {
        Image image = imageView.getImage();

        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(bImage,"jpg", outputStream);
        InputStream fileInputStream = new ByteArrayInputStream(outputStream.toByteArray());
        return fileInputStream;
    }
    public static Float roundOfFloat(Float value){
        Float round = null;
        if(value > 1000000){
            round = value - (value % 1000000);
        }else if (value > 100000){
            round = value - (value % 100000);
        }
        else if(value > 10000){
            round = value - (value % 10000);
        }
        else if(value > 1000){
            round = value - (value % 1000);
        } else if(value > 100){
            round = value - (value % 100);
        }
        return round;
    }
    public static Float advancePayCalculator(Integer quantity, Float price){
        Float adAmount = null;
        Float amount = quantity * price;
        if(amount > 700){
            adAmount = roundOfFloat(amount / 2);
        }else{
            adAmount = amount;
        }
        return adAmount;
    }
    public static String currentTime(){
        String currentTime = LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME);
        return currentTime;
    }
    public static String getCurrentMonth(){
        String month = String.valueOf(YearMonth.now().getMonth());
        return month;
    }

    public static String getCurrentDate(){
        return String.valueOf(LocalDate.now());
    }
    public static Integer getYear(String stringDate){
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dt = LocalDate.parse(stringDate, DATEFORMATTER);

        return dt.getYear();
    }
    public static String getMonth(String stringDate){
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dt = LocalDate.parse(stringDate, DATEFORMATTER);

        return dt.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public static String getYearMonth(String stringDate){
        DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dt = LocalDate.parse(stringDate, DATEFORMATTER);

        return dt.getYear() + "-" +dt.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public static ObservableList<Integer> removeIntegerDuplicates(ObservableList<Integer> list) {

        // Store unique items in result.
        ObservableList<Integer> result = FXCollections.observableArrayList();

        // Record encountered Strings in HashSet.
        HashSet<Integer> set = new HashSet<>();

        // Loop over argument list.
        for (Integer item : list) {

            // If String is not in set, add it to the list and the set.
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }
    public static ObservableList<String> removeStringDuplicates(ObservableList<String> list) {

        // Store unique items in result.
        ObservableList<String> result = FXCollections.observableArrayList();

        // Record encountered Strings in HashSet.
        HashSet<String> set = new HashSet<>();

        // Loop over argument list.
        for (String item : list) {

            // If String is not in set, add it to the list and the set.
            if (!set.contains(item)) {
                result.add(item);
                set.add(item);
            }
        }
        return result;
    }
    public static String getMonthNumber(String monthName) {
        String number = String.valueOf(Month.valueOf(monthName.toUpperCase()).getValue());
        String monthNumber = null;

        if(number.length() < 2){
            monthNumber =  "0" + number;
        }else{
            monthNumber = number;
        }
        return monthNumber;
    }
    public static Integer seperateIntegerFromString(String stringIntegerMixedText){

        String numberOnly = stringIntegerMixedText.replaceAll("[^0-9]", "");
        if(numberOnly.equals("")){
            numberOnly = "1";
        }
        return Integer.parseInt(numberOnly);
    }
    public static String seperateLettersFromText(String stringMixedText){

        String lettersOnly = stringMixedText.replaceAll("[^A-Za-z]", "");

        return lettersOnly;
    }
    public static String numberDisplayWithCommasAndDecimalPlaces(Double number){
        String stringNumber = null;
        if(number.equals(0.0)){
            stringNumber = "0.00";
        }else{
            stringNumber = new DecimalFormat("#,###.00").format(number);
        }

        return stringNumber;
    }
    public static Integer[] getNoOfDates(String newDate, String oldDate) {
        Integer[] date = new Integer[3];
        LocalDate oDate = LocalDate.parse(oldDate);
        LocalDate nDate = LocalDate.parse(newDate);

        Period diff = Period.between( nDate, oDate);
        date[0] = diff.getYears();//setting no of years
        date[1] = diff.getMonths();//setting no of months
        date[2] = diff.getDays();//setting no of days
        return date;
    }
    public static Integer getTotalNoOfDays(String oldDate, String newDate){

        return Integer.parseInt(String.valueOf(ChronoUnit.DAYS.between(LocalDate.parse(oldDate), LocalDate.parse(newDate))));
    }
}
