/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package odev1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Omar Alkadri omar.alkadri@ogr.sakarya.edu.tr
 * @since 3/1/2019
 *        <p>
 *        basit istatistik programı
 *        </p>
 */
public class Odev1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        String dosyaIsmi = "program.c";

        try {
            FileReader fileReader = new FileReader(dosyaIsmi);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            FileReader fileReader1 = new FileReader(dosyaIsmi);
            BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
            String OperatorSorgula = "\\++|\\*|\\=+|\\-+|\\/|\\&+|\\<|\\>|\\|+|\\!";
            String FonkBul = "\\(\\)|\\{";
            String Fonk_Ismi_Bul = "\\(|\\)\\{";
            String Fonk_ParaM_Bul = "\\(\\,?|\\)\\{";
            String AciklamaSayma = "\\//|\\/\\*|\\*\\/|\\*\\*|\\*^";
            String Fonk_ParaM_Temizle = "int |char |float |double |long double |short |long |string ";
            String Fonk_ParaM = null;
            String satir = null;
            String Fonk_Ismi = null;
            int Operator = 0;
            int FonkSayisi = 0;
            int ParametreSayisi = 0;
            while ((satir = bufferedReader.readLine()) != null) {

                Pattern p = Pattern.compile(AciklamaSayma);
                Matcher m = p.matcher(satir);

                if (!m.find()) {
                    p = Pattern.compile(OperatorSorgula);
                    m = p.matcher(satir);
                    while (m.find()) {
                        Operator++;
                    }
                    p = Pattern.compile(FonkBul);
                    m = p.matcher(satir);
                    if (m.find()) {
                        FonkSayisi++;
                        if (satir.contains("(") && satir.contains(")") && satir.contains("{")
                                && !satir.contains(";") && !satir.contains("()") && !satir.contains("( )")) {
                            if (satir.contains(",")) {
                                ParametreSayisi++;
                            }
                            ParametreSayisi++;
                        }
                    }
                }
            }
            bufferedReader.close();
            System.out.println("Toplam Operator Sayısı: " + Operator);
            System.out.println("Toplam Fonksiyon Sayısı: " + FonkSayisi);
            System.out.println("Toplam Parametre Sayısı: " + ParametreSayisi);
            System.out.println("Fonksiyon İsimleri: ");

            while ((satir = bufferedReader1.readLine()) != null) {
                Pattern p;
                Matcher m;

                p = Pattern.compile(AciklamaSayma);
                m = p.matcher(satir);

                if (!m.find()) {
                    if (satir.contains("(") && satir.contains(")") && satir.contains("{")
                            && !satir.contains(";")) {
                        p = Pattern.compile(Fonk_Ismi_Bul);
                        m = p.matcher(satir);
                        if (m.find()) {
                            Fonk_Ismi = satir.trim().substring(satir.indexOf(" ") + 1, satir.indexOf("("));
                        }
                        p = Pattern.compile(Fonk_ParaM_Bul);
                        m = p.matcher(satir);
                        if (m.find()) {
                            p = Pattern.compile(Fonk_ParaM_Temizle);
                            m = p.matcher(satir);
                            satir = m.replaceAll("");
                            Fonk_ParaM = satir.trim().substring(satir.indexOf("(") + 1, satir.indexOf(")"));
                        }
                        System.out.println(Fonk_Ismi + " - Parametreler: " + Fonk_ParaM);
                    }
                }
            }
            bufferedReader1.close();
        } catch (FileNotFoundException e) {
            System.out.println("Dosya Açılamıyour '" + dosyaIsmi + "'");
        } catch (IOException e) {
            System.out.println("Dosya Okunamıyor '" + dosyaIsmi + "'");
        }

    }

}
