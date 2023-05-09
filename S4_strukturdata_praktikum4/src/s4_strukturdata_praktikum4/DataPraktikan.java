package s4_strukturdata_praktikum4;

import java.util.*;

public class DataPraktikan {
    static String  menu;
    static Scanner input  = new Scanner(System.in);
    static HashMap<String, String> tabelData = new HashMap<>();
    static HashMap<String,String> tabelSesiLogin = new HashMap<>();
    static ArrayList listNim = new ArrayList();

    public static void main(String[] args) {
        tabelSesiLogin.put("kukohfr@webmail.umm.ac.id", "kiww1212");
        login(inputEmail(),inputPassword());

    }

    public static void mainMenu(){

        System.out.println("Daftar Menu");
        System.out.println("1.Tambah Data Baru");
        System.out.println("2.Tampilkan semua data");
        System.out.println("3.Tampilkan semua NIM Praktikan");
        System.out.println("4.Tampilkan semua Nama Asistenn");
        System.out.println("5.Tampilkan total semua data");
        System.out.println("6.Menghapus Data");
        System.out.println("7.Edit Data");
        System.out.println("8.Cari Nim berdasarkan Asisten");
        System.out.println("9.Log out");
        System.out.println("=======================================");
        System.out.print("masukan Menu : ");
        menu = input.nextLine();

        switch (menu){
            case "1":
                System.out.println("--Tambah Data Baru--");
                tambahData(inputNim(),inputAsisten());
                mainMenu();
                break;
            case "2":
                tampil();
                mainMenu();
                break;
            case "3":
                listNimPraktikan();
                mainMenu();
                break;
            case "4":
                listNamaAsisten();
                mainMenu();
                break;
            case "5":
                System.out.println("Total Data yang tersimpan : "+ totalEmail());
                mainMenu();
                break;
            case "6":
                System.out.println("--Menghapus Data--");
                if (hapusData(inputNim(),inputAsisten())){
                    System.out.println("Data berhasil dihapus");
                    System.out.println("==========================");
                    System.out.println();
                }else {
                    System.out.println("Data tidak ditemukan");
                    System.out.println("==========================");
                    System.out.println();
                }
                mainMenu();
                break;
            case "7":
                editData(inputNim(),inputAsisten());
                mainMenu();
                break;
            case "8":
                search(inputAsisten());
                mainMenu();
                break;
            case "9":

                break;
        }
    }

    public static String inputEmail(){
        String email;
        System.out.print("Masukan Email : " );
        email = input.nextLine();
        return email;
    }

    public static String inputPassword(){
        String password;
        System.out.print("Masukan password : " );
        password = input.nextLine();
        return password;
    }

    public static String inputNim(){
        String nim;
        System.out.print("Masukan NIM : ");
        nim = input.nextLine();

        return nim;
    }

    public static String inputAsisten(){
        String asisten;
        System.out.print("Masukan nama asisten : ");
        asisten = input.nextLine();
        return asisten;
    }

    public static boolean tambahData(String nimPraktikan, String namaAsisten){
        String tempNim;
        tempNim = nimPraktikan.substring(0,2);

        if (!checkNimEmpty(nimPraktikan)){
            System.out.println("==========================");
            System.out.println();
            return false;
        } else if (tempNim.equals("IF")){
            tabelData.put(nimPraktikan,namaAsisten);
            System.out.println("Data berhasil ditambahkan");
            System.out.println("==========================");
            System.out.println();

            return true;
        } else {

            System.out.println("Nim Anda tidak sesuai ketentuan");
            System.out.println("==========================");
            System.out.println();
            return false;
        }
    }

    public static void tampil(){
        System.out.println("Total Data yang tersimpan : " + tabelData.size());
        for (String i : tabelData.keySet()){
            System.out.println("Nim:" + i + "\tNama Asisten:" +tabelData.get(i));
        }

        System.out.println("==========================");
        System.out.println();
    }

    public static void listNimPraktikan(){
        int count=1;
        for (String listNim : tabelData.keySet()){
            System.out.println("Nim " + count + " : "+ listNim);
            count++;
        }
        System.out.println("==========================");
        System.out.println();
    }

    public static void listNamaAsisten(){
        int count=1;
        for (String namaAsisten : tabelData.keySet()){
            System.out.println("Nama Asisten " + count + " : "+ tabelData.get(namaAsisten));
            count++;
        }
        System.out.println("==========================");
        System.out.println();
    }

    public static int totalEmail(){

        return tabelData.size();
    }

    public static boolean hapusData (String nimPraktikan, String namaAsisten){
        return tabelData.remove(nimPraktikan,namaAsisten);
    }

    public static void editData(String nimPraktikan, String namaAsisten){

        tabelData.replace(nimPraktikan, namaAsisten);
        if (!checkNimExist(nimPraktikan)){
            System.out.println("Data sudah diedit");
            System.out.println("==========================");
            System.out.println();
        }else {
            System.out.println("Data tidak ditemukan");
            System.out.println("==========================");
            System.out.println();
        }

    }

    public static void search(String asisten){
        for(Map.Entry b: tabelData.entrySet()) {
            if (b.getValue().equals(asisten)) {
                listNim.add(b.getKey());
            }
        }
        System.out.println(listNim);
        listNim.clear();
    }

    public static boolean checkNimEmpty(String nimPraktikan){
        if (nimPraktikan.isBlank()){
            System.out.println("data tidak ada");
            return false;
        }else if (tabelData.containsKey(nimPraktikan)){ // jika key sudah ada.
            System.out.println("Data sudah terdaftar");
            return false;
        }
        return true;
    }

    public static boolean checkNimExist(String nimPraktikan){
        if (tabelData.containsKey(nimPraktikan)){ // jika key sudah ada.
            return false;
        }
        return true;
    }

    public static void login(String email, String password){
        for(Map.Entry temp: tabelSesiLogin.entrySet()) {
            if (temp.getKey().equals(email) && temp.getValue().equals(password)){
                System.out.println("Berhasil Login");
                System.out.println("============================");
                mainMenu();
            } else {
                System.out.println("Login gagal");
            }

        }

    }
}