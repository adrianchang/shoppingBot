package lacosteBot;

import java.util.ArrayList;
import java.util.List;

public class ConfigStructure {
    private List<String> keyWords;

    private String name;
    private String email;
    private String tel;
    private String address;
    private String aptEtc;
    private String zip;
    private String city;
    private String cityAbbrev;
    private String countryAbbrev;
    private String creditCardNumber;
    private String cardExpireMonth;
    private String cardExpireYear;
    private String CVV;
    private String dropTime;


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDropTime() {
        return dropTime;
    }

    public void setDropTime(String dropTime) {
        this.dropTime = dropTime;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setAptEtc(String aptEtc) {
        this.aptEtc = aptEtc;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityAbbrev(String cityAbbrev) {
        this.cityAbbrev = cityAbbrev;
    }

    public void setCountryAbbrev(String countryAbbrev) {
        this.countryAbbrev = countryAbbrev;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public void setCardExpireMonth(String cardExpireMonth) {
        this.cardExpireMonth = cardExpireMonth;
    }

    public void setCardExpireYear(String cardExpireYear) {
        this.cardExpireYear = cardExpireYear;
    }

    public void setCVV(String CVV) {
        this.CVV = CVV;
    }

    public List<String> getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(List<String> keyWords) {
        this.keyWords = keyWords;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getAddress() {
        return address;
    }

    public String getAptEtc() {
        return aptEtc;
    }

    public String getZip() {
        return zip;
    }

    public String getCity() {
        return city;
    }

    public String getCityAbbrev() {
        return cityAbbrev;
    }

    public String getCountryAbbrev() {
        return countryAbbrev;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public String getCardExpireMonth() {
        return cardExpireMonth;
    }

    public String getCardExpireYear() {
        return cardExpireYear;
    }

    public String getCVV() {
        return CVV;
    }
}
