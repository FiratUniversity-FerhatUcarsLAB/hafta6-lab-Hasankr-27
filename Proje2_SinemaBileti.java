/**
 * Ad Soyad: Hasan Kara
 * Numara: 250541015
 * Proje: Sinema Bileti Fiyatlandirma Sistemi
 * Tarih: 26.11.2025
 */

import java.util.Scanner;

public class SinemaBiletiFiyatlandirmaSistemi {

    // --- 1. ÇALIŞAN: Hafta sonu mu? ---
    public static boolean isWeekend(int gun) {
        if (gun == 6 || gun == 7) {
            return true;
        } else {
            return false;
        }
    }

    // --- 2. ÇALIŞAN: Matine (sabah) mi? ---
    public static boolean isMatinee(int saat) {
        if (saat < 12) {
            return true;
        } else {
            return false;
        }
    }

    // --- 3. ÇALIŞAN: Başlangıç Fiyatını Bul ---
    public static double calculateBasePrice(int gun, int saat) {
        boolean haftaSonuMu = isWeekend(gun);
        boolean matineMi = isMatinee(saat);

        if (haftaSonuMu == true) {
            if (matineMi == true) {
                return 55; // Hafta sonu sabah
            } else {
                return 85; // Hafta sonu normal
            }
        } else {
            if (matineMi == true) {
                return 45; // Hafta içi sabah
            } else {
                return 65; // Hafta içi normal
            }
        }
    }

    // --- 4. ÇALIŞAN: İndirim Oranını Bul ---
    public static double calculateDiscount(int yas, int meslek, int gun) {
        if (yas >= 65) return 0.30; // Yaşlı
        if (yas < 12)  return 0.25; // Çocuk

        if (meslek == 1) { // ÖĞRENCİ
            if (gun >= 1 && gun <= 4) {
                return 0.20; // Pzt-Perş arası
            } else {
                return 0.15; // Cuma-Pazar arası
            }
        }
        else if (meslek == 2) { // ÖĞRETMEN
            if (gun == 3) { // Sadece Çarşamba
                return 0.35;
            } else {
                return 0.0;
            }
        }
        else {
            return 0.0;
        }
    }

    // --- 5. ÇALIŞAN: Format Ücretini Bul ---
    public static double getFormatExtra(int filmTuru) {
        switch (filmTuru) {
            case 2: return 25; // 3D
            case 3: return 35; // IMAX
            case 4: return 50; // 4DX
            default: return 0; // 2D
        }
    }

    // --- 6. ÇALIŞAN: Son Fiyatı Hesapla ---
    public static double calculateFinalPrice(double temelFiyat, double indirimMiktari, double formatEkstra) {
        return (temelFiyat - indirimMiktari) + formatEkstra;
    }

    // --- 7. ÇALIŞAN: Ekrana Yazdır ---
    public static void generateTicketInfo(double temel, double indirim, double ekstra, double toplam) {
        System.out.println("\n--- BİLET DETAYLARI ---");
        System.out.println("Temel Fiyat:   " + temel + " TL");
        System.out.println("İndirim:      -" + indirim + " TL");
        System.out.println("Format Ücreti:+" + ekstra + " TL");
        System.out.println("ODENECEK:      " + toplam + " TL");
    }

    // --- PATRON (MAIN) ---
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== SİNEMA BİLETİ HESAPLAMA ===");

        // GÜN SEÇİMİ (Açıklamalı)
        System.out.println("\n--- GÜNLER ---");
        System.out.println("1: Pazartesi  2: Salı       3: Çarşamba");
        System.out.println("4: Perşembe   5: Cuma       6: Cumartesi");
        System.out.println("7: Pazar");
        System.out.print("Gün Seçiniz (1-7): ");
        int gun = scanner.nextInt();

        // SAAT SEÇİMİ
        System.out.print("\nSaat Kaçta? (Örn: 10, 15, 20): ");
        int saat = scanner.nextInt();

        // YAŞ SEÇİMİ
        System.out.print("Yaşınız: ");
        int yas = scanner.nextInt();

        // MESLEK SEÇİMİ (Açıklamalı)
        System.out.println("\n--- MESLEK ---");
        System.out.println("1: Öğrenci");
        System.out.println("2: Öğretmen");
        System.out.println("3: Diğer (Sivil)");
        System.out.print("Meslek Seçiniz (1-3): ");
        int meslek = scanner.nextInt();

        // FİLM TÜRÜ (Açıklamalı)
        System.out.println("\n--- FİLM TÜRÜ ---");
        System.out.println("1: 2D (Standart) -> Fark Yok");
        System.out.println("2: 3D            -> +25 TL");
        System.out.println("3: IMAX          -> +35 TL");
        System.out.println("4: 4DX           -> +50 TL");
        System.out.print("Film Türü Seçiniz (1-4): ");
        int filmTuru = scanner.nextInt();

        // HESAPLAMA ÇAĞRILARI
        double temelFiyat = calculateBasePrice(gun, saat);
        double indirimOrani = calculateDiscount(yas, meslek, gun);
        double indirimParasi = temelFiyat * indirimOrani;
        double formatUcreti = getFormatExtra(filmTuru);
        double sonFiyat = calculateFinalPrice(temelFiyat, indirimParasi, formatUcreti);

        // SONUÇ YAZDIRMA
        generateTicketInfo(temelFiyat, indirimParasi, formatUcreti, sonFiyat);
    }
}
