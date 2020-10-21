package com.example.mobilebookalpha;

public class Isciler {
    private String kisi_key;
    private String kisi_ad;
    private String kisi_yas;

    public Isciler() {
    }

    public Isciler(String kisi_key, String kisi_ad, String kisi_yas) {
        this.kisi_key = kisi_key;
        this.kisi_ad = kisi_ad;
        this.kisi_yas = kisi_yas;
    }

    public String getKisi_key() {
        return kisi_key;
    }

    public void setKisi_key(String kisi_key) {
        this.kisi_key = kisi_key;
    }

    public String getKisi_ad() {
        return kisi_ad;
    }

    public void setKisi_ad(String kisi_ad) {
        this.kisi_ad = kisi_ad;
    }

    public String getKisi_yas() {
        return kisi_yas;
    }

    public void setKisi_yas(String kisi_yas) {
        this.kisi_yas = kisi_yas;
    }
}
