package pl_filippeszke.model.auxiliaryMethod;

public class IconManager {

    public static String getImage(String icon) {
        switch (icon){
            case "01d":
                return "/img/icons/icon1.png";
            case "01n":
                return "/img/icons/icon2.png";
            case "02d":
                return "/img/icons/icon9.png";
            case "02n":
                return "/img/icons/icon10.png";
            case "03d": case "03n":
                return "/img/icons/icon8.png";
            case "04d": case "04n":
                return "/img/icons/icon16.png";
            case "09d": case "09n":
                return "/img/icons/icon3.png";
            case "10d":
                return "/img/icons/icon13.png";
            case "10n":
                return "/img/icons/icon12.png";
            case "11d":
                return "/img/icons/icon14.png";
            case "11n":
                return "/img/icons/icon15.png";
            case "13d": case "13n":
                return "/img/icons/icon4.png";
            case "50d": case "50n":
                return "/img/icons/icon7.png";
        }
        return "/img/icons/icon11.png";
    }
}
