package covidnews.service;

import covidnews.models.News;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class NewsService {

    private ArrayList<News> preparedNews = new ArrayList<>(Arrays.asList(
            new News("26/11/2021", "Covid-19 : le variant Omicron détecté dans plusieurs pays d’Europe, Israël va fermer ses frontières aux étrangers", "Après la Belgique, la Grande-Bretagne, l’Allemagne et l’Italie, la République tchèque a annoncé un premier cas. La France, qui n’a pas encore recensé de cas, a décidé d’adapter son protocole sanitaire.","https://static.lexpress.fr/medias_12340/w_2048,h_1146,c_crop,x_0,y_3/w_1936,h_1090,c_fill,g_north/v1637516024/coronavirus-mono-blue_6318516.jpg"),
            new News("26/11/2021", "EN DIRECT - Variant Omicron : \"Il n'impacte pas le profil de la vague que nous connaissons\"", "Le ministre de la Santé, depuis un centre de vaccination de Paris ce dimanche, a déclaré que le variant Omicron était \"probablement déjà en circulation sur le territoire\". Suivez les dernières infos.","https://img-4.linternaute.com/XxdhBXGLEcEmfr6m6jOxUWsiCVY=/1500x/smart/74c525f780b14f9ab513791be0ac5606/ccmcms-linternaute/29816199.jpg"),
            new News("26/11/2021", "Covid-19 : même vaccinés, les cas contacts de personnes infectées au variant Omicron doivent s'isoler", "Toute personne «contact» d'une autre, testée positive au nouveau variant du coronavirus, Omicron, devra être isolée même si elle est vaccinée, a indiqué samedi le ministère de la Santé. ","https://img-4.linternaute.com/XR7CfR61GVZ6cGtdY8os84LuxuU=/1500x/smart/b8884273f07943449572d5f4ee275c26/ccmcms-linternaute/29841850.jpg"),
            new News("26/11/2021", "Ce que l'on sait sur le variant Omicron qui fait trembler le monde", "Avec la reprise de l'épidémie de Covid-19 un peu partout en Europe, le coronavirus est à nouveau au cœur de l'actualité. Nouveau rebondissement dans cette série qui semble sans fin : l'identification d'un variant hypermuté, B1.1.529 en Afrique du Sud. L'OMS se réunit d'urgence ce 26 novembre 2021 pour évoquer son cas.","https://www.infovac.fr/templates/yootheme/cache/Covid2020-4f05a398.webp")

    ));

    public ArrayList<News> getNews() {
        return this.preparedNews;
    }
}
