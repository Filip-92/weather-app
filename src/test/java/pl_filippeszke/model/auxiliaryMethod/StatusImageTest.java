package pl_filippeszke.model.auxiliaryMethod;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

class StatusImageTest {
    
    @Test
    void shouldCreateObject() {
        
        //given
        StatusImage statusImage = new StatusImage();
        
        //when
        //then
        assertThat(statusImage, is(notNullValue()));

    }

    @Test
    void shouldReturnIcon1IfStatusMatches() {

        //given
        String status = "clear-day";
        String icon;

        //when
        icon = StatusImage.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon1.png"));
    }

    @Test
    void shouldReturnIcon3IfStatusMatches() {

        //given
        String status = "rain";
        String icon;

        //when
        icon = StatusImage.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon3.png"));
    }

    @Test
    void shouldReturnIcon4IfStatusMatches() {

        //given
        String status = "snow";
        String icon;

        //when
        icon = StatusImage.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon4.png"));
    }

    @Test
    void shouldReturnIcon5IfStatusMatches() {

        //given
        String status = "sleet";
        String icon;

        //when
        icon = StatusImage.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon5.png"));
    }

    @Test
    void shouldReturnIcon6IfStatusMatches() {

        //given
        String status = "wind";
        String icon;

        //when
        icon = StatusImage.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon6.png"));
    }

    @Test
    void shouldReturnIcon7IfStatusMatches() {

        //given
        String status = "fog";
        String icon;

        //when
        icon = StatusImage.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon7.png"));
    }

    @Test
    void shouldReturnIcon8IfStatusMatches() {

        //given
        String status = "cloudy";
        String icon;

        //when
        icon = StatusImage.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon8.png"));
    }
    @Test
    void shouldReturnIcon9IfStatusMatches() {

        //given
        String status = "partly-cloudy-day";
        String icon;

        //when
        icon = StatusImage.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon9.png"));
    }

    @Test
    void shouldReturnIcon10IfStatusMatches() {

        //given
        String status = "partly-cloudy-night";
        String icon;

        //when
        icon = StatusImage.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon10.png"));
    }

    @Test
    void shouldReturnIcon2IfStatusMatches() {

        //given
        String status = "clear-night";
        String icon;

        //when
        icon = StatusImage.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon2.png"));
    }

    @Test
    void shouldReturnDefaultIconIfStatusDoesNotMatch() {

        //given
        String status = "cloudy-with-a-chance-of-meatballs";
        String icon;

        //when
        icon = StatusImage.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon11.png"));
    }

}