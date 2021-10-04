package pl_filippeszke.model.auxiliaryMethod;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

class IconManagerTest {

    @Test
    void shouldCreateObject() {

        //given
        IconManager iconManager = new IconManager();

        //when
        //then
        assertThat(iconManager, is(notNullValue()));
    }

    @Test
    void shouldReturnClearIcon() {

        //given
        String status = "01d";
        String icon;

        //when
        icon = IconManager.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon1.png"));
    }

    @Test
    void shouldReturnClearNightIcon() {

        //given
        String status = "01n";
        String icon;

        //when
        icon = IconManager.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon2.png"));
    }

    @Test
    void shouldReturnCloudyDayIcon() {

        //given
        String status = "02d";
        String icon;

        //when
        icon = IconManager.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon9.png"));
    }

    @Test
    void shouldReturnCloudyNightIcon() {

        //given
        String status = "02n";
        String icon;

        //when
        icon = IconManager.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon10.png"));
    }

    @Test
    void shouldReturnScatteredCloudsIcon() {

        //given
        String status1 = "03d";
        String status2 = "03n";
        String icon1;
        String icon2;

        //when
        icon1 = IconManager.getImage(status1);
        icon2 = IconManager.getImage(status2);

        //then
        assertThat(icon1, is("/img/icons/icon8.png"));
        assertThat(icon2, is("/img/icons/icon8.png"));
        assertThat(icon1, equalTo(icon2));
    }

    @Test
    void shouldReturnBrokenCloudsIcon() {

        //given
        String status1 = "04d";
        String status2 = "04n";
        String icon1;
        String icon2;

        //when
        icon1 = IconManager.getImage(status1);
        icon2 = IconManager.getImage(status2);

        //then
        assertThat(icon1, is("/img/icons/icon16.png"));
        assertThat(icon2, is("/img/icons/icon16.png"));
        assertThat(icon1, equalTo(icon2));
    }

    @Test
    void shouldReturnShowerRainIcon() {

        //given
        String status1 = "09d";
        String status2 = "09n";
        String icon1;
        String icon2;

        //when
        icon1 = IconManager.getImage(status1);
        icon2 = IconManager.getImage(status2);

        //then
        assertThat(icon1, is("/img/icons/icon3.png"));
        assertThat(icon2, is("/img/icons/icon3.png"));
        assertThat(icon1, equalTo(icon2));
    }

    @Test
    void shouldReturnRainyDayIcon() {

        //given
        String status = "10d";
        String icon;

        //when
        icon = IconManager.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon13.png"));
    }

    @Test
    void shouldReturnRainyNightIcon() {

        //given
        String status = "10n";
        String icon;

        //when
        icon = IconManager.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon12.png"));
    }

    @Test
    void shouldReturnThunderstormDayIcon() {

        //given
        String status = "11d";
        String icon;

        //when
        icon = IconManager.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon14.png"));
    }

    @Test
    void shouldReturnThunderstormNightIcon() {

        //given
        String status = "11n";
        String icon;

        //when
        icon = IconManager.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon15.png"));
    }

    @Test
    void shouldReturnSnowIcon() {

        //given
        String status1 = "13d";
        String status2 = "13n";
        String icon1;
        String icon2;

        //when
        icon1 = IconManager.getImage(status1);
        icon2 = IconManager.getImage(status2);

        //then
        assertThat(icon1, is("/img/icons/icon4.png"));
        assertThat(icon2, is("/img/icons/icon4.png"));
        assertThat(icon1, equalTo(icon2));
    }

    @Test
    void shouldReturnMistIcon() {

        //given
        String status1 = "50d";
        String status2 = "50n";
        String icon1;
        String icon2;

        //when
        icon1 = IconManager.getImage(status1);
        icon2 = IconManager.getImage(status2);

        //then
        assertThat(icon1, is("/img/icons/icon7.png"));
        assertThat(icon2, is("/img/icons/icon7.png"));
        assertThat(icon1, equalTo(icon2));
    }

    @Test
    void shouldReturnDefaultIcon() {

        //given
        String status = "5n";
        String icon;

        //when
        icon = IconManager.getImage(status);

        //then
        assertThat(icon, is("/img/icons/icon11.png"));
    }
}