/**
 * Ad Soyad: Hasan Kara
 * Numara: 250541015
 * Proje: Ogrenci Not Degerlendirme Sistemi
 * Tarih: 26.11.2025
 */

import java.util.Scanner;

public class OgrenciNotDegerlendirmeSistemi {
    //vize,fianl ve odevlerin sonucu alınır
    public static double calculateAverage(double vize,double ffinal,double odev){
        return (vize*0.3) + (ffinal*0.4) + (odev*0.3);
    }
    //ortalaması hesaplanır
    public static boolean isPassingGrade(double ortalama){
        return (ortalama)>=50;
    }
    //ortalamaya göre harf notu belirlenir
    public static String getLetterGrade(double ortalama) {
        if (ortalama >= 90) return "A";
        else if (ortalama >= 80) return "B";
        else if (ortalama >= 70) return "C";
        else if(ortalama >= 60) return  "D";
        else return "F";
    }
    //onur listesinin durumu belirlenir
    public static boolean isHonorList(double ortalama,double vize,double ffinal,double odev){
        return (ortalama>=85) && (vize>=70) && (ffinal>=70) && (odev >=70);
    }
    //bütünleme sınavına kalmış mı ona bakılır
    public static boolean hasRetakeRight(double ortalama){
        return (ortalama>=40 && ortalama<50);
    }
    //kullanıcıdan girdi alınır
    public static void main(String[] args){
        Scanner input = new Scanner(System.in);

        System.out.print("Vize: ");
        double vize = input.nextDouble();

        System.out.print("Final: ");
        double ffinal = input.nextDouble();

        System.out.print("Odev: ");
        double odev = input.nextDouble();

        //bundan sonraki aşama çıktı olarak gözükecek
        
        System.out.println("\n=== OGRENCI NOT RAPORU ===");

        // Notları yazdır
        System.out.printf("Vize Notu  : %.1f\n", vize);
        System.out.printf("Final Notu : %.1f\n", ffinal);
        System.out.printf("Odev Notu  : %.1f\n", odev);
        System.out.println("------------------------------");

        double ortalama = calculateAverage(vize, ffinal, odev);

        System.out.printf("Ortalama     : %.1f\n", ortalama);
        System.out.println("Harf Notu    : " + getLetterGrade(ortalama));

        System.out.println("Durum        : " + (isPassingGrade(ortalama) ? "GECTI" : "KALDI"));

        System.out.println("Onur Listesi : " + (isHonorList(ortalama,vize,ffinal,odev) ? "EVET" : "HAYIR" ));

        System.out.println("Butunleme    : " + (hasRetakeRight(ortalama) ? "VAR" : "YOK"));


    }
}
