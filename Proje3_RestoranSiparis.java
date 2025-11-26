/**
 * Ad Soyad: Hasan Kara
 * Numara: 250541015
 * Proje: Akilli Restoran Siparis Sistemi
 * Tarih: 26.11.2025
 */


import java.util.Scanner;

public class AkilliRestoranSiparisSistemi {
    //Ana yemekler
    public static double getMainDishPrice(int secim){
        switch (secim){
            case 1: return 85;  // Izgara Tavuk
            case 2: return 120; // Adana Kebap
            case 3: return 110; // Levrek
            case 4: return 65;  // Mantı
            default: return 0;  // Seçim yoksa 0
        }
    }
    //Baslangiclar
    public static double getAppetizerPrice(int secim){
        switch (secim){
            case 1: return 25;  //Corba
            case 2: return 45;  //Humus
            case 3: return 55;  //Sigara Boregi
            default: return 0;
        }
    }
    //İcecekler
    public static double getDrinkPrice(int secim) {
        switch (secim) {
            case 1:  return 15; // Kola
            case 2:  return 12; // Ayran
            case 3:  return 35; // Taze Meyve Suyu
            case 4:  return 25; // Limonata
            default: return 0;
        }
    }
    //Tatlilar
    public static double getDessertPrice(int secim){
        switch (secim) {
            case 1: return 65; // Künefe
            case 2: return 55; // Baklava
            case 3: return 35; // Sütlaç
            default: return 0;
        }
    }
    //Durum kontrolü

    //Combo menü?
    public static boolean isComboOrder(boolean anaVar,boolean icecekVar,boolean tatliVar){
        if(anaVar && icecekVar && tatliVar){
            return  true;
        } else  {
            return false;
        }
    }

    //Happy hour?
    public static boolean isHappyHour(int saat){
        if(saat>=14 && saat<=17){
            return  true;
        } else {
            return false;
        }
    }

    //3.Hesaplama metotlari
    public static double calculateDiscount(double toplamTutar,boolean combo, boolean ogrenci, int saat){
        double toplamIndirim = 0;
        double kalanTutar = toplamTutar;

        //Combo indirimi
        if(combo){
            double comboIndirimi = toplamTutar*0.15;
            toplamIndirim += comboIndirimi;
            kalanTutar -= comboIndirimi;
            System.out.println("Combo İndirimi: %15 -> -" + comboIndirimi + " TL");
        }

        //Happyhour indirimi
        if(isHappyHour(saat)){
            System.out.println("Happy Hour Saati! (İçecek indirimi kasada uygulanacak)");
        }

        //Ogrenci İndirimi
        if (ogrenci) {
            double indirimMiktari = kalanTutar * 0.10;
            toplamIndirim += indirimMiktari;
            System.out.println("Öğrenci İndirimi: -" + indirimMiktari + " TL");
        }

        return  toplamIndirim;
    }

    public static double calculateServiceTip(double tutar){
        return tutar * 0.10;
    }

    //Girdi ve Sonuclar
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== RESTORAN SİPARİŞİ ===");

        // Girdiler
        System.out.println("1:Tavuk(85) 2:Adana(120) 3:Levrek(110) 4:Mantı(65)");
        System.out.print("Ana Yemek (0-4): ");
        int anaSecim = scanner.nextInt();

        System.out.println("1:Çorba(25) 2:Humus(45) 3:Börek(55)");
        System.out.print("Başlangıç (0-3): ");
        int baslangicSecim = scanner.nextInt();

        System.out.println("1:Kola(15) 2:Ayran(12) 3:Meyve Suyu(35) 4:Limonata(25)");
        System.out.print("İçecek (0-4): ");
        int icecekSecim = scanner.nextInt();

        System.out.println("1:Künefe(65) 2:Baklava(55) 3:Sütlaç(35)");
        System.out.print("Tatlı (0-3): ");
        int tatliSecim = scanner.nextInt();

        System.out.print("Saat (8-23): ");
        int saat = scanner.nextInt();

        System.out.print("Öğrenci misiniz? (1=Evet, 0=Hayır): ");
        int ogr = scanner.nextInt();
        boolean ogrenciMi = (ogr == 1);

        // Hesaplamalar
        double fAna = getMainDishPrice(anaSecim);
        double fBas = getAppetizerPrice(baslangicSecim);
        double fIcecek = getDrinkPrice(icecekSecim);
        double fTatli = getDessertPrice(tatliSecim);

        double araToplam = fAna + fBas + fIcecek + fTatli;

        boolean comboMu = isComboOrder(fAna > 0, fIcecek > 0, fTatli > 0);

        // İNDİRİM HESAPLAMA (Happy Hour içecek fiyatı manuel ekleniyor)
        double genelIndirim = calculateDiscount(araToplam, comboMu, ogrenciMi, saat);

        // HOCANIN PARAMETRE HATASINI DÜZELTMEK İÇİN YAMA:
        // Eğer Happy Hour ise ve içecek varsa, indirimi manuel ekliyoruz
        if (isHappyHour(saat) && fIcecek > 0) {
            double happyIndirim = fIcecek * 0.20;
            genelIndirim += happyIndirim;
            System.out.println("Happy Hour (İçecek): -" + happyIndirim + " TL");
        }

        double odenecek = araToplam - genelIndirim;
        double bahsis = calculateServiceTip(odenecek);

        // Çıktı
        System.out.println("\n---------------------------");
        System.out.printf("Ara Toplam    : %.2f TL\n", araToplam);
        System.out.printf("TOPLAM İNDİRİM: -%.2f TL\n", genelIndirim);
        System.out.printf("ÖDENECEK      : %.2f TL\n", odenecek);
        System.out.printf("Bahşiş Önerisi: %.2f TL\n", bahsis);
        System.out.println("---------------------------");
    }
}

