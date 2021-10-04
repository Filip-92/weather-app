package pl_filippeszke.model.auxiliaryMethod;

public class StatusImage {

    public static String getImage(String status) {

        switch (status) {
            case "clear-day":
                return "/img/icons/icon1.png";
            case "clear-night":
                return "/img/icons/icon2.png";
            case "rain":
                return "/img/icons/icon3.png";
            case "snow":
                return "/img/icons/icon4.png";
            case "sleet":
                return "/img/icons/icon5.png";
            case "wind":
                return "/img/icons/icon6.png";
            case "fog":
                return "/img/icons/icon7.png";
            case "cloudy":
                return "/img/icons/icon8.png";
            case "partly-cloudy-day":
                return "/img/icons/icon9.png";
            case "partly-cloudy-night":
                return "/img/icons/icon10.png";
        }
        return "/img/icons/icon11.png";
    }
}
